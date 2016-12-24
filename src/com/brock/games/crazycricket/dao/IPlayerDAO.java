package com.brock.games.crazycricket.dao;

import java.util.Map;

import com.brock.games.crazycricket.entity.Player;

/**
 * 
 * @author Mukul Bansal
 */

public interface IPlayerDAO
{
	public void save(Player player);
	public Player find(String id);
	public Map<Object, Object> findAll();
	public void delete(String id);
}
