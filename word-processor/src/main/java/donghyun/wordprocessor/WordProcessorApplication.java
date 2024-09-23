package donghyun.wordprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class WordProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordProcessorApplication.class, args);
    }

}
