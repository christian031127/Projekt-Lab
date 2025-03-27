#!/bin/bash

echo "Project buildelése"
mvn compile
echo "Project csomagolása"
mvn package

echo "Első teszt futtatása"

cat Tests/Test1/input.txt | mvn exec:java > Tests/Test1/output.txt

echo "Kész"