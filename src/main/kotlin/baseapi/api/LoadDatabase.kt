package baseapi.api

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import javax.management.loading.ClassLoaderRepository

class LoadDatabase {
    companion object{
        val logger = LoggerFactory.getLogger(LoadDatabase.javaClass)
    }

    fun initDatabase(boredRepository: BoredRepository) : CommandLineRunner{
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("Preloading " + boredRepository.save())
            logger.info("Preloading " + boredRepository.save())
        }
    }
}