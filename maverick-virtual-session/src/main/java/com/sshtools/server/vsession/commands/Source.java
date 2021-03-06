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
package com.sshtools.server.vsession.commands;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;

import com.sshtools.common.permissions.PermissionDeniedException;
import com.sshtools.server.vsession.Msh;
import com.sshtools.server.vsession.VirtualConsole;

public class Source extends Msh {

	public Source() {
		super("source", SUBSYSTEM_SHELL, "source <script>", null);
		setDescription("Run script in same process");
		setBuiltIn(true);
	}

	public void run(CommandLine cli, VirtualConsole console) throws IOException, PermissionDeniedException {
		
		this.commandFactory = console.getShell().getCommandFactory();
		String[] args = cli.getArgs();
		if (args.length != 2) {
			throw new IllegalArgumentException("Expects a single script as the argument.");
		} else {
			source(console, console.getCurrentDirectory().resolveFile(args[1]));
		}
	}
}