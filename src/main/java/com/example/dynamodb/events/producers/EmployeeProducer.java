package com.example.dynamodb.events.producers;

import com.example.dynamodb.entity.Employee;
import com.example.dynamodb.entity.EmployeeSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeProducer {

    private final KafkaTemplate<String, EmployeeSchema> kafkaTemplate;
    @Value("${spring.kafka.topic}")
    private String topic;

    public void producer(Employee employee) {
        final EmployeeSchema employeeSchema = EmployeeSchema.newBuilder()
                .setCpf(employee.getCpf())
                .setEmail(employee.getEmail())
                .setId(employee.getId())
                .setName(employee.getName())
                .build();

        ProducerRecord<String, EmployeeSchema> producerRecord = new ProducerRecord<>(topic, employeeSchema);

        kafkaTemplate.send(producerRecord).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Message send fail " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, EmployeeSchema> stringEmployeeSchemaSendResult) {
                log.info("Message send success " + employeeSchema);
                final RecordMetadata producerRecord = stringEmployeeSchemaSendResult.getRecordMetadata();
                log.info("Topic: " + producerRecord.topic());
                log.info("Offset: " + producerRecord.partition());
                log.info("Offset: " + producerRecord.offset());
            }
        });
    }

}
