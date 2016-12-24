package com.brock.games.crazycricket.entity;

import java.io.Serializable;

/**
 * 
 * @author Mukul Bansal
 */

public class Player implements Serializable
{
	private static final long serialVersionUID = -8593244040042347412L;

	private String player;
	private int win;
	private int loss;
	public String getPlayer()
	{
		return player;
	}
	public void setPlayer(String player)
	{
		this.player = player;
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
		return "Player [player=" + player + ", win=" + win + ", loss=" + loss + "]";
	}
	
	
}
