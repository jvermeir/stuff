# Docker examples

##build and tag
    docker build -t jenkins/jenkins1.617 .
    docker build -t jenkins/securities-jenkins1.617 .

##show local images
    docker images

##run image in daemon mode
 
    docker run -d  jenkins/jenkins1.617

##map all exposed ports to random host ports
    docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins1.617

##show running images
    docker ps

##show logs for container with id starting with d51d
    docker logs d51d

##connect to running container and run a shell
    docker exec -it d51d /bin/bash

##kill container
    docker kill d51d

##run.sh
    #!/bin/bash
    docker run -p 8080:8080 -p 50000:50000 \
       -v ${PWD}/job-dsl:/usr/share/jenkins/ref/init.groovy.d/job-dsl \
       jenkins/securities-jenkins

##run local registry
    docker run -d -p 5000:5000 --restart=always --name registry registry:2

##pull ubuntu, tag
    docker pull ubuntu && docker tag ubuntu localhost:5000/ubuntu

##push to local registry
    docker push localhost:5000/ubuntu

##registry urls
    http://localhost:5000/v2/_catalog
    http://localhost:5000/v2/ubuntu/tags/list

##remove all containers
    docker rm `docker ps -aq`

##Run a shell, mapping pwd to /source in the container
    docker run -it -v $PWD:/source java /bin/bash

## Push image to docker hub 
    docker login  # do this once 
    
    docker build . -t jvermeir/date-time
    
    docker push jvermeir/date-time:latest
    