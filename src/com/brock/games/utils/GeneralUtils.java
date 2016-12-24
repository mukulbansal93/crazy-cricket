package com.brock.games.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class GeneralUtils
{
	/**
	 * @author Mukul Bansal
	 * 
	 */
	public static Long dateTimeConverter(String format, Long unixTime)
	{
		Date date = new Date(unixTime);
		String str = new SimpleDateFormat(format).format(date);
		return new Long(str);
	}

	/**
	 * 
	 * @author https://www.mkyong.com/java/how-to-sort-a-map-in-java/
	 */
	public static HashMap<String, Float> sortHashMap(HashMap<String, Float> originalHashMap)
	{
		// 1. Convert Map to List of Map
		List<HashMap.Entry<String, Float>> list = new LinkedList<HashMap.Entry<String, Float>>(
				originalHashMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<HashMap.Entry<String, Float>>()
		{
			public int compare(HashMap.Entry<String, Float> o1, HashMap.Entry<String, Float> o2)
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		HashMap<String, Float> sortedMap = new LinkedHashMap<String, Float>();
		for (HashMap.Entry<String, Float> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
