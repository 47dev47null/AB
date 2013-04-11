package com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.data.MImages;

public class Ground extends GameObj{

	public Ground(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.setName("ground");
		this.setType("ground");
		
		//this.setSize((int)width, (int)height);
		
	}
	int imgWidth = 256;
	int imgHeight = 256;
	
	int pos;//位于哪个角
	//1左上 ，2右上，3右下，4左下
	protected void setPropery()
	{
		
	}
	
	public void setInit(int i, int pos)
	{
		this.setName("grounds");
		this.image = MImages.inGameGround[i];
		this.pos = pos;
		
		this.sPanelSize = iwidth;
		if (iheight > iwidth)
			this.sPanelSize = iheight;
		this.setSize((int)sPanelSize, (int)sPanelSize);
		
	}
	public boolean explode()
	{
		return false;
	}
	@Override
	protected Image setImage() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void paint(Graphics g) 
	{		

		int x0,y0;
		x0 = (int)((sPanelSize-iwidth)/2);
		y0 = (int)((sPanelSize-iheight)/2);
		
		int sx1 = 0, sy1 = 0, sx2 = 256, sy2 = 256;
		if (pos == 1)
		{
			sx2 = (int) this.iwidth;
			sy2 = (int) this.iheight;
		}
		if (pos == 2)
		{
			sx1 = (int) (256 - this.iwidth);
			sy2 = (int) this.iheight;
		}
		if (pos == 3)
		{
			sx1 = (int) (256 - this.iwidth);
			sy1 = (int) (256 - this.iheight);
		}
		if (pos == 4)
		{
			sy1 = (int) (256 - this.iheight);
			sx2 = (int) this.iwidth;
		}
		g.drawImage(image, x0, y0 
				, (int)(iwidth + x0), (int)(iheight + y0)
				, sx1, sy1, sx2, sy2,  this);					
		

	}

}
