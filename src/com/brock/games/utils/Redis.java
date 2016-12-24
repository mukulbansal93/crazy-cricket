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
	
	public synchronized void save(Game game)
	{
		try
		{
			new SaveByPlayer(game).save();
			new SaveByCountry(game).save();
			new SaveByMatch(game).save();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
