package donghyun.wordprocessor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WordProcessor {

    @KafkaListener(topics = "word-combinations", groupId = "word-processor-group")
    public void processWord(String word) {
        String processedWord = word.toUpperCase();
        System.out.println("Received: " + word);
        System.out.println("Processing: " + processedWord);
    }
}
