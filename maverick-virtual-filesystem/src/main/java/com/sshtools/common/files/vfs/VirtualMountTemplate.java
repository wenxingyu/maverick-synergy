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
package com.sshtools.common.files.vfs;

import org.apache.commons.vfs2.FileSystemOptions;

import com.sshtools.common.files.AbstractFileFactory;

public final class VirtualMountTemplate extends AbstractMount {

	private AbstractFileFactory<?> actualFileFactory;
	private FileSystemOptions fileSystemOptions;
	private boolean createMountFolder;
	
	public VirtualMountTemplate(String mount, String path,
			AbstractFileFactory<?> actualFileFactory,
			boolean createMountFolder) {
		super(mount, path, false, false);
		this.actualFileFactory = actualFileFactory;
		this.createMountFolder = createMountFolder;
	}

	public boolean isCreateMountFolder() {
		return createMountFolder;
	}
	public AbstractFileFactory<?> getActualFileFactory() {
		return actualFileFactory;
	}

	public FileSystemOptions getFileSystemOptions() {
		return fileSystemOptions;
	}

	public void setFileSystemOptions(FileSystemOptions fileSystemOptions) {
		this.fileSystemOptions = fileSystemOptions;
	}
}
