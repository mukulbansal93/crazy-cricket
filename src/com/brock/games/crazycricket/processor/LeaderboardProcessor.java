package com.brock.games.crazycricket.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brock.games.crazycricket.dao.ICountryDAO;
import com.brock.games.crazycricket.dao.IMatchDAO;
import com.brock.games.crazycricket.dao.IPlayerDAO;
import com.brock.games.crazycricket.entity.Country;
import com.brock.games.crazycricket.entity.Player;
import com.brock.games.exception.ProcessorException;
import com.brock.games.utils.GeneralUtils;

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
	@Autowired
	ICountryDAO countryDAO;
	@Autowired
	IMatchDAO matchDAO;

	public JSONArray getLeaderboard() throws ProcessorException
	{
		JSONArray jsonArrayResponse = new JSONArray();

		try
		{
			Map<Object, Object> allPlayers = playerDAO.findAll();
			HashMap<String, Float> playerStats = new HashMap<>();
			for (Entry<Object, Object> entry : allPlayers.entrySet())
			{
				Player player = (Player) entry.getValue();
				float losingPercentage = (float) player.getWin()
						/ (player.getLoss() + player.getWin());
				playerStats.put(entry.getKey().toString(), losingPercentage);

				System.out.println(entry.getKey() + " : " + entry.getValue());
			}

			int playerRank = 1;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(playerStats).entrySet())
			{
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				Float winningPercentage = 1f - entry.getValue();
				jsonArray.put("Rank-" + playerRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
				if (lastWinningPercentage != winningPercentage)
					playerRank++;
				lastWinningPercentage = winningPercentage;
			}
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

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public JSONArray getNationalLeaderboard() throws ProcessorException
	{
		JSONArray jsonArrayResponse = new JSONArray();

		try
		{
			Map<Object, Object> allCountry = countryDAO.findAll();
			HashMap<String, Float> countryStats = new HashMap<>();
			for (Entry<Object, Object> entry : allCountry.entrySet())
			{
				Country country = (Country) entry.getValue();
				float losingPercentage = (float) country.getLoss()
						/ (country.getLoss() + country.getWin());
				countryStats.put(entry.getKey().toString(), losingPercentage);

				System.out.println(entry.getKey() + " : " + entry.getValue());
			}

			int playerRank = 1;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(countryStats).entrySet())
			{
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				Float winningPercentage = 1f - entry.getValue();
				jsonArray.put("Rank-" + playerRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
				if (lastWinningPercentage != winningPercentage)
					playerRank++;
				lastWinningPercentage = winningPercentage;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ProcessorException(e.getMessage());
		}
		return jsonArrayResponse;
	}

	public JSONArray getDurationalNationalLeaderboard(String start, String end)
			throws ProcessorException
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
