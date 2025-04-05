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
