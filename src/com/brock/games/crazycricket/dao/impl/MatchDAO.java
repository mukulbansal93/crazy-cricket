package com.brock.games.crazycricket.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.brock.games.crazycricket.dao.IMatchDAO;
import com.brock.games.crazycricket.entity.Match;

/**
 * 
 * @author Mukul Bansal
 */
@Repository
public class MatchDAO implements IMatchDAO
{
	@Autowired
	private RedisTemplate<String, Match> redisTemplate;
	private static String MATCH_KEY = "Match";

	@Override
	public void save(Long key, Match match)
	{
		this.redisTemplate.opsForHash().put(MATCH_KEY, key, match);
	}

	@Override
	public Match find(String id)
	{
		return (Match)this.redisTemplate.opsForHash().get(MATCH_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll()
	{
		return this.redisTemplate.opsForHash().entries(MATCH_KEY);
	}

	@Override
	public void delete(String id)
	{
		 this.redisTemplate.opsForHash().delete(MATCH_KEY,id); 
	}
}
