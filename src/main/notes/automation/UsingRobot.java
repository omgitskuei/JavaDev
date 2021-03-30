package main.notes.automation;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UsingRobot {
	
	
	
	public static void main(String[] args) {
		UsingRobot thisClass = new UsingRobot();
		
		try {
			final Robot r = new Robot();
			r.keyPress(KeyEvent.VK_WINDOWS);
			r.keyPress(KeyEvent.VK_R);
			Thread.sleep(1000);
			r.keyRelease(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_WINDOWS);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
//			r.keyPress(KeyEvent.VK_ALT);
//			r.keyPress(KeyEvent.VK_TAB);
//			Thread.sleep(1000);
//			r.keyRelease(KeyEvent.VK_TAB);
//			r.keyRelease(KeyEvent.VK_ALT);
//			Thread.sleep(1000);
			
			
			
			
			
//			thisClass.takeScreenshot("D:\\i92008cc63\\Github\\JavaDev\\JavaDev", "testPic", "jpg");
			
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void takeScreenshot(
			String targetFolder, 
			final String fileName, 
			final String fileType) {
		Boolean validParams = false;
		// validate params
		validParams = ("png".equals(fileType) || "jpg".equals(fileType) || "gif".equals(fileType) || "bmp".equals(fileType)) ? true : false;
		validParams = "".equals(fileName) ? false : true;
		if(targetFolder.length() > 0) {
			targetFolder = targetFolder + "\\";
		}
		
		if(validParams == true) {
			System.out.println("takeScreenshot");
			try {
				final Robot r = new Robot();
				
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			    BufferedImage capture = r.createScreenCapture(screenRect);
			    
			    File imageFile = new File(targetFolder + fileName + "." + fileType);
			    
			    ImageIO.write(capture, fileType, imageFile);
			} catch (IOException e) {
				// Fail to print image file
				e.printStackTrace();
			} catch (AWTException e) {
				// Fail to instantiate Robot
				e.printStackTrace();
			}
		}
	}
}
