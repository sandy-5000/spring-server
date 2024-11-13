#!/bin/bash

# Check of both arguments are provided
if [[ -z "$1" || -z "$2" ]]
then
    echo "Error: Both group ID and artifact ID are required."
    echo "Usage: create-spring-java <group_id> <artifact_id>"
    exit 1
fi

# Check if both argumants contain only alphabets
if [[ ! "$1" =~ ^[a-zA-z]+$ || ! "$2" =~ ^[a-zA-z]+$ ]]
then
    echo "Error: Both group ID and artifact ID should contain only alphabets."
    exit 1
fi

# Run Spring boot command
spring init --dependencies=web --build=gradle --type=gradle-project --groupId=com.$1 --artifactId=server $2

