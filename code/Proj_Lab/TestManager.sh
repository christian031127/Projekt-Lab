#!/bin/bash

RED='\033[1;31m'
GREEN='\033[1;32m'
NC='\033[0m'
BOLD=$(tput bold)
NORMAL=$(tput sgr0)

echo "Project buildelése"
mvn compile
echo "Project csomagolása"
mvn package

osszTesztSzam=$(ls Tests | wc -l)
echo "$osszTesztSzam teszteset található"
sikeresTesztek=0

#Összes teszt lefuttatása
for file in Tests/*; do
    echo "---- A következő teszt indul: $file ----"
    #cat $file/input.txt
    #cat $file/input.txt | java -jar target/Proj_Lab-1.0-SNAPSHOT.jar > $file/output.txt
    cat $file/input.txt | mvn exec:java > $file/output.txt

    #Eredmény ellenőrzése
    if [[ -e "$file/output.txt" ]]; then
        if [[ $(cat $file/output.txt | grep "BUILD SUCCESS" | wc -l) -gt 0 ]]; then
            sikeresTesztek=$((sikeresTesztek+1))
            echo -e "${GREEN}$file lefutása SIKERES!${NC}" 
        else
            echo -e "${RED}$file lefutása SIKERTELEN!${NC}"
            echo "Részletek: "
            cat $file/output.txt | grep "ERROR"
        fi
    else
        echo "nem található fájl"
    fi

    echo "---- $file Vége ----"
done

#cat Tests/Test1/input.txt | mvn exec:java > Tests/Test1/output.txt

echo "Tesztek futtatásának vége!"
echo "Eredmények: ${sikeresTesztek} / ${osszTesztSzam} (Sikeres / sikeretelenek tesztek)"

if [[ $sikeresTesztek -eq $osszTesztSzam ]]; then
    echo -e "${GREEN}ÖSSZES TESZT SIKERES!${NC}"
else
    echo -e "${RED}SIKERTELEN${NC}, van még mit javítani!"
fi