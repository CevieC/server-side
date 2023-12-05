####### Dockerfile gives the docker commands to run ########
# starting with this linux server
FROM maven:3-eclipse-temurin-21

####### Building the Application ########
# create a directory for our application
# create a directory call /app 
# go into the directory (cd /app)
WORKDIR /app

# everything after this is in /app
# copy files
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# copy directories (need to provide directory name)
COPY .mvn .mvn
COPY src src

# build the application
RUN mvn package -Dmaven.test.skip=true

####### Running the Application ########
# define environment variables
ENV PORT=8080

# expose the port
EXPOSE ${PORT}

# run the program
# server.port is exclusive to springboot, docker uses PORT
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/day14-0.0.1-SNAPSHOT.jar