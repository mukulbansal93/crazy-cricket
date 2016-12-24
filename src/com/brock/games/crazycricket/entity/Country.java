package com.brock.games.crazycricket.entity;

import java.io.Serializable;

/**
 * 
 * @author Mukul Bansal
 */

public class Country implements Serializable
{
	private static final long serialVersionUID = -8593244040042347412L;

	private String country;
	private int win;
	private int loss;
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	public int getWin()
	{
		return win;
	}
	public void setWin(int win)
	{
		this.win = win;
	}
	public int getLoss()
	{
		return loss;
	}
	public void setLoss(int loss)
	{
		this.loss = loss;
	}
	@Override
	public String toString()
	{
		return "Country [country=" + country + ", win=" + win + ", loss=" + loss + "]";
	}	
	
}
