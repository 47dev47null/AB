package com.model;



import java.awt.Image;
import java.util.Vector;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import com.control.GameRunning;
import com.data.MImages;
public class BirdSplit extends Bird{

		public BirdSplit(float width, float height) {
			super(width, height);
			// TODO Auto-generated constructor stub
			this.setName("splitbird");
		}
		
		protected void setPropery() {
			// TODO Auto-generated method stub
			density = 0.1f;
			friction = 0.9f;
			restitution = 0.35f;
			angularDamping = 0.7f;
			//35,35
			
		}
		@Override
		public Image setImage()
		{

			return MImages.bird[2][0];
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
					image = MImages.bird3_4;
					//this.repaint();
					break;
				case 10:
					image = MImages.bird3_4;
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
				//image = MImages.bird[1][2];
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
			
			World world = this.getBody().getWorld();
			
			float x = this.worldx;
			float y = this.worldy;
			Vec2 v = this.getBody().getLinearVelocity();
			float vx = this.getBody().getLinearVelocity().x;
			float vy = this.getBody().getLinearVelocity().y;
			
			double o = Math.atan(vy / vx);
			
			Bird bird = new BirdSplit(35,35);
			bird.setWorldPoint(x-this.sPointx,y-this.sPointx,x,y,0);
			double o2 = Math.toRadians(15);
			float vx2 = (float)(v.length() * Math.cos(o+ o2));
			float vy2 = (float)(v.length() * Math.sin(o+ o2));
			Body b = GameRunning.createCircleBody(world,bird, vx2, vy2);
			jPanel.add(bird);
			iBodyList.add(b);
			bird.points = this.points;
			bird.shoot();
			bird.isUseSkill = true;
			bird.game = game;
			
			bird = new BirdSplit(35,35);
			bird.setWorldPoint(x-this.sPointx,y-this.sPointx,x,y,0);
			vx2 = (float)(v.length() * Math.cos(o- o2));
			vy2 = (float)(v.length() * Math.sin(o- o2));
			b = GameRunning.createCircleBody(world,bird, vx2, vy2);
			jPanel.add(bird);
			iBodyList.add(b);
			bird.points = this.points;
			bird.shoot();
			bird.isUseSkill = true;
			bird.game = game;
			
			this.isUseSkill = true;
		}

		protected Chip newChip(int k)
		{
			int x =MImages.birdex[2][k].getWidth(this);
			int y =MImages.birdex[2][k].getHeight(this);
			Chip cp = new Chip(x,y);
			cp.image = MImages.birdex[2][k];
			return cp;
		}

}
