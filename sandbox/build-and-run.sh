#!/bin/bash
echo "1) BUILD jagent"
../gradlew build
echo "2) COPY to sandbox"
cp ../build/libs/jagent.jar .
echo "3) RUN"
echo "----------------------------------"
java -javaagent:jagent.jar -jar dummy.jar
echo "----------------------------------"
