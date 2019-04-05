mysql -u root -e 'source src\main\resources\drop.sql;'
pause

mvn clean compile assembly:single
pause

java -jar target\lolcounter-1-jar-with-dependencies.jar
pause
