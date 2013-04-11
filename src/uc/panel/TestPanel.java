package uc.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;

public class TestPanel extends JPanel{
	
	int x=0;
	int y =0;
	double a=0;
	public TestPanel()
	{
		x=0;
	a=0;
		this.setBackground(Color.GREEN);
		Timer timer1 = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my(e);
			}
		});
		//timer1.start();
	}
	
	
	
	public void my(ActionEvent e) {
		// TODO Auto-generated method stub
		x+=1;
		a+=0.1;
		System.out.println(x);
		this.validate();
		this.repaint();
		
	}
	
	protected void paintComponent(Graphics g) 
	{
		this.setBackground(Color.GREEN);
		x = 100;
		g.drawLine(0, 150, 500, 150);
		//g.drawLine(150, 0, 150, 500);
		
		Graphics2D g2d = (Graphics2D)g;
		
		/*
		java.net.URL imageURL = getClass().getResource("/images/button5.png");
		Image image=null;
		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		g2d.translate(x+50, 150);
		
		g2d.rotate(-0.5);
		
		g2d.translate(-(x+50), -150);
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, 100, 100, 100);
	}
}
