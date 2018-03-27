package de.frudisch.manager.team;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import static de.frudisch.manager.team.ApplicationConfiguration.NAME_UUIDS_DATA;
import static de.frudisch.manager.team.ApplicationConfiguration.RAW_DATA;
import static de.frudisch.manager.team.ApplicationConfiguration.UUID_TEAM_DATA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamApplicationTests {


	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RAW_DATA, UUID_TEAM_DATA, NAME_UUIDS_DATA);


	@BeforeClass
	public static void setupClass() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
	}

	@Test
	public void contextLoads() {
	}

	@AfterClass
	public static void shutdownClass() throws Exception {
		embeddedKafka.destroy();
	}

}
