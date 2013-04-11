package uc.button;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import resource.Images;
import resource.TextString;

public class LevelBtn extends MyButton{

	int levelnum;
	
	public LevelBtn(int i) {
		super(Images.BTN_LEVEL1);
		// TODO Auto-generated constructor stub
		levelnum = i;
		this.setName(i+"");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(TextString.myFont);
		g.drawString(levelnum+"", 25, 58);
	}

}
