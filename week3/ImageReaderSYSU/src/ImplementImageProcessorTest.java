import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.image.*;
import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;

public class ImplementImageProcessorTest {

	// filePath record the test pictures' location
	private String filePath = "/home/administrator/Desktop/GridWorld_SYSU/Week3/ImageReader/bmptest/";
	private ImplementImageIO myIO = new ImplementImageIO();
	private ImplementImageProcessor myPro = new ImplementImageProcessor();
	private Image stdImg1 = myIO.myRead(filePath + "1.bmp");
	private Image stdImg2 = myIO.myRead(filePath + "2.bmp");
	
	// function cmp is used to compare 2 image whether they are same or not
	public boolean cmp(Image img1, Image img2) {
		int w = img1.getWidth(null), 
			h = img1.getHeight(null);	
		
		if ( (w != img2.getWidth(null)) || (h != img2.getHeight(null)) )
			return false;
		
		BufferedImage buff1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
		BufferedImage buff2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
		buff1.getGraphics().drawImage(img1, 0, 0, w, h, null);
		buff2.getGraphics().drawImage(img2, 0, 0, w, h, null);
		
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++) {
				if (buff1.getRGB(i, j) != buff2.getRGB(i, j)){
					return false;
				}
			}
		}	
		
		return true;
	}
	
	// Test for pic1 in red
	@Test
	public void testRed1() {
		Image stdRedImg1 = myIO.myRead(filePath + "goal/1_red_goal.bmp");
		Image myRedImg1 = myPro.showChanelR(stdImg1);
		
		assertEquals(cmp(stdRedImg1, myRedImg1), true);
	}
	
	// Test for pic2 in red
	@Test
	public void testRed2() {
		Image stdRedImg2 = myIO.myRead(filePath + "goal/2_red_goal.bmp");
		Image myRedImg2 = myPro.showChanelR(stdImg2);
		
		assertEquals(cmp(stdRedImg2, myRedImg2), true);
	}
	
	// Test for pic1 in green
	@Test
	public void testGreen1() {
		Image stdGreenImg1 = myIO.myRead(filePath + "goal/1_green_goal.bmp");
		Image myGreenImg1 = myPro.showChanelG(stdImg1);
		
		assertEquals(cmp(stdGreenImg1, myGreenImg1), true);
	}
	
	// Test for pic2 in green
	@Test
	public void testGreen2() {
		Image stdGreenImg2 = myIO.myRead(filePath + "goal/2_green_goal.bmp");
		Image myGreenImg2 = myPro.showChanelG(stdImg2);
		
		assertEquals(cmp(stdGreenImg2, myGreenImg2), true);
	}
	
	// Test for pic1 in blue
	@Test
	public void testBlue1() {
		Image stdBlueImg1 = myIO.myRead(filePath + "goal/1_blue_goal.bmp");
		Image myBlueImg1 = myPro.showChanelB(stdImg1);
		
		assertEquals(cmp(stdBlueImg1, myBlueImg1), true);
	}
	
	// Test for pic2 in blue
	@Test
	public void testBlue2() {
		Image stdBlueImg2 = myIO.myRead(filePath + "goal/2_blue_goal.bmp");
		Image myBlueImg2 = myPro.showChanelB(stdImg2);
		
		assertEquals(cmp(stdBlueImg2, myBlueImg2), true);
	}
	
	// Test for pic1 in gray
	@Test
	public void testGray1() {
		Image stdGrayImg1 = myIO.myRead(filePath + "goal/1_gray_goal.bmp");
		Image myGrayImg1 = myPro.showGray(stdImg1);
		
		assertEquals(cmp(stdGrayImg1, myGrayImg1), true);
	}
	
	// Test for pic2 in gray
	@Test
	public void testGray2() {
		Image stdGrayImg2 = myIO.myRead(filePath + "goal/2_gray_goal.bmp");
		Image myGrayImg2 = myPro.showGray(stdImg2);
		
		assertEquals(cmp(stdGrayImg2, myGrayImg2), true);
	}

}
