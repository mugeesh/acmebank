#!/usr/bin/env bash
# for example
cd $(pwd)
gradle fatJar
java -jar $(pwd)/build/libs/sample.jar -Dorg.slf4j.simpleLogger.defaultLogLevel=debug