// MyImagaIO.java
import imagereader.IImageIO;

import java.awt.image.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;


public class ImplementImageIO implements IImageIO{

	public int byteToInt(byte[] b, int start)
	{
		int t1, t2, t3, t4;
		t1 = b[start + 3] & 0xff;
		t2 = b[start + 2] & 0xff;
		t3 = b[start + 1] & 0xff;
		t4 = b[start] & 0xff;
		return (t1 << 24 | t2 << 16 | t3 << 8 | t4); 
	}

	private Image img ;

	public Image myRead(String filePath){
		try{
			
			File file = new File(filePath);
			FileInputStream fin = new FileInputStream(file);

			// BMP文件的 位图文件头 和 位图信息图
			byte fileHeader[] = new byte[14];
			byte infoHeader[] = new byte[40];

			fin.read(fileHeader, 0, 14);
			fin.read(infoHeader, 0, 40);

			// 位图全部像素占用的字节数，位图宽度及高度 
			int biWidth = byteToInt(infoHeader, 4);
			int biHeight = byteToInt(infoHeader, 8);
			int biSizeImage = byteToInt(infoHeader, 20);

			// 每个像素的位数
			int bitCount = (infoHeader[15] & 0xff) << 8 | (infoHeader[14] & 0xff);

			// 即一个像素占3个字节
			if (bitCount == 24) {
				int emptyByte = biSizeImage / biHeight - 3 * biWidth;

				// rgbByte[]读入颜色表,index作为其下标变量
				int pixel[] = new int [biWidth * biHeight];
				byte rgbByte[] = new byte[biSizeImage];
				fin.read(rgbByte, 0, biSizeImage);
				int index = 0;

				// 从下到上，从左到右存储图像
				for (int i = biHeight - 1; i >= 0; i--)
				{
					for (int j = 0; j < biWidth; j++)
					{
						pixel[i * biWidth + j] = 0xff << 24 | (rgbByte[index + 2] & 0xff) << 16 |
												(rgbByte[index + 1] & 0xff) << 8 | (rgbByte[index] & 0xff);
						index += 3;
					} 
					index += emptyByte; 
					// 补齐空白字节
				}

				img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(biWidth, biHeight, pixel, 0, biWidth));	
			}	

			fin.close();
			return img;	
		} catch (Exception e){
			e.printStackTrace();
		}
		return (Image)null;
	}

	public Image myWrite(Image image, String filePath){
		try{
			File imgFile = new File(filePath + "-BMP.bmp");
			BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D graph = buffer.createGraphics(); 
			graph.drawImage(image, 0, 0, null);
			graph.dispose();
			ImageIO.write(buffer, "bmp", imgFile);
			return image;
		}catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}
}


	
