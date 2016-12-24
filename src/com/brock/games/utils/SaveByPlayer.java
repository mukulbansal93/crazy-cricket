package com.brock.games.utils;

import org.springframework.context.ApplicationContext;

import com.brock.games.crazycricket.dao.IPlayerDAO;
import com.brock.games.crazycricket.entity.Player;
import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;

/**
 * 
 * @author Mukul Bansal
 */
public class SaveByPlayer implements Runnable
{
	ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
	IPlayerDAO playerDAO = (IPlayerDAO) appCtx.getBean("playerDAO");

	Game game;

	SaveByPlayer(Game game)
	{
		this.game = game;
	}

	@Override
	public void run()
	{
		Player winner = playerDAO.find(game.getWinner().getUserId());
		
		if (winner != null)
		{
			winner.setWin(winner.getWin() + 1);
			playerDAO.save(winner);
		}
		else
		{
			Player newPlayer = new Player();
			newPlayer.setPlayer(game.getWinner().getUserId());
			newPlayer.setWin(1);
			newPlayer.setLoss(0);
			playerDAO.save(newPlayer);
		}

		Player loser = playerDAO.find(game.getLoser().getUserId());
		if (loser != null)
		{
			loser.setLoss(loser.getLoss() + 1);
			playerDAO.save(loser);
		}
		else
		{
			Player newPlayer = new Player();
			newPlayer.setPlayer(game.getLoser().getUserId());
			newPlayer.setLoss(1);
			newPlayer.setWin(0);
			playerDAO.save(newPlayer);
		}
	}

}
