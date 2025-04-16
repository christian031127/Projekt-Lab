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
    yarn <Név> <Tekton változó> [Tekton változó]
    yarn Y1 T1 //T1 Tektonon fonál létrehozása
    yarn Y2 T1 T2 //T1 és T2 Tekton között fonál létrehozása
```

Gomba növesztés
```java
    grow <Név> <Tekton változó> <Gombász változó>
    grow MS T1 //T1 Tektonon gomba növesztése
    grow MS2 T1 G2 //Gombász a T1 es Tektonon gombát növesztést kezdeményez (modell ellenőrzi, hogy megteheti-e)
```

Spóra szórás
```java
    eject <Típus> <Név> <Tekton változó>
    eject SlowingSpore SP1 T1 // Lassító spóra szórása T1 Tektonra ? kell-e gombász?
```