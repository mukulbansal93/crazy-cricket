package com.brock.games.crazycricket.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.brock.games.crazycricket.dao.IPlayerDAO;
import com.brock.games.crazycricket.entity.Player;

/**
 * 
 * @author Mukul Bansal
 */

@Repository
public class PlayerDAO implements IPlayerDAO
{

	@Autowired
	private RedisTemplate<String, Player> redisTemplate;

	private static String PLAYER_KEY = "Player";

	@Override
	public void save(Player player)
	{
		this.redisTemplate.opsForHash().put(PLAYER_KEY, player.getPlayer(), player);
	}

	@Override
	public Player find(String id)
	{
		return (Player) this.redisTemplate.opsForHash().get(PLAYER_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll()
	{
		return this.redisTemplate.opsForHash().entries(PLAYER_KEY);
	}

	@Override
	public void delete(String id)
	{
		this.redisTemplate.opsForHash().delete(PLAYER_KEY, id);
	}

}
