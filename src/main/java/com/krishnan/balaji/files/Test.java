package com.krishnan.balaji.files;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Test {

	private static final Logger log = Logger.getLogger(Test.class.getName());

	public static void main(String[] args) {

		ResourceBundle rb = ResourceBundle.getBundle("constants");
		String rootDir = rb.getString("testDirectory");
		try {
			Path path = Paths.get(new URI(rootDir));
			File file = path.toFile();
			log.info(file.getName());
			Files.walkFileTree(path, new PrettyPrintFileVisitor());			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
