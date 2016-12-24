package com.brock.games.crazycricket.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.brock.games.crazycricket.dao.ICountryDAO;
import com.brock.games.crazycricket.entity.Country;

/**
 * 
 * @author Mukul Bansal
 */

@Repository
public class CountryDAO implements ICountryDAO
{
	@Autowired
	private RedisTemplate<String, Country> redisTemplate;
	private static String COUNTRY_KEY = "Country";

	@Override
	public void save(Country country)
	{
		this.redisTemplate.opsForHash().put(COUNTRY_KEY, country.getCountry(), country);
	}

	@Override
	public Country find(String id)
	{
		return (Country)this.redisTemplate.opsForHash().get(COUNTRY_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll()
	{
		return this.redisTemplate.opsForHash().entries(COUNTRY_KEY);
	}

	@Override
	public void delete(String id)
	{
		 this.redisTemplate.opsForHash().delete(COUNTRY_KEY,id); 
	}

}
