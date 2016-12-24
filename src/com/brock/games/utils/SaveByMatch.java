package com.brock.games.utils;

import org.springframework.context.ApplicationContext;

import com.brock.games.crazycricket.dao.IMatchDAO;
import com.brock.games.crazycricket.entity.Match;
import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;

/**
 * 
 * @author Mukul Bansal
 */
public class SaveByMatch
{

	ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
	IMatchDAO matchDAO = (IMatchDAO) appCtx.getBean("matchDAO");

	Game game;

	SaveByMatch(Game game)
	{
		this.game = game;
	}

	public void save()
	{
		Match match=new Match();
		match.setLosscountry(game.getLoser().getCountry());
		match.setLossPlayer(game.getLoser().getUserId());
		match.setLosscountry(game.getWinner().getCountry());
		match.setLossPlayer(game.getWinner().getUserId());
		match.setMatchDate(game.getGameDate());
		matchDAO.save(match);
	}
}
