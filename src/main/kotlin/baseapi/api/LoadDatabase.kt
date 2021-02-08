package baseapi.api

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class LoadDatabase {
    companion object{
        val logger = LoggerFactory.getLogger(LoadDatabase.javaClass)
    }

    @Bean
    fun initDatabase(boredRepository: BoredRepository) : CommandLineRunner{
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("Preloading " + boredRepository.save(Bored("Futbol",30)))
            logger.info("Preloading " + boredRepository.save(Bored("Natacion",72)))
        }
    }
}