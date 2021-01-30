#!/bin/bash

# run clean target on all pom files

cd ~/dev
root_path=$(pwd -P)

poms=$(find $root_path -name "pom.xml")

for pom in $poms; do
  echo pom: ${pom}
  project=$(dirname ${pom})
  echo project: ${project}
  cd ${project}
  mvn clean | grep "BUILD SUCCESS"
done
