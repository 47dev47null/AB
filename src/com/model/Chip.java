package com.model;

import java.awt.Image;

public class Chip extends GameObj{

	public Chip(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		this.setName("chip");
		this.setType("chip");
		this.isDead = true;
	}

	int count = 0;
	
	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		if (count > 45)
			return true;
		
		count++;
		return false;
	}

	@Override
	public boolean hit(float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return this.isDead;
	}

	@Override
	protected Image setImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setPropery() {
		// TODO Auto-generated method stub
		this.sPanelSize = 56;
		
	}

}
