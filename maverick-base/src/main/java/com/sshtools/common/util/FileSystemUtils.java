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
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.sshtools.common.util;

public class FileSystemUtils {

	public static String addTrailingSlash(String str) {
		if(!str.endsWith("/")) {
			return str + "/";
		} else {
			return str;
		}	
	}
	
	public static String removeTrailingSlash(String str) {
		if(str.endsWith("/")) {
			return str.substring(0, str.length()-1);
		} else {
			return str;
		}	
	}

	public static String removeStartingSlash(String str) {
		if(str.startsWith("/")) {
			return str.substring(1);
		}
		return str;
	}
	
	public static String addStartingSlash(String str) {
		if(str.startsWith("/")) {
			return str;
		}
		return "/" + str;
	}

	public static String getFilename(String path) {
		int idx = path.indexOf("/");
		if(idx > -1) {
			return path.substring(idx+1);
		} else {
			return path;
		}
	}
}