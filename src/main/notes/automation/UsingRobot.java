package main.notes.automation;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class UsingRobot {
	
	
	
	public static void main(String[] args) {
		try {
			final Robot r = new Robot();
			
			
			
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		    BufferedImage capture = r.createScreenCapture(screenRect);
		    
		    File imageFile = new File("single-screen.png");
		    Path p = FileSystems.getDefault().getPath(imageFile.getPath());
		    System.out.println(imageFile.getPath());
		    System.out.println(p.toString());
//		    imageFile.
//		    ImageIO.write(capture, "png", imageFile);
			
		} catch (AWTException e) {
			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}
