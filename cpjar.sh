#!/usr/bin/env bash

mkdir target/appengine-staging
cp app.yaml ./target/appengine-staging
mvn package -Dmaven.test.skip=true
ls -all ./target
cp ./target/spring-boot*.jar ./target/appengine-staging
ls -all ./target/appengine-staging


