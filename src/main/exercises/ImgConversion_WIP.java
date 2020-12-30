package main.exercises;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgConversion_WIP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public byte[] ByteArrayConversion() {
		try {
			BufferedImage bufferimage = ImageIO.read(new File("myimage.jpg"));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(bufferimage, "jpg", output);
			byte[] data = output.toByteArray();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
