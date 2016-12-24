package com.brock.games.utils;

import org.springframework.context.ApplicationContext;

import com.brock.games.crazycricket.constants.Constants;
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
	Long matchNumber;

	SaveByMatch(Long matchNumber,Game game)
	{
		this.game = game;
		this.matchNumber=matchNumber;
	}

	public void save()
	{
		Match match=new Match();
		match.setLosscountry(game.getLoser().getCountry());
		match.setLossPlayer(game.getLoser().getUserId());
		match.setWinCountry(game.getWinner().getCountry());
		match.setWinPlayer(game.getWinner().getUserId());
		match.setMatchDate(GeneralUtils.dateTimeConverter(Constants.DATEFORMAT, game.getGameDate()));
		matchDAO.save(matchNumber,match);
	}
}
