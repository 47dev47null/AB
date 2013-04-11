package uc.button;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

import com.data.MImages;

public class MyButton extends JButton implements MouseListener{

	
	
	//private int iwidth;
	//private int iheight;
	
	int pwidth =50;
	int pheight =50;
	
	String img;
	int x;
	int y;
	Image image;
	double bigger = 1.2;

	boolean is_first = true;
	
	public MyButton(String img)
	{
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		
		
		
		this.img = img;
		
		this.addMouseListener(this);
		
		File f = new File(MImages.getPath() + "/data/" + img );
		
	
		image=null;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				buttonComponentShown(evt);
			}
		});
		
		
		ImageIcon imageicon = new ImageIcon(MImages.getPath() + "/data/images/cursor1.png/");
		
		
		Cursor cursor=Toolkit.getDefaultToolkit().createCustomCursor(
				 imageicon.getImage(),new Point(0,0),"myCursor");
		
		this.setCursor(cursor);
	}
	
	private void buttonComponentShown(ComponentEvent e)
	{
		//System.out.println("butt" + this.getWidth());
	}
	
	public void paint(Graphics g) {
		
		if (this.is_first)
		{
			this.pwidth = (int) (this.getWidth() / bigger);
			this.pheight = (int) (this.getHeight() / bigger);
			
			x = (int) ((getWidth() - (getWidth() / bigger)) / 2);
			y = (int) ((getHeight() - (getHeight() / bigger)) / 2);
		}
		
		
		Image image2 = image.getScaledInstance(pwidth,pheight, Image.SCALE_SMOOTH);
		g.drawImage(image2, x, y, pwidth,pheight, this);

	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.pwidth = getWidth();
		this.pheight = getHeight();
		x = 0;
		y = 0;
		is_first = false;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.pwidth = (int) (getWidth() / bigger);
		this.pheight = (int) (getHeight() / bigger);
		x = (int) ((getWidth() - (getWidth() / bigger)) / 2);
		y = (int) ((getHeight() - (getHeight() / bigger)) / 2);
		is_first = false;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//URL imageURL = this.getClass().getResource("/images/cursor2.png/");
		ImageIcon imageicon = new ImageIcon(MImages.getPath() + "/data/images/cursor2.png");
		Image image = imageicon.getImage();
		
		Cursor cursor=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(0,0),"myCursor");
		
		this.setCursor(cursor);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//URL imageURL = this.getClass().getResource("/images/cursor1.png/");
		ImageIcon imageicon = new ImageIcon(MImages.getPath() + "/data/images/cursor1.png");
		Image image = imageicon.getImage();
		
		Cursor cursor=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(0,0),"myCursor");
		
		this.setCursor(cursor);
		
		this.mouseExited(e);
	}
}
