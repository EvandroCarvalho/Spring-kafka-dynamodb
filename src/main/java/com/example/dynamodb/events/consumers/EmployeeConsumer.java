package com.example.dynamodb.events.consumers;

import com.example.dynamodb.entity.EmployeeSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "group-id")
    public void listener(ConsumerRecord<String, EmployeeSchema> employeeSchema) {
        final EmployeeSchema value = employeeSchema.value();
        log.info("RECEIVE: " + value.getName());
    }
}
