#!/usr/bin/env bash

mkdir target/appengine-staging
mvn package -Dmaven.test.skip=true
ls -all ./target
cp ./target/spring-boot*.jar target/appengine-staging


