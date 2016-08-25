#!/bin/bash
cd ..
echo "0) Cleanup"
rm -rf build/libs
echo "1) BUILD jagent"
./gradlew fatJar
echo "2) COPY to sandbox"
cp ./build/libs/jagent-all.jar sandbox
cd sandbox
echo "5) RUN"
echo "----------------------------------"
java -javaagent:jagent-all.jar -jar dummy.jar
echo "----------------------------------"
