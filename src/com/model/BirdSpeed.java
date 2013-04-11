package com.model;

import java.awt.Image;
import java.util.Vector;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.data.MImages;

public class BirdSpeed extends Bird
{

	public BirdSpeed(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.setName("speedbird");
	}
	
	protected void setPropery() {
		// TODO Auto-generated method stub
		density = 0.1f;
		friction = 0.9f;
		restitution = 0.35f;
		angularDamping = 0.7f;
	
		
	}
	@Override
	public Image setImage()
	{

		return MImages.bird[1][0];
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
				image = MImages.bird1_4.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				//this.repaint();
				break;
			case 10:
				image = MImages.bird1_4;
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
			image = MImages.bird[1][2];
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
		
		image = MImages.bird[1][1];
		Vec2 v =this.getBody().getLinearVelocity().mul(2f);
		this.getBody().setLinearVelocity(v);
		this.isUseSkill = true;
		ATK = 0.95f;
	}
	
	protected Chip newChip(int k)
	{
		int x =MImages.birdex[1][k].getWidth(this);
		int y =MImages.birdex[1][k].getHeight(this);
		Chip cp = new Chip(x,y);
		cp.image = MImages.birdex[1][k];
		return cp;
	}

}
