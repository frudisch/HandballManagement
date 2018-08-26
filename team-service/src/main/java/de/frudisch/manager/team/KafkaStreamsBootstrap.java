package de.frudisch.manager.team;

import org.apache.kafka.streams.KafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamsBootstrap implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

    /*
    private KafkaStreams kafkaStreams;

    @Autowired
    public KafkaStreamsBootstrap(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
        System.err.println("kafka bootstrap created!");
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaStreams.cleanUp();
        kafkaStreams.start();
        System.err.println("kafka streams started " + kafkaStreams.state().isRunning());
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
    */
}
