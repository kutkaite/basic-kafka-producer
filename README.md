# Apache Kafka & Java Spring Boot - Producer
## Running simple message flow with docker

Start zookeeper and kafka:

`docker-compose up -d`

Get container list and retrieve the name of kafka container:

`docker ps`

### Producer side setup

Get inside kafka container:

`docker exec -it kafka_1 bash`

```
# Create a new topic named test
kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_test

# Start a producer
kafka-console-producer.sh --broker-list localhost:9092 --topic t_test
```

Consumer side [setup](https://github.com/kutkaite/basic-kafka-consumer#consumer-side-setup)

### Topics used:

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_test`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_incremental`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 4 --topic t_multiple_partitions`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_employee`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_commodity`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_location`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_food_order`

`kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t_simple_number`

## Producers explained:
1. `KafkaProducer.java` - sends a simple `String` message and is called from `KafkaProducerApplication.java`
2. `IncrementalProducer.java` - sends messages and is run on a schedule using `@Scheduled` annotation
3. `KafkaKeyProducer.java` - sends a message with a key to topic `t_multiple_partitions`
4. `EmployeeProducer` - send a JSON message to topic `t_employee`
5. `CommodityProducer.java` - send a JSON message scheduled by `CommodityScheduler` service and getting data from `http://localhost:80/api/commodity/v1/all`
6. `CarLocationProducer.java` - sends a JSON message to topic `t_location`
7. `FoodOrderProducer.java` - sends a JSON message  to topic `t_food_order`
8. `SimpleNumberProducer.java` - sends a JSON message to topic `t_simple_number`

## Useful commands:

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
