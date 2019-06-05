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
/* HEADER */
package com.sshtools.common.ssh.components;

import java.io.IOException;
import java.security.PrivateKey;

import com.sshtools.common.ssh.SshException;

/**
 *  Interface for SSH supported private keys.
 *
 *  @author Lee David Painter
 */
public interface SshPrivateKey {

  /**
   * Create a signature from the data.
   * @param data
   * @return byte[]
   * @throws SshException
   */
  public byte[] sign(byte[] data) throws IOException;

  public byte[] sign(byte[] data, String signingAlgorithm) throws IOException;
  
  public String getAlgorithm();

  PrivateKey getJCEPrivateKey();
}