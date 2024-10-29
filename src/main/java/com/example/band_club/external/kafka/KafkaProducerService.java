package com.example.band_club.external.kafka;

import com.example.band_club.external.JsonUtil;
import com.example.band_club.activity.command.ActivityCommand;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;


    public String sendActivityCommandToKafka(ActivityCommand command){

        ObjectNode node = JsonUtil.toObjectNode(command);
        node.put("type", command.getClass().getSimpleName());
        String message = JsonUtil.toJson(node);

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.whenComplete((result, ex) -> {
            if(ex != null){
                log.error("Error: " + ex.getMessage());
            } else{
                log.info("Success: " + result.getRecordMetadata());
            }
        });

        return message;
    }
}
