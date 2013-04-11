package com.data;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;


import javax.swing.ImageIcon;


public class MImages {
	
	public static String getPath()
	{
		return System.getProperty("user.dir");
	}
	public static void loadImage()
	{
		String path = System.getProperty("user.dir") + "/data/images/";
		
		
		bird1_4 = (new ImageIcon(path + "birds/bird1_4.png")).getImage();
		bird4_4 = (new ImageIcon(path + "birds/bird4_4.png")).getImage();
		bird3_4 = (new ImageIcon(path + "birds/bird3_4.png")).getImage();

		pigex1 = (new ImageIcon(path + "birds/pigex1.png")).getImage();
		pigex2 = (new ImageIcon(path + "birds/pigex2.png")).getImage();
		pigex3 = (new ImageIcon(path + "birds/pigex3.png")).getImage();
		pigex4 = (new ImageIcon(path + "birds/pigex4.png")).getImage();
		
		
		bubble = (new ImageIcon(path + "bubble_1.png")).getImage();
		bubble_3 = (new ImageIcon(path + "bubble_3.png")).getImage();
		s500 = (new ImageIcon(path + "s500.png")).getImage();
		s5000 = (new ImageIcon(path + "s5000.png")).getImage();
		s10000 = (new ImageIcon(path + "s10000.png")).getImage();
		newScore = (new ImageIcon(path + "newscore.png")).getImage();
		
		BACKGROUNDS_LS_3 = (new ImageIcon(path + "BACKGROUNDS_LS_3.png")).getImage();
		BACKGROUNDS_LS_2 = (new ImageIcon(path + "BACKGROUNDS_LS_2.png")).getImage();
		
		ImageIcon imageicon = new ImageIcon(path + "cursor1.png");
		Image image = imageicon.getImage();
		
		
		cursor_n=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
		
		imageicon = new ImageIcon(path + "cursor2.png");
		image = imageicon.getImage();
		image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		
		cursor_p=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
	
		imageicon = new ImageIcon(path + "cursor3.png");
		image = imageicon.getImage();
		
		image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		cursor_d=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
	
		imageicon = new ImageIcon(path + "cursor4.png");
		image = imageicon.getImage();
		
		
		cursor_gm=Toolkit.getDefaultToolkit().createCustomCursor(
				image,new Point(1,1),"myCursor");
		
		
		
		backGroundImg = new Image[1];
		backSkyImg = new Image[1];
		backTreeImg = new Image[1];
		backGrassImg = new Image[1];
		
		backGroundImg[0] = (new ImageIcon(path + "level1/ground.png")).getImage();
		backSkyImg[0] = (new ImageIcon(path + "level1/sky.png")).getImage();
		backTreeImg[0] = (new ImageIcon(path + "level1/tree.png")).getImage();
		backGrassImg[0] = (new ImageIcon(path + "level1/grass.png")).getImage();
		dangong1 = (new ImageIcon(path + "dangong1.png")).getImage();
		dangong2 = (new ImageIcon(path + "dangong2.png")).getImage();
		

		
		wood = new Image[4][3];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
			{
				wood[i][j] = (new ImageIcon(path + "barrier/wood"+i+"_"+j+".png")).getImage();
			}
		
		woodex= new Image[3];
		for (int j = 0; j < 3; j++)
		{
			woodex[j] = (new ImageIcon(path + "barrier/woodex0_"+j+".png")).getImage();
		}
		
		glass = new Image[4][3];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
			{
				glass[i][j] = (new ImageIcon(path + "barrier/glass"+i+"_"+j+".png")).getImage();
			}
		
		glassex= new Image[3];
		for (int j = 0; j < 3; j++)
		{
			glassex[j] = (new ImageIcon(path + "barrier/glassex1_"+j+".png")).getImage();
		}
		
		stone = new Image[4][3];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
			{
				stone[i][j] = (new ImageIcon(path + "barrier/stone"+i+"_"+j+".png")).getImage();
			}
		
		stoneex= new Image[3];
		for (int j = 0; j < 3; j++)
		{
			stoneex[j] = (new ImageIcon(path + "barrier/stoneex1_"+j+".png")).getImage();
		}
		
		pig = new Image[3][2];
		for (int i = 0;i<3;i++)
		{
			for (int j = 0; j< 2; j++)
			{
				pig[i][j] = (new ImageIcon(path + "birds/pig"+(i+1)+"_"+(j+1)+".png")).getImage();
				
			}
		}
		bird = new Image[4][3];
		
		for (int i = 0;i<2;i++)
			for (int j = 0; j < 3; j++)
			{
				bird[i][j] = (new ImageIcon(path + "birds/bird"+(i+1)+"_"+(j+1)+".png")).getImage();
			}
		
		bird[2][0] = (new ImageIcon(path + "birds/bird3_1.png")).getImage();
		bird[2][1] = bird[2][0];
		bird[2][2] = bird[2][0];
		
		for (int i = 3;i < 4; i++)
			for (int j = 0; j < 3; j++)
			{
				bird[i][j] = (new ImageIcon(path + "birds/bird"+(i+1)+"_"+(j+1)+".png")).getImage();
			}
		
		
		
		birdex = new Image[4][3];
		
		for (int i = 0;i<4;i++)
			for (int j = 0; j < 3; j++)
			{
				birdex[i][j] = (new ImageIcon(path + "birds/birdex"+(i+1)+"_"+(j+1)+".png")).getImage();
			}
		
		inGameGround = new Image[1];
		for (int i = 0; i < 1; i++)
		{
			inGameGround[i] =  (new ImageIcon(path + "INGAME_THEME_GROUND_"+(i+1)+".png")).getImage();
			
		}
	}
	
	public static void loadLevel()
	{
		srcPath = getPath();
		String path = System.getProperty("user.dir") + "/data/level/";
		
		
		levelList = new LevelData[levelNum];
		for (int i = 0; i <levelNum; i++)
		{
			String filePath = path + "level" + (i+1) +".txt";
			try {
				FileInputStream is = new FileInputStream(filePath);
				InputStreamReader ir = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(ir);
				
				String str;
				String context[];
				str = reader.readLine();
				levelList[i] = new LevelData();
				levelList[i].level = Integer.parseInt(str); 				
				str = reader.readLine();
				context = str.split(" ");
				levelList[i].shootStartX = Float.parseFloat(context[0]);
				levelList[i].shootStartY = Float.parseFloat(context[1]);								
				str = reader.readLine();
				int num = Integer.parseInt(str);
				levelList[i].birdNum = num;
				levelList[i].birds = new float[num][3];
				
				for (int j = 0; j <num;j++)
				{
					str = reader.readLine();
					context = str.split(" ");
					levelList[i].birds[j][0] = Integer.parseInt(context[0]);
					levelList[i].birds[j][1] = Float.parseFloat(context[1]);
					levelList[i].birds[j][2] = Float.parseFloat(context[2]);
				}
				
				str = reader.readLine();
				num = Integer.parseInt(str);
				levelList[i].pigNum = num;
				levelList[i].pigs = new float[num][5];
				
				for (int j = 0; j <num;j++)
				{
					str = reader.readLine();
					context = str.split(" ");
					levelList[i].pigs[j][0] = Integer.parseInt(context[0]);
					levelList[i].pigs[j][1] = Float.parseFloat(context[1]);
					levelList[i].pigs[j][2] = Float.parseFloat(context[2]);
					levelList[i].pigs[j][3] = Float.parseFloat(context[3]);
					levelList[i].pigs[j][4] = Float.parseFloat(context[4]);
				}
				
				str = reader.readLine();
				num = Integer.parseInt(str);
				levelList[i].barrierNum = num;
				levelList[i].barriers = new float[num][7];
				
				for (int j = 0; j <num;j++)
				{
					str = reader.readLine();
					context = str.split(" ");
					levelList[i].barriers[j][0] = Integer.parseInt(context[0]);
					levelList[i].barriers[j][1] = Integer.parseInt(context[1]);
					levelList[i].barriers[j][2] = Float.parseFloat(context[2]);
					levelList[i].barriers[j][3] = Float.parseFloat(context[3]);
					levelList[i].barriers[j][4] = Float.parseFloat(context[4]);
					levelList[i].barriers[j][5] = Float.parseFloat(context[5]);
					levelList[i].barriers[j][6] = Float.parseFloat(context[6]);
				}
				
				str = reader.readLine();
				num = Integer.parseInt(str);
				levelList[i].groundNum = num;
				levelList[i].grounds = new float[num][7];
				
				for (int j = 0; j <num;j++)
				{
					str = reader.readLine();
					context = str.split(" ");
					
					levelList[i].grounds[j][0] = Float.parseFloat(context[0]);
					levelList[i].grounds[j][1] = Float.parseFloat(context[1]);
					levelList[i].grounds[j][2] = Float.parseFloat(context[2]);
					levelList[i].grounds[j][3] = Float.parseFloat(context[3]);
					levelList[i].grounds[j][4] = Float.parseFloat(context[4]);
					levelList[i].grounds[j][5] = Float.parseFloat(context[5]);
					levelList[i].grounds[j][6] = Float.parseFloat(context[6]);
				}
				
				reader.close();
				ir.close();
				//dis.close();				
				is.close();
				
				
				File filename = new File(filePath);
				RandomAccessFile rf = new RandomAccessFile(filename,"rw");
				rf.seek(rf.length() - 4);
				levelList[i].highScore = rf.readInt();
				//rf.seek(rf.length() - 4);
				//rf.writeInt(0);
				
				System.out.println(i + "  " + levelList[i].highScore);
				
				rf.close();
				//or.write(10000
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error: File doesn't exit");
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void updateHighScore(int level)
	{
		String filePath = srcPath + "/data/level/level" + level +".txt";
		
		File filename = new File(filePath);
		RandomAccessFile rf;
		try {
			rf = new RandomAccessFile(filename,"rw");
			rf.seek(rf.length() - 4);
			rf.writeInt(levelList[level-1].highScore);
			//rf.writeInt(11);
			System.out.println(levelList[level-1].highScore);			
			rf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//public static Image bird1_1;
	//public static Image bird1_2;
	//public static Image bird1_3;
	public static String srcPath;
	public static Image bird1_4;
	public static Image bird3_4;
	public static Image bird4_4;
	
	public static Image pigex1;
	public static Image pigex2;
	public static Image pigex3;
	public static Image pigex4;
	public static Image dangong1;
	public static Image dangong2;
	public static Image bubble;
	public static Image bubble_3;
	public static Image s500;
	public static Image s5000;
	public static Image s10000;
	public static Image newScore;
	
	public static Cursor cursor_n;
	public static Cursor cursor_d;
	public static Cursor cursor_p;
	public static Cursor cursor_gm;
	
	public static Image[] backGroundImg;
	public static Image[] backGrassImg;
	public static Image[] backSkyImg;
	public static Image[] backTreeImg;
	

	public static Image[][] wood;
	public static Image[] woodex;
	
	public static Image[][] glass;
	public static Image[] glassex;
	
	public static Image[][] stone;
	public static Image[] stoneex;
	
	public static Image[][] birdex;
	public static Image[][] bird;
	
	public static Image[][] pig;
	public static Image[] inGameGround;
	
	public static int iDelay = 16;
	public static float pixelNum = 25;
	
	public static int levelNum = 10;
	public static LevelData[] levelList;
	
	public static Image BACKGROUNDS_LS_3;
	public static Image BACKGROUNDS_LS_2;
}
