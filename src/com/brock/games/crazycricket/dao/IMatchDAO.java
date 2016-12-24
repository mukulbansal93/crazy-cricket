package com.brock.games.crazycricket.dao;

import java.util.Map;

import com.brock.games.crazycricket.entity.Match;

/**
 * 
 * @author Mukul Bansal
 */

public interface IMatchDAO
{
	public void save(Match match);
	public Match find(String id);
	public Map<Object, Object> findAll();
	public void delete(String id);
}
