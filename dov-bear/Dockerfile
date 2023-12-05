####### Dockerfile gives the docker commands to run ########
FROM node:20.10.0

# create work directory
WORKDIR /app

# copy files over 
COPY main.js .
COPY package.json .
COPY package-lock.json .

# copy folders over
COPY public public
COPY views views

# build the application
RUN npm ci

# define environment variables
ENV PORT=3000 
ENV INSTANCE_VARIABLES="dov-bear"

# expose the port
EXPOSE ${PORT}

# run the program
# server.port is exclusive to springboot, docker uses PORT
ENTRYPOINT node main.js

