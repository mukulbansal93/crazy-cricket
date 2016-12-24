package com.brock.games.utils;

import org.springframework.context.ApplicationContext;

import com.brock.games.crazycricket.dao.ICountryDAO;
import com.brock.games.crazycricket.entity.Country;
import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;

/**
 * 
 * @author Mukul Bansal
 */
public class SaveByCountry implements Runnable
{
	ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
	ICountryDAO countryDAO = (ICountryDAO) appCtx.getBean("countryDAO");

	Game game;

	SaveByCountry(Game game)
	{
		this.game = game;
	}

	@Override
	public void run()
	{
		Country winner = countryDAO.find(game.getWinner().getCountry());
		
		if (winner != null)
		{
			winner.setWin(winner.getWin() + 1);
			countryDAO.save(winner);
		}
		else
		{
			Country newCountry = new Country();
			newCountry.setCountry(game.getWinner().getCountry());
			newCountry.setWin(1);
			newCountry.setLoss(0);
			countryDAO.save(newCountry);
		}

		Country loser = countryDAO.find(game.getLoser().getCountry());
		if (loser != null)
		{
			loser.setLoss(loser.getLoss() + 1);
			countryDAO.save(loser);
		}
		else
		{
			Country newCountry = new Country();
			newCountry.setCountry(game.getWinner().getCountry());
			newCountry.setLoss(1);
			newCountry.setWin(0);
			countryDAO.save(newCountry);
		}
	}
}
