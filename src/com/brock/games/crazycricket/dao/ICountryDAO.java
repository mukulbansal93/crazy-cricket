package com.brock.games.crazycricket.dao;

import java.util.Map;

import com.brock.games.crazycricket.entity.Country;


/**
 * 
 * @author Mukul Bansal
 */

public interface ICountryDAO
{
	public void save(Country country);
	public Country find(String id);
	public Map<Object, Object> findAll();
	public void delete(String id);
}
