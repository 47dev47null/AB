package uc.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.data.MImages;

public class ImageItem_Panel extends JPanel{

	private String img;
	
	public ImageItem_Panel(String img)
	{
		this.img = img;
	}
	
	protected void paintComponent(Graphics g) {
		File f = new File(MImages.getPath() + "/data/" + img);
		Image image=null;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = image.getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_SMOOTH);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),
				this);


	}
}
