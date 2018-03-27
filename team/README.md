# Team Microservice

The microservice is based on a Apache Kafka. The service provides the typical CRUD HTTP Methods. Every request is directed to the corresponding topic in Kafka.

The Create Request creates a new Event containing the passed Team, which is send to Kafkas "raw-data" topic. Update and Delete Request are also converted to Events and passed to the "raw-data" topic.

Selecting a team by its corresponding UUID is possible through a State Store. Using a Stream Processor the "raw-data" topic is mapped and reduced to contain only the recent team with the corresponding UUID. When sending a UUID Get Request the passed ID is selected through a ReadyOnlyKeyValueStore.

Selecting a team by the name or multiple Teams by their name, a Stream Processor is used to select the team using the same mechanism as described with the UUID.