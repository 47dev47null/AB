package com.control;

import org.jbox2d.callbacks.ContactFilter;
import org.jbox2d.dynamics.Fixture;

import com.model.GameObj;

public class MyContactFilter extends ContactFilter{

	public boolean shouldCollide(Fixture fix1, Fixture fix2)
	{
		GameObj objA = (GameObj) fix1.getBody().getUserData();
		GameObj objB = (GameObj) fix2.getBody().getUserData();
		
		if (objA.getType() == "bird" && objB.getType() == "bird")
		{
			return false;
		}
		
		if (objA.getType() == "chip" || objB.getType() == "chip")
		{
			return false;
		}
		return true;
	}
}
