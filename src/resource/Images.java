package resource;

import java.awt.*;

import javax.swing.ImageIcon;

import com.data.MImages;

public class Images {

	public static Image getMenuBackGround()
	{
		
		ImageIcon icon = new ImageIcon(MImages.getPath() + "/data/images/BACKGROUNDS_GE_1.png/");
		return icon.getImage();
	}
	
	public static Image getStartImage()
	{
		ImageIcon icon = new ImageIcon(MImages.getPath() + "/data/images/BACKGROUNDS_GE_1.png/");
		return icon.getImage();
	}
	
	public static final String BTN_START = "/images/button1.png";
	public static final String BTN_QUIT = "/images/button3.png";
	public static final String BTN_MENU = "/images/button10.png";
	public static final String BTN_RESTART = "/images/button2.png";
	public static final String BTN_BACK = "/images/button6.png";
	public static final String BTN_RESTARTG = "/images/button9.png";
	public static final String BTN_PAUSE = "/images/button8.png";
	public static final String BTN_NEXT = "/images/button5.png";
	public static final String BTN_SLEVEL = "/images/button4.png";
	public static final String BTN_CONTINUE = "/images/button7.png";
	public static final String BTN_LEVEL1 = "/images/buttonl1.png";

	
	public static final String LAB_LEVEL_RIGHT = "/images/level_sheet_1.png";
	public static final String LAB_LEVEL_LEFT = "/images/level_sheet_2.png";
	public static final String LAB_NEW_HIGH_SCORE = "/images/newscore.png";
	
	public static final int BTN_START_X = 200;
	public static final int BTN_START_Y = 125;
	
	public static final int BTN_X = 50;
	public static final int BTN_Y = 50;
	
	public static final int BTN_SMALL_X = 40;
	public static final int BTN_SMALL_Y = 40;
	
}
