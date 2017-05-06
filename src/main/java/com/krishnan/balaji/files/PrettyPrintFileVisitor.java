package com.krishnan.balaji.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrettyPrintFileVisitor implements FileVisitor<Path> {

	private static final Logger log = Logger.getLogger(PrettyPrintFileVisitor.class.getName());
	private static final String indicator = "-";
	private static final String branchIndicator = "|";
	private int currentIdentation = 0;

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {		
		printDirectoryName(dir);
		currentIdentation++;
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		printFileName(file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		log.log(Level.SEVERE, "Visiting " + file.getFileName() + " failed");
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		currentIdentation--;
		return FileVisitResult.CONTINUE;
	}

	private void printDirectoryName(Path dir) {
		for (int i = 0; i < currentIdentation; i++)
			System.out.print("  ");
		System.out.println(branchIndicator + "" + indicator + " " + dir.getFileName()+"/");
	}

	private void printFileName(Path file) {
		for (int i = 0; i < currentIdentation; i++)
			System.out.print("  ");
		System.out.println(branchIndicator);
		for (int i = 0; i < currentIdentation; i++)
			System.out.print("  ");
		System.out.println(branchIndicator + indicator + " " + file.getFileName());
	}
}
