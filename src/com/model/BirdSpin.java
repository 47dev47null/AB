package com.model;

import java.awt.Image;
import java.util.Vector;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import com.control.GameRunning;
import com.data.MImages;

public class BirdSpin extends Bird{

	public BirdSpin(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.setName("spinbird");
	}
	
	protected void setPropery() {
		// TODO Auto-generated method stub
		density = 0.1f;
		friction = 0.97f;
		restitution = 0.2f;
		angularDamping = 0.1f;
		this.angularVelocity = 10;
		ATK = 1.2f;
		//100,100
		
	}
	@Override
	public Image setImage()
	{

		return MImages.bird[3][0];
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
				image = MImages.bird4_4;
				//this.repaint();
				break;
			case 10:
				image = MImages.bird4_4;
				break;
			case 22:
				return true;
				
		}
		
		count++;
		return false;
	}
	@Override
	public void shoot()
	{
		this.isShoot = true;
		//image = MImages.bird[1][1];
		//this.repaint();
	}
	
	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		if (hitCount == 1)
		{
			image = MImages.bird[3][2];
			hitCount = 0;
		}
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
		isNeedChip = true;
		
		this.points.big = true;
		this.points.bigx = worldx - 10;
		this.points.bigy = worldy - 10;
		
		image = MImages.bird[3][1];
		Body b = this.getBody();
		b.applyLinearImpulse(new Vec2(-5,-1), new Vec2(this.worldx, worldy));
		b.setAngularVelocity(10);
		this.isUseSkill = true;
	}
	int skillcount = 0;
	public void usingSkill()
	{
		
		if (skillcount < 23)
		{
			if (skillcount % 2 ==0)
			{
				Body b = this.getBody();
				b.applyLinearImpulse(new Vec2(-6.4f,-1), new Vec2(this.worldx, worldy));
				b.setAngularVelocity(10);
			}
			skillcount++;
		}
		
	}
	
	protected Chip newChip(int k)
	{
		int x =MImages.birdex[3][k].getWidth(this);
		int y =MImages.birdex[3][k].getHeight(this);
		Chip cp = new Chip(x,y);
		cp.image = MImages.birdex[3][k];
		return cp;
	}
	
	public boolean isDead()
	{
		Vec2 v = this.getBody().getLinearVelocity();
		
		if (v.length() < 0.34f)
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

}
