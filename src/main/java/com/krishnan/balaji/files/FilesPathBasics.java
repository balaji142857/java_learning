package com.krishnan.balaji.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesPathBasics {
	public static void main(String[] args) throws IOException {

		Path p = null,p1 = null,p2 = null,subdir = null;
		Files.exists(p);
		Files.notExists(p);
		Files.isRegularFile(p);
		Files.isReadable(p);
		Files.isWritable(p);
		Files.isExecutable(p);
		Files.isSameFile(p1, p2);
		Files.createDirectory(p);
		Files.createDirectories(subdir); // like mkdir -r


		String prefix = "log_";
		String suffix = ".txt";
		Files.createTempFile(p, prefix, suffix);
		Files.createTempFile(p, null, null);
		Files.delete(p);
		Files.deleteIfExists(p);


		//copy file
		Path dir1 = null,dir2 = null;
		 Files.createDirectory(dir1);
		 Files.createDirectory(dir2);
		 Path file1 = dir1.resolve("filetocopy.txt");
		 Path file2 = dir2.resolve("filetocopy.txt");
		 Files.createFile(file1);
		 Files.copy(file1, file2);
		 Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);

		 Files.move(file1, file2);
		 Files.move(file1, file2, StandardCopyOption.REPLACE_EXISTING);



		Path path = Paths.get("c:\\users\\admin\\file.txt"); 
		path = path.getFileName(); // returns file.txt
		path = path.getName(0); // returns users
		int count = path.getNameCount(); // returns 3 (users, admin and file.txt)
		path = path.subpath(0, 2); // returns users/admin
		path = path.getParent(); // returns /users/admin
		path = path.getRoot(); // returns c:\ (if we were in a unix filesystem, it would return /)
		
	}

}
