@echo off
setlocal enabledelayedexpansion

rem Színek beállítása (Windows nem támogatja a színes kimenetet alapból)

echo Project buildelése
mvn compile
echo Project csomagolása
mvn package

rem Tesztfájlok megszámolása
set /a osszTesztSzam=0
for %%F in (Tests\*) do set /a osszTesztSzam+=1

echo %osszTesztSzam% teszteset található
set /a sikeresTesztek=0

rem Összes teszt lefuttatása
for %%F in (Tests\*) do (
    echo ---- A következő teszt indul: %%F ----
    
    rem Input futtatása és output mentése
    type %%F\input.txt | mvn exec:java > %%F\output.txt

    rem Eredmény ellenőrzése
    if exist %%F\output.txt (
        findstr /C:"BUILD SUCCESS" %%F\output.txt >nul
        if !errorlevel! == 0 (
            set /a sikeresTesztek+=1
            echo %%F lefutása SIKERES!
        ) else (
            echo %%F lefutása SIKERTELEN!
            echo Részletek:
            findstr /C:"ERROR" %%F\output.txt
        )
    ) else (
        echo Nem található output fájl.
    )

    echo ---- %%F Vége ----
)

echo Tesztek futtatásának vége!
echo Eredmények: %sikeresTesztek% / %osszTesztSzam% (Sikeres / Sikertelen tesztek)

if %sikeresTesztek% == %osszTesztSzam% (
    echo ÖSSZES TESZT SIKERES!
) else (
    echo SIKERTELEN, van még mit javítani!
)

endlocal
