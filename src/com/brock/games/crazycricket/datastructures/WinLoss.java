package com.brock.games.crazycricket.datastructures;

/**
 * 
 * @author Mukul Bansal
 */

public class WinLoss
{
	private static final long serialVersionUID = -8593244040042347412L;

	private int win;
	private int loss;
	private Long date;
	
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
	
	public Long getDate()
	{
		return date;
	}
	public void setDate(Long date)
	{
		this.date = date;
	}
	@Override
	public String toString()
	{
		return "WinLoss [win=" + win + ", loss=" + loss + ", date=" + date + "]";
	}
	
}
