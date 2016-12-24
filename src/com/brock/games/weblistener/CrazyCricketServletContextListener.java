package com.brock.games.weblistener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.brock.games.crazycricket.constants.Constants;
import com.brock.games.utils.KafkaReader;

/**
 * 
 * @author Mukul Bansal
 */

public class CrazyCricketServletContextListener implements ServletContextListener
{

	private ExecutorService executor;

	@Override
	public void contextInitialized(ServletContextEvent event)
	{

		// Very important: Enables Spring bean injection into
		// ServletContextListener
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		try
		{

			String[] KafkaTopicsArray = Constants.KAFKA_TOPICS.split(",");
			int noOfTopics = KafkaTopicsArray.length;

			// using 'one thread per topic' model
			executor = Executors.newFixedThreadPool(noOfTopics);

			for (String topic : KafkaTopicsArray)
			{
				System.out.println("Starting to Read TOPIC-" + topic);
				executor.submit(new KafkaReader(topic));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		if (executor != null)
			executor.shutdown();
		try
		{
			if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS))
			{
				System.out.println(
						"Timed out waiting for consumer threads to shut down, exiting uncleanly...");
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Interrupted during shutdown, exiting uncleanly...");
		}

	}

}
