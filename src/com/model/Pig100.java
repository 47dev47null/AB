package com.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.data.MImages;


public class Pig100 extends Pig{

	
	
	public Pig100(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
		
	}
	@Override
	public Image setImage()
	{

		return MImages.pig[2][0];
	}
	
	@Override
	protected void setPropery() {
		// TODO Auto-generated method stub
		density = 0.1f;
		friction = 0.9f;
		restitution = 0.4f;
		angularDamping = 0.8f;
		HP = 12;

	}
	
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		//System.out.println(MaxHP + "  " + HP+ "  " +value);
		HP = HP - value;
		
		if (HP < 0)
		{
			this.isDead = true;
		}
		if (HP > 0 && HP < stat1HP * MaxHP)
		{
			image =  MImages.pig[2][1];
		}
		//this.repaint();
		return false;
	}
	
	public boolean explode()
	{
		//this.getBody().getWorld().destroyBody(this.getBody());
		this.angle = 0;
		switch(count)
		{
			case 0:
				image = MImages.pigex1;
				iwidth =image.getWidth(this);
				iheight = image.getHeight(this);
				scoreImg = MImages.s5000;
				this.sPanelSize = 250;
				this.setBounds((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2)
						,(int)sPanelSize, (int)sPanelSize);
				
				//this.repaint();
				break;
			case 3:
				
				break;
			case 5:
				//this.getBody().setActive(false);
				image = MImages.pigex2;				
				//this.repaint();
				break;
			case 10:
				image = MImages.pigex3;
				
				//this.repaint();
				break;
			case 17:
				image = MImages.pigex4;				
				//this.repaint();
				break;
			case 30:
				return true;
				
		}
		
		count++;
		return false;
	}

}
