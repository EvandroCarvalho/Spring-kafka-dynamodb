package com.example.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.UUID;

@Data
@DynamoDBTable(tableName = "employee")
public class Employee {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "cpf")
    private String cpf;
    @DynamoDBAttribute(attributeName = "email")
    private String email;

}
