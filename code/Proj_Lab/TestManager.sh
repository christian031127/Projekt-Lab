#!/bin/bash

RED='\033[1;31m'
GREEN='\033[1;32m'
YELLOW='\033[1;33m'
WHITE='\033[1;34m'
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
    echo -e  "${WHITE}---- A következő teszt indul: $file ----${NC}"
    #cat $file/input.txt
    cat $file/input.txt | java -cp target/Proj_Lab-1.0-SNAPSHOT.jar org.example.Teszt > $file/output.txt
    #cat $file/input.txt | mvn exec:java > $file/output.txt
    cat logfile.log >> $file/output.txt
    #rm logfile.log

    #Eredmény ellenőrzése
    #Ha exception van benne, akkor instant bukó
    if [[ -e "$file/output.txt" ]]; then

        sikeres=1

        while IFS= read -r line; do
            # Ha a sor nincs benne a file2-ben, kiírjuk
            if ! grep -Fq "$line" "$file/output.txt"; then
                echo -e "Nem található: ${YELLOW}$line${NC}"
                sikeres=0
            fi

            if cat $file/output.txt | grep -q "Exception"; then
                problema = cat $file/output.txt | grep -q "Exception"
                echo -e "Hiba található a tesztben (Exception): ${RED}$problema${NC}"
                sikeres=0
            fi
        done < "$file/expected.txt"

        if [[ $sikeres -gt 0 ]]; then
            sikeresTesztek=$((sikeresTesztek+1))
            echo -e "${GREEN}$file lefutása SIKERES!${NC}" 
        else
            echo -e "${RED}$file lefutása SIKERTELEN!${NC}"
            echo "Részletek: "
            cat $file/output.txt | grep -E "ERROR|WARNING"
        fi
    else
        echo "nem található fájl"
    fi

    #echo "---- $file Vége ----"
done


echo "Tesztek futtatásának vége!"
echo "Eredmények: ${sikeresTesztek} / ${osszTesztSzam} (Sikeres / Összes tesztek)"

if [[ $sikeresTesztek -eq $osszTesztSzam ]]; then
    echo -e "${GREEN}ÖSSZES TESZT SIKERES!${NC}"
else
    echo -e "${RED}SIKERTELEN${NC}, van még mit javítani!"
fi