package donghyun.wordprocessor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WordProcessor {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WordProcessor(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "word-combinations", groupId = "word-processor-group")
    public void processWord(String word) {
        String processedWord = word.toUpperCase();
        System.out.println("Received: " + word);
        System.out.println("Processing: " + processedWord);

        kafkaTemplate.send("processed-words", processedWord);
        System.out.println("Sent to processed-words topic: " + processedWord);
    }
}
