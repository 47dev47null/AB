package com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jbox2d.dynamics.Body;

import com.control.GameRunning;
import com.data.MImages;

import uc.panel.MyPanel;

public abstract class GameObj extends MyPanel{



	protected float sPanelSize;
	
	public GameRunning game = null;
	Body iBody = null;
	
	public Image image;
	//Image bl;
	
	protected Image scoreImg = null;
	
	public GameObj(float width,float height)
	{
		
		this.setShowSize(width, height);
		//java.net.URL imageURL = getClass().getResource("/images/bl.png/");
		//ImageIcon icon = new ImageIcon(imageURL);
		//bl = icon.getImage();
		image = this.setImage();
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				uMousePressed(e);
			}
		});
	}
	public float sPointx;
	public float sPointy;
	public float angle;
	public float density = 1;
	public float friction = 0;
	public float restitution = 1;
	public float angularDamping = 0;
	public float iwidth;
	public float iheight;
	public float worldx;
	public float worldy;
	
	public String iType;
	public boolean isDead = false;
	
	public float angularVelocity = 0;
	//HP = 8 * iwidth * iheight * density * hardCount;
	public float MaxHP;
	public float ATK;
	public float DEF;
	public float HP;
	
	public boolean isExplode = false;
	//±¬Õ¨¼ÆÊ±
	protected int count = 0;
	public void setShowSize(float width, float height)
	{
		count = 0;
		iwidth = width;
		iheight = height;
		sPanelSize = (float) ((Math.sqrt(iwidth * iwidth+ iheight * iheight)));
		//sPanelSize = 250;
		ATK = 1;
		HP = 10;
		setPropery();
		this.setSize((int)sPanelSize, (int)sPanelSize);
		setInitHP();
	}
	
	protected abstract void setPropery();
	
	public abstract boolean hit(float value);
	
	protected float stat1HP = 0.6f;
	protected float stat2HP = 0.2f;
	public void setInitHP()
	{
		
		//HP = 10 * hardCount;
		MaxHP = HP;
	}
	
	public String getType()
	{
		return iType;
	}
	public void setType(String type)
	{
		iType = type;
	}
	protected void cursorPressed(MouseEvent e) {

		this.setCursor(cursor_d);
		
	}
	
	protected void cursorReleased(MouseEvent e) {

		this.setCursor(cursor_gm);
		
	}
	
	protected abstract Image setImage();

	public void paint(Graphics g) 
	{		
		Graphics2D g2d = (Graphics2D)g;	
		g2d.translate(sPanelSize/2, sPanelSize/2);		
		g2d.rotate(angle);		
		g2d.translate(-sPanelSize/2, -sPanelSize/2);		
		if (image != null)
		{
			g2d.drawImage(image, (int)((sPanelSize-iwidth)/2), (int)((sPanelSize-iheight)/2), (int)iwidth, (int)iheight, this);					
		}
		
		if (scoreImg != null)
		{
			g2d.rotate(-angle);
			g2d.drawImage(scoreImg, (int)((sPanelSize - scoreImg.getWidth(this))/2),50,this);
		}
	}
	
	public void setWorldPoint(float ix,float iy,float wx, float wy, float angle)
	{
		this.worldx = wx;
		this.worldy = wy;
		
		this.sPointx = wx - ix;
		this.sPointy = wy - iy;
		this.angle = angle;
		this.setLocation((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2));
	}
	
	public void setLocatPoint(float ix,float iy,float sx, float sy, float angle)
	{
		this.worldx = ix + sx;
		this.worldy = iy + sy;
		
		this.sPointx = sx;
		this.sPointy = sy;
		this.angle = angle;
		this.setLocation((int)(sPointx - sPanelSize/2), (int)(sPointy - sPanelSize/2));
	}
	
	public void setBody(Body body)
	{
		this.iBody = body;
	}
	
	public Body getBody()
	{
		return this.iBody;
	}
	
	public abstract boolean explode();
	
	public abstract boolean isDead();
	
	public void createChip()
	{
		
	}
	
	private void uMousePressed(MouseEvent e) {
		this.game.uMousePressed(e);
	}
}
