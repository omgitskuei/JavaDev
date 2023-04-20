package main.notes.file.operatingFileExplorer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileBetweenTwoDIRs {
	public static void main(String[] args) throws IOException, URISyntaxException {
		// One way to create paths (hard to use because it doesn't take array or
		// List<String>)
		Path aPath = Paths.get("D:", "someFolde", "someSubFolder", "test1", "hi.txt"); // note added txt to uri
		System.out.println("aPath = " + aPath.toAbsolutePath());

		// Get current working directory, where this project is located
		String projectDir = System.getProperty("user.dir");
		// Get path of this class
		StringBuilder sb = new StringBuilder();
		sb.append(projectDir).append(File.separator)
				.append("src").append(File.separator)
				.append("main").append(File.separator)
				.append("notes").append(File.separator)
				.append("file").append(File.separator)
				.append("operatingFileExplorer").append(File.separator);
		String operatingFileExplorer = sb.toString();
		// Get path of /testSource and /testDestination
		String testSource = operatingFileExplorer + "testSource" + File.separator;
		String testDestination = operatingFileExplorer + "testDestination" + File.separator;
		System.out.println(String.format(
				"sourcePath=		%s \r\n"
						+ "destinationPath=	%s",
				testSource, testDestination));

		// Append name of the file you want to copy from testSource to the end of sourcePath
		Path sourcePath = convertFilepathStringToPath(testSource, "targetFile.txt");
		// Append name for the new file that will be created in testDestination
		Path endPath = convertFilepathStringToPath(testDestination, "targetFile2.txt");
		
		// Attempt to copy the file
		boolean success = copyFile(sourcePath, endPath);
		System.out.println(success);
	}

	private static Path convertFilepathStringToPath(String startPathString, String fileName) throws URISyntaxException {
		return Paths.get(startPathString + fileName);
	}

	private static boolean copyFile(Path origin, Path destination) {
		System.out.println("Attempting to copy from " + origin.toAbsolutePath() + " \r\n"
				+ "to " + destination.toAbsolutePath());
		if (!Files.exists(origin)) {
			System.out.println("Origin file doesn't exist, can't copy non-existant file, copy failed.");
			return false;
		}
		if (!Files.exists(destination)) {
			try {
				Files.createDirectories(destination);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Can't create a directory at this destination:" + destination + ", copy failed.");
				return false;
			}
		}
		try {
			Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println(
					"Can't copy file: " + origin.toString() + " to this destination:" + destination + ", copy failed.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
