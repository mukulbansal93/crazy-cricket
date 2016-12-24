package com.brock.games.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brock.games.crazycricket.constants.Constants;
import com.brock.games.crazycricket.protobuf.CrazyCricketProtos;
import com.brock.games.crazycricket.protobuf.CrazyCricketProtos.Game;
import com.google.protobuf.InvalidProtocolBufferException;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 
 * @author Mukul Bansal
 */

public class KafkaReader implements Runnable
{
	private final ConsumerConnector consumerConnector;
	private final String topic;
	
	public KafkaReader(String topic)
	{
		Properties properties = new Properties();
		properties.put("zookeeper.connect", Constants.ZOOKEEPER_IP);
		properties.put("group.id", UUID.randomUUID().toString());
		properties.put("auto.offset.reset", "smallest");
		properties.put("zookeeper.session.timeout.ms", "10000");
		ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		this.consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
		this.topic=topic;
	}

	@Override
	public void run()
	{

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector
				.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext())
		{
			byte[] message=it.next().message();
			Game game;
			try
			{
				game = CrazyCricketProtos.Game.parseFrom(message);
				//System.out.println(game);
			}
			catch (InvalidProtocolBufferException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}