package com.brock.games.crazycricket.dao;

import java.util.Map;

import com.brock.games.crazycricket.entity.Match;

/**
 * 
 * @author Mukul Bansal
 */

public interface IMatchDAO
{
	public Match find(String id);
	public Map<Object, Object> findAll();
	public void delete(String id);
	public void save(Long key, Match match);
}
