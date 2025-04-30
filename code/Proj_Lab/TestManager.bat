@echo off
setlocal enabledelayedexpansion

echo Project buildelese
call .\mvnw.cmd compile
echo Project csomagolasa
call .\mvnw.cmd package
echo Javadoc elkeszitese
call .\mvnw.cmd javadoc:javadoc

set /a sikeresTesztek=0
set /a osszTesztSzam=0

for /d %%G in (Tests\*) do (
    set /a osszTesztSzam+=1
)

echo %osszTesztSzam% teszteset talalhato

for /d %%F in (Tests\*) do (
    echo ---- A kovetkezo teszt indul: %%F ----

    type "%%F\input.txt" | java -cp .\target\Proj_Lab-1.0-SNAPSHOT.jar org.example.Teszt > "%%F\output.txt"
    type logfile.log >> "%%F\output.txt"

    if exist "%%F\output.txt" (
        set sikeres=1

        for /f "usebackq delims=" %%A in ("%%F\expected.txt") do (
            findstr /c:"%%A" "%%F\output.txt" >nul
            if errorlevel 1 (
                echo Nem talalhato: %%A
                set sikeres=0
            )
        )

        if "!sikeres!"=="1" (
            set /a sikeresTesztek+=1
            echo %%F lefutasa SIKERES!
        ) else (
            echo %%F lefutasa SIKERTELEN!
            echo Reszletek:
            findstr /r /c:"ERROR" /c:"WARNING" "%%F\output.txt"
        )
    ) else (
        echo nem talalhato fajl
    )

    echo ---- %%F Vege ----
)

echo Tesztek futtatasanak vege!
echo Eredmenyek: %sikeresTesztek% / %osszTesztSzam% (Sikeres / sikertelen tesztek)

if "%sikeresTesztek%"=="%osszTesztSzam%" (
    echo OSSZES TESZT SIKERES!
) else (
    echo SIKERTELEN, van meg mit javitani!
)

endlocal
pause
