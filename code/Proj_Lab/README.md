## Játék futtatása
### Maven telepítése nélkül
#### Windows-on 
```bash
    ./mvnw.cmd compile
    ./mvnw.cmd package
    ./mvnw.cmd exec:java
```
Alternatív futtatás elkészült jar fájlból
```bash
    java -jar target/Projlab1.0-SNAPSHOT.jar
```

```bash
    java -cp target/Projlab1.0-SNAPSHOT.jar org.example.Main
```

#### Linux-on
```bash
    ./mvnw compile
    ./mvnw package
    ./mvnw exec:java
```
### Telepített maven esetén
```bash
    mvn conpile
    mvn package
    mvn exec:java
```

## Tesztek futtatása
A tesztek jelen pillanatban csak linuxon vannak 100% osan támogatva, de windows platformra is készülnek, csak lassabban.
### Linux-on 
```bash 
bash TestManager.sh
```
Vagy
```bash
sh TesztManager.sh
```
Vagy
```bash
chmod +X TesztManager.sh
./TesztManager.sh
```
## Teszhez használható parancsok

Az add parancsot csak a pálya generálásához lehet használni.
```java
add <Típus> <Név> [Egyéb specializálás]
add Tekton T1 SingleYarnTekton
add Shroom S1 <Tekton változó>
add Spore Sp1 T1
add Player Sr1 <Tekton vátozó> [Shroomer/Insect]
```

A szomszédok beállítását a neighbour parancs végzi
```java
neighbour <Tekton változó> <Tekton változó>
neighbour T1 T2
```

Fonál növesztés
```java
yarn <Player> <Név> <Tekton változó> [Tekton változó]
yarn Sh1 Y1 T1 //T1 Tektonon fonál létrehozása
yarn Sh1 Y2 T1 T2 //T1 és T2 Tekton között fonál létrehozása
```

Fonál evés
```java
eat Yarn <Yarn változó> <Player változó>
eat Yarn Y1 P1
//eat a játékost szimulálja

remove-yarn <Név> <Tekton változó> [Tekton változó]
remove-yarn Y1 T1 //T1 Tektonon fonál törlése
remove-yarn Y2 T1 T2 //T1 és T2 Tekton közötti fonál törlése
```

Gomba növesztés
```java
grow <Név> <Tekton változó> <Játékos változó> <Gomba kora>
grow MS T1 G2 5//T1 Tektonon gomba növesztése
grow MS2 T1 G2 0//G2 Gombász a T1 es Tektonon gombát növesztést kezdeményez (modell ellenőrzi, hogy megteheti-e)
```

Spóra szórás
```java
eject <Név> <Tekton változó> <Player>
eject SP1 T1 Sh1 // Lassító spóra szórása T1 Tektonra ? kell-e gombász?
```

Spóra evés
```java
eat Spore <Spóra változó> <Játékos változó>
eat Spore SP1 Sr1 //SR1 játékos elfogyasztani próbálja a SP1 spórát
```

Tekton split
```java
split <Tekton változó> <Tekton Újváltozó> <Tekton Újváltozó>
split T1 T2 T3
```

Player mozgatás
```java
move <Player változó> <Tekton változó>
move P1 T1
```