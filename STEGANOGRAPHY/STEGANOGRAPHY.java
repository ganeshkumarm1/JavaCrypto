import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import javax.imageio.*;
import java.util.*;

class STEGANOGRAPHY
{
	public static BufferedImage encrypt(BufferedImage img, int w, int h, String plaintText) throws Exception
	{
		int textCount = 0;
		int count = 1;
		for(int i = 0; i < w; i ++)
		{
			for(int j = 0; j < h; j ++)
			{
				if(count % 8 == 0)
				{
					if(textCount == plaintText.length())
						break;
					img.setRGB(j, i, (int) plaintText.charAt(textCount));
					textCount ++;
				}
				count ++;
			}
		}
		ImageIO.write(img, "jpg", new File("kira_enc.jpg"));
		return img;
	}

	public static String decrypt(BufferedImage img, int w, int h, String plaintText) throws Exception
	{
		int textCount = 0;
		int count = 1;
		String decrypted = "";
		for(int i = 0; i < w; i ++)
		{
			for(int j = 0; j < h; j ++)
			{
				if(count % 8 == 0)
				{
					if(textCount == plaintText.length())
						break;
					decrypted += (char) img.getRGB(j, i);
					textCount ++;
				}
				count ++;
			}
		}
		return decrypted;
	}

	public static void main(String args[]) throws Exception
	{
		BufferedImage img = ImageIO.read(new File("kira.jpg"));
		int w = img.getWidth();
		int h = img.getHeight();

		Scanner sc = new Scanner(System.in);
		System.out.println("plaintText: ");
		String plaintText = sc.nextLine();

		
		img = encrypt(img, w, h, plaintText);
		String decrypted = decrypt(img, w, h, plaintText);
		System.out.println(decrypted);
	}
}