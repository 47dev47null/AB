package uc.panel;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import form.Menu_Form;

public abstract class MyPanel extends JPanel{

	public Cursor cursor_n;
	public Cursor cursor_p;
	public Cursor cursor_d;
	public Cursor cursor_gm;
	
	public MyPanel()
	{
		
		this.addMyListener();
		cursor_n = com.data.MImages.cursor_n;
		cursor_p = com.data.MImages.cursor_p;
		cursor_d = com.data.MImages.cursor_d;
		cursor_gm = com.data.MImages.cursor_gm;
		/*
		URL imageURL = this.getClass().getResource("/images/cursor1.png/");
		ImageIcon imageicon = new ImageIcon(imageURL);
		Image image = imageicon.getImage();
		
		
		cursor_n=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
		imageURL = this.getClass().getResource("/images/cursor2.png/");
		imageicon = new ImageIcon(imageURL);
		image = imageicon.getImage();
		image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		
		cursor_p=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
		imageURL = this.getClass().getResource("/images/cursor3.png/");
		imageicon = new ImageIcon(imageURL);
		image = imageicon.getImage();
		
		image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		cursor_d=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
		imageURL = this.getClass().getResource("/images/cursor4.png/");
		imageicon = new ImageIcon(imageURL);
		image = imageicon.getImage();
		
		
		cursor_gm=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		*/
		
		
		
	}
	public Menu_Form getMainForm()
	{
		Container c = this.getParent();
		
		while (c.getName() != "Main" && c != null)
		{
			c = c.getParent();
		}
		
		return (Menu_Form) c;
	}
	protected void addMyListener()
	{
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				cursorPressed(evt);
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				cursorReleased(evt);
			}
		});
		
	}
	
	protected void cursorPressed(MouseEvent e)
	{
		this.setCursor(cursor_p);
	}
	
	protected void cursorReleased(MouseEvent e)
	{
				
		this.setCursor(cursor_n);
	}
}
