package com.brock.games.crazycricket.processor;

import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brock.games.crazycricket.dao.IPlayerDAO;
import com.brock.games.exception.ProcessorException;

/**
 * 
 * @author Mukul Bansal
 *
 */

@Service
public class LeaderboardProcessor
{

	@Autowired
	IPlayerDAO playerDAO;
	
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

	public JSONArray getDurationalLeaderboard(String start, String end) throws ProcessorException
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

	public JSONArray getNationalLeaderboard() throws ProcessorException
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

	public JSONArray getDurationalNationalLeaderboard(String start, String end) throws ProcessorException
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

}
