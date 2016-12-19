package com.brock.games.crazycricket.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brock.games.crazycricket.processor.LeaderboardProcessor;

/**
 * 
 * @author Mukul Bansal
 *
 */

@RestController
public class LeaderboardController
{
	@Autowired
	LeaderboardProcessor leaderboardProcessor;

	@RequestMapping(value = "/api/leaderboard", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getDateRangeLeaderboard(@RequestParam(required=false, value ="start") String start,
			@RequestParam(required=false, value ="end") String end)
	{

		try
		{
			if (start == null || end == null)
				;
			else
				;
			
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JSONObject result = new JSONObject().put("success", true).put("msg", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(result.toString());
		}
	}

	@RequestMapping(value = "/api/national_leaderboard", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getDateRangeNationalLeaderboard(
			@RequestParam(required=false, value ="start") String start, @RequestParam(required=false, value ="end") String end)
	{

		try
		{
			if (start == null || end == null)
				;
			else
				;

			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JSONObject result = new JSONObject().put("success", true).put("msg", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(result.toString());
		}
	}
}
