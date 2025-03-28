#!/bin/bash

echo "Project buildelése"
mvn compile
echo "Project csomagolása"
mvn package

echo "Első teszt futtatása"

osszTesztSzam=$(ls Tests | wc -l)
echo "$osszTesztSzam teszteset található"


#Összes teszt lefuttatása
for file in Tests/*; do
    echo "---- A következő teszt indul: $file ----"
    cat $file/input.txt
    #cat $file/input.txt | java -jar target/Proj_Lab-1.0-SNAPSHOT.jar > $file/output.txt
    cat $file/input.txt | mvn exec:java > $file/output.txt

    #Eredmény ellenőrzése
done

#cat Tests/Test1/input.txt | mvn exec:java > Tests/Test1/output.txt

echo "Kész"