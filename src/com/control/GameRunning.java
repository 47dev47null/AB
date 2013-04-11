package com.control;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;


import javax.swing.Timer;


import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


import uc.panel.Game_UC;


import com.data.LevelData;
import com.data.MImages;
import com.data.ParabolaPoints;
import com.model.*;


public class GameRunning {
	
	Timer timer1;
	Image backImg = null;

	Image backTreeImg = null;
	Image backSkyImg = null;
	Image backGrassImg = null;
	
	Image dangong1 = null;
	Image dangong2 = null;
	

	
	public GameRunning(Game_UC jp)
	{		
		
		//System.out.println(worldSizex);
		
		jPanel = jp;
		timer1 = new Timer(this.iDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		bodyList = new Vector<Body>();
		
		
		jPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				backGroundMove(e);
			}
		});
		
		
		jPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				uMousePressed(e);
			}
		});
		
		
		
		
		
	}
	
	int hightScore;
	public int currentScore = 0;

	//世界的大小
	public static int worldSizex = 2560;
	public static int worldSizey = 1440;
	//屏幕的坐标
	int showPointx = 0;
	int showPointy = 0;
	//显示的大小
	int ishowWidth = 1280;
	int ishowHeight = 720;
	public Game_UC jPanel;
	World world;
	//1显示像素代表showRange个实际。
	public float showRange = 1;
	//1m代表pixelNum个像素
	public float pixelNum = MImages.pixelNum= 25;
	boolean isGameFinish = false;
	boolean isWin = false;
	boolean isAllBirdDead = false;
	boolean isAllPigDead = false;
	boolean isGameBegin = false;
	int winCount = 0;
	int endCount = 0;
	int endBirdNum = 0;
	boolean isGameEnd = false;
	
	public int level;
	int danx = 300;
	int dany = 310 + 720;
	
	int shootStartx = 355;
	int shootStarty = 350 + 720;
	
	// v = len * tk / pnum
	// sg/2 = h v*v
	//900g=v*v
	//g = k*k
	
	int maxLen = 120;
	
	int startPigPoint = 1300;
	
	int iDelay =MImages.iDelay = 16;
	float tanhuanK = 5.7f;	
	float g = 10 * tanhuanK * tanhuanK / pixelNum /pixelNum * 25 * 0.9f;
	Vec2 gravity = new Vec2(0.0f, g);
	
	//boolean isDragged = false;
	//还未上弹弓的小鸟
	Vector<Bird> iBirdList = new Vector<Bird>();
	public Vector<Body> bodyList;
	Vector<Body> toDestory = new Vector<Body>();

	//要被发射的小鸟
	Bird iBird = null;
	//最近被发射的小鸟
	Bird iBirdShoot = null;
	//发射后的计时
	int shootCount;
	
	boolean isDrawLa = false;
	
	Vector<ParabolaPoints> iParaPoints = new Vector<ParabolaPoints>();
	
	public void setShowp(int x, int y){
		
		showPointx = x;
		showPointy = y;
		if (showPointx < 0)
			showPointx = 0;
		
		if (showPointx > GameRunning.worldSizex - this.ishowWidth)
		{
			showPointx = GameRunning.worldSizex - this.ishowWidth;
		}
		for (int i = 0; i< iBirdList.size();++i){		
			GameObj g = (iBirdList.elementAt(i));
			
			g.setWorldPoint(showPointx,showPointy,g.worldx, g.worldy, g.angle);
		}
		
		for (int i = 0; i< bodyList.size();++i){
			Body box = bodyList.elementAt(i);
			GameObj g = (GameObj) box.getUserData();
			
			//Vec2 p = box.getPosition();
			g.setWorldPoint(showPointx,showPointy,g.worldx, g.worldy, g.angle);
		}
	}
	
	
	
	public void createWorld(int level) {
		this.backImg = com.data.MImages.backGroundImg[level];		
		backTreeImg = com.data.MImages.backTreeImg[level];				
		backSkyImg = com.data.MImages.backSkyImg[level];
		backGrassImg = com.data.MImages.backGrassImg[level];
		
		dangong1 = com.data.MImages.dangong1;	
		dangong2 = com.data.MImages.dangong2;
		currentScore = 0;
		
		for (int i = 0; i< iBirdList.size();++i){		
			this.jPanel.remove(iBirdList.elementAt(i));
		}
		iBirdList.removeAllElements();
		this.iParaPoints.removeAllElements();
		for (int i = 0; i< bodyList.size();++i){
			Body box = bodyList.elementAt(i);
			
			GameObj obj = (GameObj) box.getUserData();
			this.jPanel.remove(obj);

		}
		bodyList.removeAllElements();
		
		
		worldSizex = backImg.getWidth(jPanel);
		worldSizey = backImg.getHeight(jPanel);
		this.setShowp(1300, 650);
		
		boolean doSleep = true;
		world = new World(gravity, doSleep);
		this.world.setContactListener(new MyContactListener());
		this.world.setContactFilter(new MyContactFilter());
		Ground gd = new Ground(worldSizex * 2 + 100, 20);
		//地面y坐标1250
		gd.setWorldPoint(showPointx, showPointy, 0, 1260, 0);
		gd.game = this;
		GameRunning.createGround(world,gd);
		
		
	
	}
	
	
	
	public Bird getCurBird()
	{
		return iBird;
	}
	//开始后计时
	int beginCount;
	public void createModels(int level)
	{
		/*
		 * 文件
		 * level1
		 * shootStart
		 * n *鸟的个数
		 * 1 width height  *鸟
		 * *1正常，2加速，3分裂
		 * n 猪个数
		 * 1 width height pointX pointY
		 * * 1小猪  2猪80 3 猪100
		 * n 障碍物的个数 *没有用0表示
		 * a b width height pointX pointY angel*
		 * * a障碍物种类 1木头 ，2石头，3玻璃。b每个种类中的分类
		 * n Ground的个数
		 * width height pointX pointY
		 */
		//com.data.MImages.loadLevel(this.getClass().getResource("").getPath());
		beginCount = 0;
		this.currentScore = 0;
		this.level = level;
		isGameFinish = false;
		isWin = false;
		isAllBirdDead = false;
		isAllPigDead = false;
		isGameBegin = false;
		isGameEnd = false;
		endCount = 0;
		endBirdNum = 0;
		
		
		//danx = shootstartx - 55
		//dany = shootstarty - 40
		
		LevelData ld = MImages.levelList[level -1];
		this.shootStartx =(int) ld.shootStartX;
		this.shootStarty =(int) ld.shootStartY;
		danx = shootStartx - 55;
		dany = shootStarty - 40;
		//danx = 300;
		//dany = 1030;
		this.hightScore = ld.highScore;
		jPanel.high_jLabel.setText("" + this.hightScore);
		for (int i = 0; i < ld.pigNum;i++)
		{
			Pig pg = null;
			if (ld.pigs[i][0] == 1)//小猪50
			{
				pg = new Pig(50,50);
			}
			if (ld.pigs[i][0] == 2)//小猪80
			{
				pg = new Pig80(80,80);
			}
			if (ld.pigs[i][0] == 3)
			{
				pg = new Pig100(100,100);
			}
			pg.game = this;
			pg.setWorldPoint(showPointx, showPointy, ld.pigs[i][3], ld.pigs[i][4], 0);
			jPanel.add(pg);
			Body body = GameRunning.createCircleBody(world,pg,0,0);
			bodyList.add(body);
			if (i == 0)
			{
				this.startPigPoint = (int) (pg.worldx - 600);
			}
		}
		for (int i = 0; i < ld.barrierNum; i++)
		{
			Barrier ba = null;
			if (ld.barriers[i][0] == 1)//木头
			{
				ba = new Barrier(ld.barriers[i][2],ld.barriers[i][3]);
				ba.setAsWood((int)ld.barriers[i][1]);	
			}
			if (ld.barriers[i][0] == 2)//石头
			{
				ba = new Barrier(ld.barriers[i][2],ld.barriers[i][3]);
				ba.setAsStone((int)ld.barriers[i][1]);
			}
			if (ld.barriers[i][0] == 3)//玻璃
			{
				ba = new Barrier(ld.barriers[i][2],ld.barriers[i][3]);
				ba.setAsGlass((int)ld.barriers[i][1]);
				
			}
			ba.game = this;		
			ba.setWorldPoint(showPointx, showPointy, ld.barriers[i][4]
			                  , ld.barriers[i][5],(float)Math.toRadians(ld.barriers[i][6]));
			
			jPanel.add(ba);
			Body b2 = GameRunning.createPolygonBody(world,ba,0,0);
			bodyList.add(b2);
			//System.out.println(ba.worldy);
			//System.out.println(b2.getPosition().y);
		}
		for (int i = 0; i< ld.birdNum;i++)
		{
			Bird bird = null;
			if (ld.birds[i][0] == 1)
			{
				bird = new Bird(50,50);
			}
			if (ld.birds[i][0] == 2)
			{
				bird = new BirdSpeed(70,70);
			}
			if (ld.birds[i][0] == 3)
			{
				bird = new BirdSplit(35,35);
			}
			if (ld.birds[i][0] == 4)
			{
				bird = new BirdSpin(100,100);
			}
			
			if (i == 0)
			{
				iBird = bird;
				iBird.setWorldPoint(showPointx,showPointy,ld.shootStartX,ld.shootStartY,0);
				iBird.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						objPressed(e);
					}
				});
				iBird.isDragged = false;
			}
			else
			{
				bird.setWorldPoint(showPointx,showPointy,ld.shootStartX - i*60,ld.shootStartY + 180 - (bird.iheight / 2),0);
			}
			bird.game = this;
			iBirdList.add(bird);
			jPanel.add(bird);
		}
		
		for (int i = 0; i < ld.groundNum; i++)
		{
			Ground gd = new Ground(ld.grounds[i][2], ld.grounds[i][3]);
			gd.setInit((int)ld.grounds[i][0],(int)ld.grounds[i][1]);
			//地面y坐标1250
			gd.setWorldPoint(showPointx, showPointy, ld.grounds[i][4], ld.grounds[i][5], 0);
			gd.game = this;
			jPanel.add(gd);
			Body b3 = GameRunning.createGround(world,gd);
			bodyList.add(b3);
		}
		
		this.setShowp(this.startPigPoint, 650);
	}
	
	public static Body createGround(World iworld, GameObj obj)
	{
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(obj.worldx / MImages.pixelNum, obj.worldy / MImages.pixelNum);

		Body groundBody = iworld.createBody(groundBodyDef);
		//top
		PolygonShape groundBoxDef = new PolygonShape();
		float lx = obj.iwidth / 2.0f / MImages.pixelNum;
		float ly = obj.iheight / 2.0f /MImages.pixelNum;
		//System.out.println("ground " + lx + "   "+ ly);
		groundBoxDef.setAsBox(lx, ly);
		//groundBoxDef.density = 0.0f;
		groundBody.createFixture(groundBoxDef, 0);
		groundBody.setUserData(obj);
		obj.setBody(groundBody);
		
		return groundBody;
	}
	
	public static Body createCircleBody(World iworld, GameObj obj, float Vx, float Vy)
	{
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyType.DYNAMIC;
		bodyDef2.angle = obj.angle;
		bodyDef2.angularDamping = obj.angularDamping;
		bodyDef2.position.set(obj.worldx / MImages.pixelNum, obj.worldy / MImages.pixelNum);
		bodyDef2.linearVelocity.set(Vx,Vy);
		bodyDef2.angularVelocity = obj.angularVelocity;
		Body box3 = iworld.createBody(bodyDef2);
		
		CircleShape cs = new CircleShape();
		
		float r = obj.iwidth / 2.0f / MImages.pixelNum;
		//System.out.println("szie " + r);
		cs.m_radius = (r);
		FixtureDef fix2 = new FixtureDef();
		fix2.shape = cs;
		fix2.density = obj.density;
		fix2.friction = obj.friction;
		fix2.restitution = obj.restitution;
		box3.createFixture(fix2);
		
		box3.setUserData(obj);
		obj.setBody(box3);
		//bodyList.add(box3);
		//jPanel.add(obj);
		
		return box3;
	}

	public static Body createPolygonBody(World iworld, GameObj pg, float Vx, float Vy)
	{
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyType.DYNAMIC;
		bodyDef2.position.set(pg.worldx / MImages.pixelNum, pg.worldy / MImages.pixelNum);
		bodyDef2.angle = pg.angle;
		bodyDef2.angularDamping = pg.angularDamping;
		bodyDef2.angularVelocity = pg.angularVelocity;
		bodyDef2.linearVelocity.set(Vx,Vy);
		
		Body box2 = iworld.createBody(bodyDef2);

		PolygonShape boxDef2 = new PolygonShape();
		float lx = pg.iwidth / 2.0f / MImages.pixelNum;
		float ly = pg.iheight / 2.0f /MImages.pixelNum;
		//System.out.println("ground " + lx + "   "+ ly);
		
		boxDef2.setAsBox(lx, ly);

		FixtureDef fix2 = new FixtureDef();
		fix2.shape = boxDef2;
		fix2.density = pg.density;
		fix2.friction = pg.friction;
		fix2.restitution = pg.restitution;
		box2.createFixture(fix2);

		box2.setUserData(pg);
		pg.setBody(box2);
		/*
		 * b2Vec2 vertices[3];
		vertices[0].Set(0.0f, 0.0f);
		vertices[1].Set(1.0f, 0.0f);
		vertices[2].Set(0.0f, 1.0f);
		int32 count = 3;
		b2PolygonShape polygon;
		polygon.Set(vertices, count);
		 */
		
		//jPanel.add(pg);
		//bodyList.add(box2);
		
		return box2;
	}
	
	public void start()
	{
		//this.setShowp(0, 720);
		timer1.start();
		//this.isDragged = false;
	}
	public void stop()
	{
		timer1.stop();
	}
	//游戏继续
	public void gameContinue()
	{
		//this.setShowp(0, 720);
		timer1.restart();
		//this.isDragged = false;
	}
	public void init()
	{
		this.destory();
		this.createModels(this.level);
		this.setShowp(this.startPigPoint, 650);
		timer1.start();
	}
	public void destory()
	{
		timer1.stop();
		
		for (int i = 0; i< iBirdList.size();++i){		
			this.jPanel.remove(iBirdList.elementAt(i));
		}
		iBirdList.removeAllElements();
		this.iParaPoints.removeAllElements();
		for (int i = 0; i< bodyList.size();++i){
			Body box = bodyList.elementAt(i);
			
			GameObj obj = (GameObj) box.getUserData();
			this.jPanel.remove(obj);
			
			world.destroyBody(box);
		}
		bodyList.removeAllElements();
	}
	
	//是否有新鸟要被发射
	private void isNeedBird()
	{
		this.isAllBirdDead = true;
		
		if (iBird.isDead && iBirdList.size() == 0)
		{
			this.isGameFinish = true;
			this.isAllBirdDead = true;
		}
		if (iBirdList.size() == 0)
		{
			isAllBirdDead = false;
			return;
		}
		isAllBirdDead = false;
		if (iBird.isNextShoot())
		{

			iBird = iBirdList.elementAt(0);
			iBird.setWorldPoint(showPointx,showPointy,shootStartx,shootStarty,0);
			iBird.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					objPressed(e);
				}
			});
			iBird.isDragged = false;

		}
	}
	
	private void gameBeginScreenMove()
	{
		if (beginCount <= (500 / iDelay))
		{
			this.beginCount++;
		}
		if (this.showPointx != 0 && beginCount < (2000 /iDelay)&& beginCount > (500/iDelay))
		{
			this.beginCount++;
			this.setShowp(this.showPointx - 13, this.showPointy);
			//jPanel.repaint();
		}
		if (this.showPointx == 0)
		{
			beginCount = (2000 /iDelay);
		}
		
	}
	
	private void birdShootingScreenMove()
	{
		if (iBirdShoot != null && this.shootCount < (2500 /iDelay))
		{
			
			this.setShowp((int)(iBirdShoot.worldx - 500), showPointy);
			
			//jPanel.validate();
			//jPanel.repaint();
			
			shootCount++;
		}
	}
	//处理鸟
	private void runGetBird(Bird bird, Vec2 p)
	{
		if (bird.isNeedChip)
		{
			bird.createChip();
			bird.isNeedChip = false;
		}
		if (bird.getName() == "spinbird" && bird.isUseSkill)
		{
			 BirdSpin sbird = (BirdSpin) bird;
			 sbird.usingSkill();
		}
		
		bird.addPoint(p.x * pixelNum, p.y * pixelNum);
		if (bird.hitCount == 0 && bird.isFirstHit)
		{
			bird.isFirstHit = false;
			for (int i = 0;i<this.iParaPoints.size();i++)
			{
				ParabolaPoints point  = iParaPoints.elementAt(i);
				if (point.No != ParabolaPoints.n)
				{
					this.iParaPoints.remove(i);
					break;
				}
			}
		}
		//g.isDead = true;
		//shootBird.remove(0);
	}
	private void gameObjDead(GameObj g)
	{
		if (g.isDead())
		{						
			if (g.explode())
			{
				if (g.getType() == "pig")
					this.currentScore += 5000;
				if (g.getType() == "barrier")
					this.currentScore += 500;
				
				world.destroyBody(g.getBody());
				this.toDestory.add(g.getBody());
				jPanel.remove(g);
				//jPanel.repaint();
			}
		}
	}
	
	
	private void run()
	{
		gameBeginScreenMove();
		boolean allBodySleep = true;
		boolean allPigDead = true;
		
		float timeStep = 1f / 60f;
		int velocityIterations = 6;

		int positionIterations = 2;

		world.step(timeStep, velocityIterations, positionIterations);
		world.clearForces();
		this.toDestory.removeAllElements();
		isNeedBird();
		
		for (int i = 0; i< bodyList.size();++i){
			Body box = bodyList.elementAt(i);
			GameObj g = (GameObj) box.getUserData();
			
			Vec2 p = box.getPosition();
			
			if (g.getType() == "bird")
			{
				runGetBird((Bird)g, p);
			}
			
			g.setWorldPoint(showPointx, showPointy, p.x * pixelNum, p.y * pixelNum, box.getAngle());
			
			gameObjDead(g);
			
			if (g.getType() == "bird" && !g.isDead)
			{
				isAllBirdDead = false;
			}
			
			if (g.getType() == "pig" && !g.isDead)
			{
				allPigDead = false;
			}
			
			if (box.isAwake())
			{
				allBodySleep = false;
			}
		}
		this.isAllPigDead = allPigDead;
		for (int i = 0; i< toDestory.size();++i){
			bodyList.remove(toDestory.elementAt(i));
		}
		birdShootingScreenMove();
		gameEnd(allBodySleep);
		
		
		
		if (this.currentScore > this.hightScore)
		{
			this.hightScore = this.currentScore;
			jPanel.high_jLabel.setText(this.hightScore + "");
		}
		jPanel.score_jLabel.setText(this.currentScore + "");
		
		//printAllBody();
		//System.out.printf("speed, %4.1f %4.1f\n", b.m_linearVelocity.x, b.m_linearVelocity.y);
		jPanel.repaint();
	}
	private void printAllBody()
	{
		for (int i = 0;i < bodyList.size();i++)	
		{
			Body b = bodyList.elementAt(i);
			GameObj g = (GameObj) b.getUserData();
			if (g.getType() == "barrier" || g.getType() == "pig")
			{
				System.out.print(g.getName());
				System.out.printf(" %4.1f %4.1f %4.1f\n", g.worldx, g.worldy, Math.toDegrees(g.angle));
			}
		}
		System.out.println("end\n");
	}
	private void gameEnd(boolean allBodySleep)
	{
		if (allBodySleep || isGameFinish)
		{
			if (isAllPigDead)
			{
				this.isGameFinish = true;
				this.isWin = true;
			}
			else
			{
				if (this.isAllBirdDead)
				{
					this.isGameFinish = true;
					this.isWin = false;
				}
			}
		
		}
		
		if (isGameFinish)
		{
			gameEndScreenMove();
			if (endCount == 125)
			{
				if (this.endBirdNum == iBirdList.size())
				{
					isGameEnd = true;
				}
				else
				{
					Bird b = iBirdList.elementAt(endBirdNum);
					if (b.showScore())
					{
						this.currentScore+=10000;
						endBirdNum++;
					}
				}
				
			}
			if (isGameEnd)
			{
				jPanel.gameFinish(isWin, currentScore);
			}
			//
		}
	}
	
	private void gameEndScreenMove()
	{
		if (endCount <= (500 / iDelay))
		{
			this.endCount++;
		}
		if (this.showPointx != 0 && endCount < 125&& endCount > (500/iDelay))
		{
			this.endCount++;
			this.setShowp(this.showPointx - 13, this.showPointy);
			//jPanel.repaint();
		}
		if (this.showPointx == 0)
		{
			endCount = 125;
		}
		
	}
	
	private void objDragged(MouseEvent e)
	{
		Bird bi = (Bird) e.getSource();
		if (!bi.isDragged){
			GameObj bird =	(GameObj)e.getSource();
		
			//屏幕显示坐标
			float sx = bird.getX() +  e.getX();
			float sy = bird.getY() +  e.getY();
			
			//g.drawImage(null, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)
			//屏幕显示发射点坐标
			float x = shootStartx - showPointx;
			float y = shootStarty - showPointy;
			//距离
			float cx = sx - x;
			float cy = sy - y;
			
			if (cx * cx + cy * cy > maxLen * maxLen)
			{
				double o = Math.atan(cy / cx);
				
				float sign = Math.abs(cx)/cx;
				if (cx == 0)
					sign = -1;				
				sx = (float) (x + Math.abs(maxLen * Math.cos(o)) * sign);
				if (cy == 0)
					sign = -1;
				else
					sign = Math.abs(cy)/cy;
				sy = (float) (y + Math.abs(maxLen * Math.sin(o)) * sign);
				
			}
			
			
			//Graphics2D g2d = (Graphics2D)jPanel.getGraphics();
			//jPanel.repaint();
			//g2d.setStroke(new BasicStroke(13.0f));
			//g2d.setColor(new Color(48, 23, 8));
			//g2d.drawLine(this.shootStartx - this.showPointx, shootStarty - showPointy, (int)sx, (int)sy);
			//g2d.dispose();
			
			bird.setLocatPoint(showPointx, showPointy,sx, sy, 0);
		}
	}
	

	int pressedX;
	int pressedY;
	
	public void backGroundMove(MouseEvent e) {
		showPointx -= 0.1 * (e.getX() - pressedX);

		if (showPointx < 0) {
			showPointx = 0;

		}
		if (showPointx > worldSizex - ishowWidth * showRange) {
			showPointx = (int) (worldSizex - ishowWidth * showRange);
		}

		this.setShowp(showPointx, showPointy);

	
		//jPanel.validate();
		//jPanel.repaint();
	}
	
	public void uMousePressed(MouseEvent e) {
		pressedX = e.getPoint().x;
		pressedY = e.getPoint().y;
		
		this.useSkill(e);

	}

	private void objPressed(MouseEvent e){
		Bird bi = (Bird) e.getSource();
		if (!bi.isDragged){
			
			isDrawLa = true;
			GameObj bird =	(GameObj)e.getSource();
			
			int sx = bird.getX() +  e.getX();
			int sy = bird.getY() +  e.getY();
			
			bird.setLocatPoint(showPointx, showPointy, sx, sy, 0);
			bird.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					objDragged(e);
				}
			
			});
			bird.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					objReleased(e);
				}
			});
			//jPanel.repaint();
		}
	}
	
	
	private void objReleased(MouseEvent e){

		Bird bi = (Bird) e.getSource();
		if (!bi.isDragged){
			float vx = (shootStartx -  iBird.worldx)* tanhuanK;
			float vy = (shootStarty -  iBird.worldy) * tanhuanK;
		
			Body b = GameRunning.createCircleBody(world,iBird, vx/this.pixelNum, vy/this.pixelNum);
			bodyList.add(b);
			//System.out.printf("startv, %4.1f %4.1f\n", vx, vy);
			//System.out.printf("startv, %4.1f %4.1f\n", vx / pixelNum, vy / pixelNum);
			//System.out.printf("pingp %4.1f %4.1f %4.1f",pigg.getPosition().x,pigg.getPosition().y, pigg.getFixtureList().m_shape.m_radius);
			//System.out.printf("pingp %4.1f %4.1f %4.1f",gg.getPosition().x,gg.getPosition().y, gg.getFixtureList().m_shape.m_radius);
			
			//System.out.printf("world, %4.1f %4.1f\n", iBird.worldx, iBird.worldy);
			iBirdList.remove(0);
			bi.isDragged = true;
			bi.shoot();
			iBirdShoot = bi;
			this.shootCount = 0;
			
			ParabolaPoints p = new ParabolaPoints();
			p.No = (ParabolaPoints.n+1) % 2;
			ParabolaPoints.n = p.No;
			bi.points = p;
			this.iParaPoints.add(p);
			isDrawLa = false;
			//bi.getBody().setActive(false);
		}
		
	}
	
	protected void useSkill(MouseEvent e)
	{
		if (this.iBirdShoot != null && iBirdShoot.isShoot && !iBirdShoot.isUseSkill && iBirdShoot.hitCount ==1)
		{
			iBirdShoot.useSkill(bodyList,jPanel);
		}
	}
	
	public void panitBefore(Graphics g)
	{
		
		g.drawImage(backSkyImg, 0, 0, jPanel.getWidth(), jPanel.getHeight(), showPointx, showPointy,
				(int) (showPointx + ishowWidth * showRange), (int) (showPointy + ishowHeight
						* showRange), jPanel);
		
		g.drawImage(backTreeImg, 0, 0, jPanel.getWidth(), jPanel.getHeight(), showPointx, showPointy,
				(int) (showPointx + ishowWidth * showRange), (int) (showPointy + ishowHeight
						* showRange), jPanel);
		
		
		
		
		//g.drawImage(backSkyImg, 0, 0, jPanel.getWidth(), jPanel.getHeight(), showPointx, showPointy, sx2, sy2, observer)
		g.drawImage(dangong1, danx - showPointx, dany - showPointy, 100, 220, jPanel);
		if (this.isDrawLa)
		{
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(new Color(48,23,8));
			g2d.setStroke(new BasicStroke(5));
			g2d.drawLine(shootStartx - showPointx + 15, shootStarty - showPointy, (int)(iBird.worldx - showPointx), (int)iBird.worldy - showPointy);
		}
		for (int i = 0;i<this.iParaPoints.size();i++)
		{
			ParabolaPoints p  = iParaPoints.elementAt(i);
			for (int j = 0; j < p.size();j++)
			{
				g.drawImage(MImages.bubble, (int)(p.getX(j) - showPointx),  (int)(p.getY(j) - showPointy),jPanel);
			}
			if (p.big)
			{
				g.drawImage(MImages.bird3_4, (int)(p.bigx - showPointx),  (int)(p.bigy - showPointy),jPanel);
				
			}
		}
		

	}
	
	public void panitAfter(Graphics g)
	{
		if (this.isDrawLa)
		{			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(new Color(48,23,8));
			g2d.setStroke(new BasicStroke(5));
			g2d.drawLine(shootStartx - showPointx - 15, shootStarty - showPointy, (int)iBird.worldx - showPointx, (int)iBird.worldy - showPointy);
		}
		
		g.drawImage(backImg, 0, 0, jPanel.getWidth(), jPanel.getHeight(), showPointx, showPointy,
				(int) (showPointx + ishowWidth * showRange), (int) (showPointy + ishowHeight
						* showRange), jPanel);
		g.drawImage(dangong2, danx - showPointx, dany - showPointy, 100, 220, jPanel);
		//g.setFont(TextString.myFont);
		//g.drawString("" + this.currentScore, 1000, 200);
	}
	
	
}
