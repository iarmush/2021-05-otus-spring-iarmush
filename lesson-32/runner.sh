#!/usr/bin/env bash

mvn clean package
docker build -t lesson-32 .
docker-compose up -d