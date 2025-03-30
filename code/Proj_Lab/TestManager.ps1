# Színek beállítása
$RED = [char]27 + "[1;31m"
$GREEN = [char]27 + "[1;32m"
$NC = [char]27 + "[0m"

Write-Host "Project buildelése"
mvn compile
Write-Host "Project csomagolása"
mvn package

# Tesztfájlok megszámolása
$osszTesztSzam = (Get-ChildItem -Path "Tests" | Measure-Object).Count
Write-Host "$osszTesztSzam teszteset található"
$sikeresTesztek = 0

# Összes teszt lefuttatása
foreach ($file in Get-ChildItem -Path "Tests") {
    Write-Host "---- A következő teszt indul: $file ----"
    
    # Input futtatása és output mentése
    Get-Content "$file\input.txt" | mvn exec:java | Out-File "$file\output.txt"

    # Eredmény ellenőrzése
    if (Test-Path "$file\output.txt") {
        if (Select-String -Path "$file\output.txt" -Pattern "BUILD SUCCESS") {
            $sikeresTesztek++
            Write-Host "$GREEN$file lefutása SIKERES!$NC"
        } else {
            Write-Host "$RED$file lefutása SIKERTELEN!$NC"
            Write-Host "Részletek:"
            Select-String -Path "$file\output.txt" -Pattern "ERROR"
        }
    } else {
        Write-Host "Nem található output fájl."
    }

    Write-Host "---- $file Vége ----"
}

Write-Host "Tesztek futtatásának vége!"
Write-Host "Eredmények: $sikeresTesztek / $osszTesztSzam (Sikeres / Sikertelen tesztek)"

if ($sikeresTesztek -eq $osszTesztSzam) {
    Write-Host "$GREEN ÖSSZES TESZT SIKERES! $NC"
} else {
    Write-Host "$RED SIKERTELEN, van még mit javítani! $NC"
}
