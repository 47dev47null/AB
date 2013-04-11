package com.data;

import java.util.Vector;

public class ParabolaPoints {

	public static int n =0;
	public int No;
	Vector<ParabolaPoint> points;
	
	public class ParabolaPoint{
		public ParabolaPoint(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
		float x;
		float y;	
	}
	public boolean big = false;
	public float bigx = 0;
	public float bigy = 0;
	public ParabolaPoints()
	{
		points = new Vector<ParabolaPoint>();
	}
	
	public void add(float x, float y)
	{
		ParabolaPoint p = new ParabolaPoint(x,y);
		points.add(p);
	}
	
	public int size()
	{
		return points.size();
	}
	
	public float getX(int index)
	{
		return points.elementAt(index).x;
	}
	
	public float getY(int index)
	{
		return points.elementAt(index).y;
	}
}
