package com.brock.games.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;

/**
 * 
 * @author Mukul Bansal
 */

@Component
public class Redis
{
	public void save(Game game)
	{
		try
		{
			ExecutorService executor = Executors.newFixedThreadPool(3);
			executor.submit(new SaveByPlayer(game));
			executor.submit(new SaveByCountry(game));
			executor.submit(new SaveByMatch(game));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
