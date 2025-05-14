echo "Project buildelése"
mvn compile
echo "Project csomagolása"
mvn package
#echo "JavaDoc generálása"
#mvn javadoc:javadoc
echo indítás
java -jar target/Proj_Lab-1.0-SNAPSHOT.jar
