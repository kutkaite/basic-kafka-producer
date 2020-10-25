# Apache Kafka & Java Spring Boot
## Running simple message flow with docker

Start zookeeper and kafka:

`docker-compose up -d`

Get container list and retrieve the name of kafka container:

`docker ps`

### Producer side

Get inside kafka container:

`docker exec -it kafka_1 bash`

```
# Create a new topic named test
kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_test

# Start a producer
kafka-console-producer.sh --broker-list localhost:9092 --topic t_test
```

### Consumer side
Get inside kafka container (on another terminal window):

`docker exec -it kafka_1 bash`

```
# Start a consumer which feeds on test topic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t_test
```

<hr>

You can now type messages on producer side which will get transmitted to the consumer 🎉

<hr>

### Topics used:

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_test`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_incremental`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 4 --topic t_multiple_partitions`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_employee`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_commodity`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_location`

### API

`http://localhost/api/commodity/v1/all`

#### Useful commands:

List topics:
`kafka-topics.sh --bootstrap-server=localhost:9092  --list`

Delete topic:
`kafka-topics.sh --bootstrap-server=localhost:9092  --delete --topic test`

Create topic with multiple partitions:
`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 3 --topic t_multiple_partitions`

`kafka-topics.sh --bootstrap-server localhost:9092 --topic t_multiple_partitions --describe`

`kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic t_multiple_partitions --partitions 4`

Check group ids:
`kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group cg-dashboard --describe`

Change offset for a specific group:
`kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group cg-dashboard --execute --reset-offsets --to-offset 10 --topic t_commodity:0`

#### Producers:
1. `KafkaProducer.java` - sends a simple `String` message and is called from `KafkaProducerApplication.java`
2. `IncrementalProducer.java` - sends messages and is run on a schedule using `@Scheduled` annotation
3. `KafkaKeyProducer.java` - sends a message with a key
4. `EmployeeProducer` - send a JSON message
5. `CommodityProducer.java` - send a JSON message scheduled by `CommodityScheduler` service and getting data from `http://localhost:80/api/commodity/v1/all`
6. `CarLocationProducer.java` - sends a JSON message