package com.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.data.MImages;

public class Pig extends GameObj{

	
	//protected Image[] images;
	//protected int imgIndex = 0;
	
	public Pig(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub

		
		
		this.setName("pig");
		this.setType("pig");
		
		
	
	}
	@Override
	protected void setPropery() {
		// TODO Auto-generated method stub
		density = 0.1f;
		friction = 0.9f;
		restitution = 0.4f;
		angularDamping = 0.8f;
		HP = 10;

	}
	public Image setImage()
	{
		return MImages.pig[0][0];
	}
	protected int count = 0;
	public boolean explode()
	{
		//this.getBody().getWorld().destroyBody(this.getBody());
		this.angle = 0;
		switch(count)
		{
			case 0:
				
				image = MImages.pigex1;
				iwidth = 80;
				iheight = 80;
				this.sPanelSize = 250;
				this.setBounds((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2)
						,(int)sPanelSize, (int)sPanelSize);
				
				//this.repaint();
				break;
			case 3:
				//this.getBody().setActive(false);
				break;
			case 5:
				image = MImages.pigex2;
				scoreImg = MImages.s5000;
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
			case 22:
				return true;
				
		}
		
		count++;
		return false;
	}
	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		//this.repaint();
		//System.out.println(HP);
		HP = HP - value;
		
		if (HP < 0)
		{
			this.isDead = true;
		}
		if (HP > 0 && HP < stat1HP * MaxHP)
		{
			image =  MImages.pig[0][1];
		}
		
		return false;
	}
	
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}


}
