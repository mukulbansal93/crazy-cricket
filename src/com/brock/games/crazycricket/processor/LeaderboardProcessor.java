package com.brock.games.crazycricket.processor;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.brock.games.exception.ProcessorException;

/**
 * 
 * @author Mukul Bansal
 *
 */

@Service
public class LeaderboardProcessor
{

	public JSONArray getLeaderboard() throws ProcessorException
	{
		JSONArray jsonArrayResponse = new JSONArray();

		try
		{

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ProcessorException(e.getMessage());
		}
		return jsonArrayResponse;
	}

	public JSONArray getDurationalLeaderboard(String start, String end)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
