package com.brock.games.crazycricket.processor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brock.games.crazycricket.constants.Constants;
import com.brock.games.crazycricket.dao.ICountryDAO;
import com.brock.games.crazycricket.dao.IMatchDAO;
import com.brock.games.crazycricket.dao.IPlayerDAO;
import com.brock.games.crazycricket.entity.Country;
import com.brock.games.crazycricket.entity.Match;
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

			int playerRank = 0;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(playerStats).entrySet())
			{
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				Float winningPercentage = 1f - entry.getValue();
				if (lastWinningPercentage != winningPercentage)
					playerRank++;
				jsonArray.put("Rank-" + playerRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
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

	public JSONArray getDurationalLeaderboard(Long start, Long end) throws ProcessorException
	{
		JSONArray jsonArrayResponse = new JSONArray();
		try
		{
			Map<Object, Object> allMatches = matchDAO.findAll();
			System.out.println(allMatches);
			HashMap<String, Float> playerStats = getPlayerStats(allMatches, start, end);
			System.out.println(playerStats);
			int playerRank = 0;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(playerStats).entrySet())
			{
				System.out.println(entry.getKey());
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				float winningPercentage = 1f - entry.getValue();
				if (lastWinningPercentage != winningPercentage)
					playerRank++;
				jsonArray.put("Rank-" + playerRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
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

	private HashMap<String, Float> getPlayerStats(Map<Object, Object> allPlayers, Long start,
			Long end)
	{
		HashMap<String, HashMap<String, Integer>> playerStats = new LinkedHashMap<>();
		for (Entry<Object, Object> entry : allPlayers.entrySet())
		{
			Match match = (Match) entry.getValue();
			Long dateTime = match.getMatchDate();

			System.out.println(dateTime);
			if (dateTime >= start && dateTime <= end)
			{
				String winningPlayer = match.getWinPlayer();
				if (!playerStats.containsKey(winningPlayer))
				{
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", 1);
					innerHM.put("LOSS", 0);
					playerStats.put(winningPlayer, innerHM);
				}
				else
				{
					int wins = playerStats.get(winningPlayer).get("WIN");
					wins++;
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", wins);
					innerHM.put("LOSS", playerStats.get(winningPlayer).get("LOSS"));
					playerStats.put(winningPlayer, innerHM);
				}

				String losingPlayer = match.getLossPlayer();
				if (!playerStats.containsKey(losingPlayer))
				{
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", 0);
					innerHM.put("LOSS", 1);
					playerStats.put(losingPlayer, innerHM);
				}
				else
				{
					int loss = playerStats.get(losingPlayer).get("LOSS");
					loss++;
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", playerStats.get(losingPlayer).get("WIN"));
					innerHM.put("LOSS", loss);
					playerStats.put(losingPlayer, innerHM);
				}
			}
		}
		System.out.println(playerStats);
		HashMap<String, Float> newPlayerStats = new HashMap<>();
		for (Entry<String, HashMap<String, Integer>> entry : playerStats.entrySet())
		{
			float losingPercentage = (float) entry.getValue().get("LOSS")
					/ (entry.getValue().get("WIN") + entry.getValue().get("LOSS"));
			newPlayerStats.put(entry.getKey(), losingPercentage);
		}
		return newPlayerStats;
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

			int countryRank = 0;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(countryStats).entrySet())
			{
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				Float winningPercentage = 1f - entry.getValue();
				if (lastWinningPercentage != winningPercentage)
					countryRank++;
				jsonArray.put("Rank-" + countryRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
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

	public JSONArray getDurationalNationalLeaderboard(Long start, Long end)
			throws ProcessorException
	{
		JSONArray jsonArrayResponse = new JSONArray();
		try
		{
			Map<Object, Object> allMatches = matchDAO.findAll();
			System.out.println(allMatches);
			HashMap<String, Float> countryStats = getCountryStats(allMatches, start, end);
			System.out.println(countryStats);
			int countryRank = 0;
			float lastWinningPercentage = 0f;

			for (Entry<String, Float> entry : GeneralUtils.sortHashMap(countryStats).entrySet())
			{
				System.out.println(entry.getKey());
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(entry.getKey());
				float winningPercentage = 1f - entry.getValue();
				if (lastWinningPercentage != winningPercentage)
					countryRank++;
				jsonArray.put("Rank-" + countryRank + "/Winning Percentage-" + winningPercentage);
				jsonArrayResponse.put(jsonArray);
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

	private HashMap<String, Float> getCountryStats(Map<Object, Object> allMatches, Long start,
			Long end)
	{
		HashMap<String, HashMap<String, Integer>> countyrStats = new LinkedHashMap<>();
		for (Entry<Object, Object> entry : allMatches.entrySet())
		{
			Match match = (Match) entry.getValue();
			Long dateTime = match.getMatchDate();

			System.out.println(dateTime);
			if (dateTime >= start && dateTime <= end)
			{
				String winningCountry = match.getWinCountry();
				if (!countyrStats.containsKey(winningCountry))
				{
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", 1);
					innerHM.put("LOSS", 0);
					countyrStats.put(winningCountry, innerHM);
				}
				else
				{
					int wins = countyrStats.get(winningCountry).get("WIN");
					wins++;
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", wins);
					innerHM.put("LOSS", countyrStats.get(winningCountry).get("LOSS"));
					countyrStats.put(winningCountry, innerHM);
				}

				String losingCountry = match.getLosscountry();
				if (!countyrStats.containsKey(losingCountry))
				{
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", 0);
					innerHM.put("LOSS", 1);
					countyrStats.put(losingCountry, innerHM);
				}
				else
				{
					int loss = countyrStats.get(losingCountry).get("LOSS");
					loss++;
					HashMap<String, Integer> innerHM = new LinkedHashMap<>();
					innerHM.put("WIN", countyrStats.get(losingCountry).get("WIN"));
					innerHM.put("LOSS", loss);
					countyrStats.put(losingCountry, innerHM);
				}
			}
		}
		System.out.println(countyrStats);
		HashMap<String, Float> newPlayerStats = new HashMap<>();
		for (Entry<String, HashMap<String, Integer>> entry : countyrStats.entrySet())
		{
			float losingPercentage = (float) entry.getValue().get("LOSS")
					/ (entry.getValue().get("WIN") + entry.getValue().get("LOSS"));
			newPlayerStats.put(entry.getKey(), losingPercentage);
		}
		return newPlayerStats;
	}

}
