FROM openjdk:11.0.9
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} machines.jar
CMD ["java","-jar","-Dspring.profiles.active=h2,cache,redis-cache","machines.jar"]

#1- build (clean, test, package)
#2- build docker: docker build -t machines:1.0.0 .
#3- docker-compose up


#java -jar -Dspring.profiles.active=h2,cache,redis-cache ./target/machines-1.0.0.jar
#java -jar -Dspring.profiles.active=h2,cache,redis-cache machines.jar


# build docker: docker build -t machines:1.0.0 .

# docker build -t habibahmedmagdy/machines:1.0.0 .
# docker tag machines:2.0.0 habibahmedmagdy/machines:2.0.0
# By using docker commit <existing-container> <hub-user>/<repo-name>[:<tag>] to commit changes
# docker push habibahmedmagdy/machines:1.0.0