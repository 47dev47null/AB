package com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import org.jbox2d.dynamics.Body;

import com.control.GameRunning;
import com.data.MImages;

public class Barrier extends GameObj {

	public Barrier(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.setName("birrier");
		this.setType("barrier");
		
		//System.out.print(b)
	}
	Image imgBarrier[] = new Image[3];
	Image imgChip[] = new Image[3];
	
	public void setAsStone(int i)
	{
		density = 0.31f;
		this.image = MImages.stone[i][0];
		imgBarrier[0] = MImages.stone[i][0];
		imgBarrier[1] = MImages.stone[i][1];
		imgBarrier[2] = MImages.stone[i][2];
		
		imgChip[0] = MImages.stoneex[0];
		imgChip[1] = MImages.stoneex[1];
		imgChip[2] = MImages.stoneex[2];
		
		ATK = 1.33f;
		HP = 45;
		setInitHP();
		this.setName("stone");
		
	}
	protected void setPropery()
	{
		density = 0.1f;
		friction = 0.76f;
		restitution = 0f;
	}
	
	public void setAsWood(int i)
	{
		density = 0.07f;
		
		
		this.image = MImages.wood[i][0];
		imgBarrier[0] = MImages.wood[i][0];
		imgBarrier[1] = MImages.wood[i][1];
		imgBarrier[2] = MImages.wood[i][2];
		
		imgChip[0] = MImages.woodex[0];
		imgChip[1] = MImages.woodex[1];
		imgChip[2] = MImages.woodex[2];
		
		ATK = 0.58f;
		HP = 16;
		setInitHP();
		this.setName("wood");
		
	}
	public void setAsGlass(int i)
	{
		density = 0.04f;
		this.image = MImages.glass[i][0];
		imgBarrier[0] = MImages.glass[i][0];
		imgBarrier[1] = MImages.glass[i][1];
		imgBarrier[2] = MImages.glass[i][2];
		
		imgChip[0] = MImages.glassex[0];
		imgChip[1] = MImages.glassex[1];
		imgChip[2] = MImages.glassex[2];
		
		ATK = 0.38f;
		HP = 7.9f;
		setInitHP();
		this.setName("glass");
	}
	
	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		this.angle = 0;
		switch(count)
		{
			case 0:				
				createChip();
				image = null;
				this.sPanelSize = 250;
				this.setBounds((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2)
						,(int)sPanelSize, (int)sPanelSize);
				
				this.isExplode = true;
				//this.repaint();
				break;
			
			case 3:
				
				scoreImg = MImages.s500;
				//image = MImages.bird1_4;
				break;
			case 5:
				//this.getBody().setActive(false);
				break;
			case 30:
				return true;
				
		}
		
		count++;
		return false;
	}

	@Override
	protected Image setImage() {
		// TODO Auto-generated method stub
		return MImages.wood[0][0];
	}
	//0.6
	//0.2

	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		//System.out.println("barrier" + MaxHP + "  " + HP+ "  " +value);
		HP = HP - value;
		
		if (HP < 0)
		{
			this.isDead = true;
		}
		if (HP > 0 && HP < stat2HP * MaxHP)
		{
			image =  imgBarrier[2];
		}
		if (HP > stat2HP * MaxHP && HP < 0.8f * MaxHP)
		{
			image =  imgBarrier[1];
		}
		return false;
	}
	


	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public void createChip()
	{
		Random r = new Random();
		int num = r.nextInt(7)+4;
		for (int i =0;i < num;i++)
		{
			int k = r.nextInt(3);
			int x = r.nextInt(100) - 50 + (int)worldx;
			int y = r.nextInt(100) - 50 + (int)worldy;
			float vx = r.nextFloat() * 6 - 2f;
			float vy = r.nextFloat() * 6 - 2f;
			int a = r.nextInt(360);
			int av = r.nextInt(10) - 5;
			Chip cp = new Chip(imgChip[k].getWidth(this),imgChip[k].getHeight(this));
			cp.image = imgChip[k];
			cp.game = this.game;
			
			cp.angularVelocity = av;
			cp.setWorldPoint(this.worldx - this.sPointx, this.worldy - this.sPointy
					, x, y, (float)Math.toRadians(a));
			Body b = GameRunning.createPolygonBody(this.getBody().getWorld(), cp, vx, vy);
			this.game.bodyList.add(b);
			this.game.jPanel.add(cp);
		}
	}
	
}
