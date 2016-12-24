package com.brock.games.utils;

import org.springframework.stereotype.Component;

import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;

/**
 * 
 * @author Mukul Bansal
 */

@Component
public class Redis
{
	Long matchNumber=0L;
	public synchronized void save(Game game)
	{
		try
		{
			matchNumber++;
			new SaveByPlayer(game).save();
			new SaveByCountry(game).save();
			new SaveByMatch(matchNumber,game).save();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
