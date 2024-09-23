package donghyun.wordgenerator

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class WordGenerator(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val adjectives = listOf("빨간", "파란", "초록", "노란", "검은", "흰")
    private val nouns = listOf("사과", "바나나", "체리", "대추", "엘더베리", "무화과")

    @Scheduled(fixedRate = 5000)
    fun generateAndSendWord() {
        val adjective = adjectives.random()
        val noun = nouns.random()
        val combination = "$adjective $noun"

        kafkaTemplate.send("word-combinations", combination)
        println("Generated and sent word: $combination")
    }

}