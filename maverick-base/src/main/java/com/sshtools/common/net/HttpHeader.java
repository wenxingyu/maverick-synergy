/**
 * (c) 2002-2019 JADAPTIVE Limited. All Rights Reserved.
 *
 * This file is part of the Maverick Synergy Java SSH API.
 *
 * Maverick Synergy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Maverick Synergy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Maverick Synergy.  If not, see <https://www.gnu.org/licenses/>.
 */
/* HEADER */
package com.sshtools.common.net;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Utility class to process HTTP headers.
 * 
 * @author Lee David Painter
 *
 */
public abstract class HttpHeader {

    protected final static String white_SPACE = " \t\r";
    Hashtable<String,String> fields;


    protected String begin;

    protected HttpHeader() {
        fields = new Hashtable<String,String>();
    }

    protected String readLine(ByteBuffer in) throws IOException {
        StringBuffer lineBuf = new StringBuffer();
        int c;

        while (true) {
            
        	if(!in.hasRemaining()) {
        		throw new EOFException("Unexpected EOF during HTTP header read");
        	}
        	
        	c = (int) in.get() & 0xFF;

            if (c == '\r') {
                continue;
            }

            if (c != '\n') {
                lineBuf.append((char) c);
            } else {
                break;
            }
        }

        return new String(lineBuf);
    }


    public String getStartLine() {
        return begin;
    }

    public Hashtable<String,String> getHeaderFields() {
        return fields;
    }

    public Enumeration<String> getHeaderFieldNames() {
        return fields.keys();
    }

    public String getHeaderField(String headerName) {
    	for(Enumeration<String> e = fields.keys();e.hasMoreElements();) {
    		String f = (String)e.nextElement();
    		if(f.equalsIgnoreCase(headerName)) {
    			return (String) fields.get(f);
    		}
    	}
        return null;
    }

    public void setHeaderField(String headerName, String value) {
        fields.put(headerName, value);
    }

    public String toString() {
        String str = begin + "\r\n";
        Enumeration<String> it = getHeaderFieldNames();

        while (it.hasMoreElements()) {
            String fieldName = (String) it.nextElement();
            str += (fieldName + ": " + getHeaderField(fieldName) + "\r\n");
        }

        str += "\r\n";

        return str;
    }

    protected void processHeaderFields(ByteBuffer in)
        throws IOException {
        fields = new Hashtable<String,String>();

        StringBuffer lineBuf = new StringBuffer();
        String lastHeaderName = null;
        int c;

        while (true) {
        	
        	if(!in.hasRemaining()) {
        		throw new EOFException("Unexpected EOF whilst reading HTTP Headers");
        	}
        	
            c = (int) in.get() & 0xFF;

            if (c == '\r') {
                continue;
            }

            if (c != '\n') {
                lineBuf.append((char) c);
            } else {
                if (lineBuf.length() != 0) {
                    String line = lineBuf.toString();
                    lastHeaderName = processNextLine(line, lastHeaderName);
                    lineBuf.setLength(0);
                } else {
                    break;
                }
            }
        }

        c = (int) in.get() & 0xFF;
    }

    private String processNextLine(String line, String lastHeaderName)
        throws IOException {
        String name;
        String value;
        char c = line.charAt(0);

        if ((c == ' ') || (c == '\t')) {
            name = lastHeaderName;
            value = getHeaderField(lastHeaderName) + " " + line.trim();
        } else {
            int n = line.indexOf(':');

            if (n == -1) {
                throw new IOException(
                    "HTTP Header encoutered a corrupt field: '" + line + "'");
            }

            name = line.substring(0, n).toLowerCase();
            value = line.substring(n + 1).trim();
        }

        setHeaderField(name, value);

        return name;
    }
}
