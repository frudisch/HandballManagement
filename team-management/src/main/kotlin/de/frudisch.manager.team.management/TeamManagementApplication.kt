package de.frudisch.manager.team.management

import de.frudisch.manager.team.management.domain.TeamRepository
import de.frudisch.manager.team.management.domain.entity.Team
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class TeamManagementApplication {

    val BASE_URL : String = "/api/v1/"

    @Bean
    open fun init(repository: TeamRepository) = CommandLineRunner {
//        repository.save(Team("Jack", 1))
//        repository.save(Team("Chloe", 2))
//        repository.save(Team("Kim", 3))
//        repository.save(Team("David", 4))
//        repository.save(Team("Michelle", 5))
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(TeamManagementApplication::class.java, *args)
}
