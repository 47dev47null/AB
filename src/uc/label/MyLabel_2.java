package uc.label;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.data.MImages;

public class MyLabel_2 extends JLabel{

	public MyLabel_2()
	{
		
	}
	
	protected void paintComponent(Graphics g) {
		File f = new File(MImages.getPath() + "/data/images/select_level.png" );
		Image image = null;
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
