package com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.control.GameRunning;
import com.data.MImages;
import com.data.ParabolaPoints;


public class Bird extends GameObj{


	public boolean isDragged = true;
	public boolean isShoot = false;
	public int rushCount = 0;
	int shootCount = 0;
	public boolean isUseSkill = false;
	public boolean isFirstHit = true;
	
	public ParabolaPoints points;
	
	public Bird(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
		
		this.setName("bird");
		this.setType("bird");
	}
	@Override
	protected void setPropery() {
		// TODO Auto-generated method stub
		density = 0.1f;
		friction = 0.9f;
		restitution = 0.35f;
		angularDamping = 0.7f;
		ATK = 1.0f;
		
	}
	@Override
	public Image setImage()
	{

		return MImages.bird[0][0];
	}
	
	public void createChip()
	{
		Random r = new Random();
		int num = r.nextInt(3)+3;
		for (int i =0;i < num;i++)
		{
			int k = r.nextInt(3);
			int x = r.nextInt(80) - 40 + (int)worldx;
			int y = r.nextInt(80) - 40 + (int)worldy;
			float vx = r.nextFloat() * 6 - 2.5f;
			float vy = r.nextFloat() * 6 - 2.5f;
			int a = r.nextInt(360);
			int av = r.nextInt(10) - 5;
			Chip cp = newChip(k);
			cp.game = this.game;
			
			cp.angularVelocity = av;
			cp.setWorldPoint(this.worldx - this.sPointx, this.worldy - this.sPointy
					, x, y, (float)Math.toRadians(a));
			Body b = GameRunning.createPolygonBody(this.getBody().getWorld(), cp, vx, vy);
			this.game.bodyList.add(b);
			this.game.jPanel.add(cp);
			//b.setActive(false);
		}
		
		for (int i = 0; i < 4;i++)
		{

			int x = r.nextInt(70) - 35 + (int)worldx;
			int y = r.nextInt(70) - 35 + (int)worldy;

			Chip cp = new Chip(25,25);
			cp.game = this.game;
			cp.image = MImages.bubble_3;
			float vx = r.nextFloat() * 6 - 2.5f;
			float vy = r.nextFloat() * 6 - 2.5f;
			cp.setWorldPoint(this.worldx - this.sPointx, this.worldy - this.sPointy
					, x, y, 0);
			Body b = GameRunning.createPolygonBody(this.getBody().getWorld(), cp, vx, vy);
			this.game.bodyList.add(b);
			
			this.game.jPanel.add(cp);
		}
	}
	protected Chip newChip(int k)
	{
		int x =MImages.birdex[0][k].getWidth(this);
		int y =MImages.birdex[0][k].getHeight(this);
		Chip cp = new Chip(x,y);
		cp.image = MImages.birdex[0][k];
		return cp;
	}
	@Override
	public boolean explode()
	{
		this.angle = 0;
		switch(count)
		{
			case 0:
				this.getBody().setActive(false);
				createChip();
				//this.drawChip(im, MImages.bird1_4);
				image = MImages.bird1_4;
				
				this.isExplode = true;
				//this.repaint();
				break;
			case 1:
				this.isExplode = false;
				break;
			case 10:
				//image = MImages.bird1_4;
				break;
			case 22:
				return true;
				
		}
		
		count++;
		return false;
	}
	
	public boolean showScore()
	{
		this.angle = 0;
		switch(count)
		{
			
			case 1:
				this.sPanelSize = 250;
				
				this.setBounds((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2)
						,(int)sPanelSize, (int)sPanelSize);
				
				scoreImg = MImages.s10000;
				break;
			case 10:
				//image = MImages.bird1_4;
				break;
			case 30:
				scoreImg = null;
				return true;
				
		}
		
		count++;
		return false;
	}
	
	public void shoot()
	{
		this.isShoot = true;
		image = MImages.bird[0][1];
		//this.repaint();
	}
	public boolean isNextShoot()
	{
		if (isShoot)
		{
			if (shootCount > 92)
			{
				return true;
			}
			shootCount++;
		}
		return false;
	}
	public int hitCount = 1;
	public boolean isNeedChip = false;
	
	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		if (hitCount == 1)
		{
			image = MImages.bird[0][2];
			hitCount = 0;
		}
		//this.createChip();
		if (value > 5)
		{
			isNeedChip = true;
			//this.createChip();
		}
		//this.repaint();
		return false;
	}
	
	public void useSkill(Vector<Body> iBodyList, JPanel jPanel)
	{
		
		//this.getBody().setBullet(true);
		//this.getBody().setLinearVelocity(new Vec2(1000,0));
		this.isUseSkill = true;
	}
	

	

	
	public boolean isDead()
	{
		Vec2 v = this.getBody().getLinearVelocity();
		
		if (v.length() < 0.2f)
		{
			this.isDead = true;
			return true;
		}
		if (!this.getBody().isAwake() 
				|| this.worldx < -50 || this.worldx > (GameRunning.worldSizex + 50))
		{
			this.isDead = true;
			return true;
		}
		
		return false;
	}
	
	int pointCount = 0;
	public void addPoint(float x, float y)
	{
		if (this.hitCount == 0)
		{
			return;
		}
		if (pointCount == 2)
		{
			this.points.add(x,y);
			pointCount = 0;
			return;
		}
		pointCount++;
	}
}
