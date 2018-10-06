mysql -u root -e 'source src\main\resources\drop.sql'
mvn clean compile assembly:single
java -jar target/lolcounter-1-jar-with-dependencies.jar