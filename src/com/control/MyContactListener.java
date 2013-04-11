package com.control;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;


import com.model.GameObj;

public class MyContactListener implements ContactListener
{

	@Override
	public void beginContact(Contact arg0) {
		// TODO Auto-generated method stub
		
			
		
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
			
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		Body bodyA = arg0.getFixtureA().getBody();
		Body bodyB = arg0.getFixtureB().getBody();
		GameObj objA = (GameObj) bodyA.getUserData();
		GameObj objB = (GameObj) bodyB.getUserData();
		float value = 0;
		WorldManifold wm = new WorldManifold();
		
		arg0.getWorldManifold(wm);
		Vec2 point = wm.points[0];
		Vec2 vA = bodyA.getLinearVelocityFromWorldPoint(point);
		Vec2 vB = bodyB.getLinearVelocityFromWorldPoint(point);
		Vec2 v = vA.sub(vB);
		//力方向的单位向量
		Vec2 normal = wm.normal.mul(1 / wm.normal.length());
		//相对速度在力方向上的投影
		value = Math.abs(Vec2.dot(v, normal));
		if (value < 0.7)
			value = 0;
		
		//System.out.println("vlaue" + value);
		if (objA.getType() == "bird")
		{
			objA.game.isGameBegin = true;
		}
		if (objB.getType() == "bird")
		{
			objA.game.isGameBegin = true;
		}
		value = value * 0.87f;
		
		GameObj atker = null, defer = null;
		//速度大的为攻击一方，速度小的为防守方
		if (vA.length() > vB.length())
		{
			value = value * objA.ATK;
			atker = objA;
			defer = objB;		
		}
		else
		{
			atker = objB;
			defer = objA;
			value = value * objB.ATK;
		}
		//if (value > 1)
		//System.out.printf("%4.1f\n",value);
		if (objA.getType() != "ground" && objB.getType() != "ground" && objA.game.isGameBegin)
			objA.game.currentScore += 10 * value;
		
		if (objA.getType() == "pig")
		{
			//System.out.printf("v%4.1f,%4.1f n%4.1f,%4.1f  %4.1f\n", v.x,v.y,normal.x,normal.y,value);
		}
		//防守方受全额伤害
		//攻击者受0.1伤害，除非攻击者为猪
		if (defer.getType() != "ground")
		{
			//攻击者为障碍物，防守者为障碍物时，防守者减伤
			if (atker.getType() == "barrier" && defer.getType() == "barrier")
				defer.hit(value * 0.7f);
			else
				defer.hit(value);
		}
		if (atker.getType() != "ground")
		{
			if (atker.getType() != "pig")
				atker.hit(value * 0.1f);
			else
				atker.hit(value);
		}

	
	}
	
}
