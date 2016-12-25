## crazy-cricket
APIs for Blackrock's crazy-cricket problem 

## Prerequisite

  - Windows (or Linux) 
  - JDK 1.8 or above
  - Apache Kafka- [Recommended Kafka Version] 
  - Apache Maven- [Recommended Maven Version]
  - Apache Tomcat 7 or above
  - Redis (any version)

## Environment Setup

  - Clone this repo - https://github.com/mukulbansal93/crazy-cricket.git
  - Build using Maven.
```sh
> cd crazy-cricket
> mvn clean package
```
  - Start the Apache Kafka Zookeeper and Server on localhost.
  - Start the Redis Server on localhost.
  - Deploy the build war formed in target folder on Apache Tomcat.
  - Follow the steps at https://github.com/blackrock/crazy-cricket to push sample messages to Apache Kafka and test the APIs using browser(since all are GET requests) or use any REST client like [POSTMAN]



[Recommended Kafka Version]: <https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.1.0/kafka_2.11-0.10.1.0.tgz>
[Recommended Maven Version]:<http://www-eu.apache.org/dist//maven/maven-3/3.3.9/binaries/>
[POSTMAN]:<https://www.getpostman.com/>
   


