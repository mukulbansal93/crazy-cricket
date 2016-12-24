package com.brock.games.crazycricket.entity;

import java.io.Serializable;

/**
 * 
 * @author Mukul Bansal
 */

public class Match implements Serializable
{

	private static final long serialVersionUID = -8593244040042347412L;

	private String winPlayer;
	private String winCountry;
	private String lossPlayer;
	private String losscountry;
	private Long matchDate;
	public String getWinPlayer()
	{
		return winPlayer;
	}
	public void setWinPlayer(String winPlayer)
	{
		this.winPlayer = winPlayer;
	}
	public String getWinCountry()
	{
		return winCountry;
	}
	public void setWinCountry(String winCountry)
	{
		this.winCountry = winCountry;
	}
	public String getLossPlayer()
	{
		return lossPlayer;
	}
	public void setLossPlayer(String lossPlayer)
	{
		this.lossPlayer = lossPlayer;
	}
	public String getLosscountry()
	{
		return losscountry;
	}
	public void setLosscountry(String losscountry)
	{
		this.losscountry = losscountry;
	}
	public Long getMatchDate()
	{
		return matchDate;
	}
	public void setMatchDate(Long matchDate)
	{
		this.matchDate = matchDate;
	}
	@Override
	public String toString()
	{
		return "Match [winPlayer=" + winPlayer + ", winCountry=" + winCountry + ", lossPlayer="
				+ lossPlayer + ", losscountry=" + losscountry + ", matchDate=" + matchDate + "]";
	}
}
