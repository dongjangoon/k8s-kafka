package donghyun.wordgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WordGeneratorApplication

fun main(args: Array<String>) {
    runApplication<WordGeneratorApplication>(*args)
}
