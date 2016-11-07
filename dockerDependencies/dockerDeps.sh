#!/bin/bash

# 
# Read all Dockerfiles from a given root and draw a diagram 
# showing how they depend on each other.
# Output is in $OUTPUT_FILE which can be processed by dot to produce a graph.
# Some files are ignored because in my orginal use case they produced a useless 
# picture. You may have to adapt the if statement for best results. 
# 
# Usage: ./dockerDeps.sh <-r rootOfSources> [-o outputFile]
# Example: 
#   ./dockerDeps.sh -r ./test
#   dot -Tps $OUTPUT_FILE -o dockerDeps.ps
#

if (( $# < 1 ))
then
   printf "Usage: ./dockerDeps.sh <-r rootOfSources> [-o outputFile]\n"
   exit 2
fi

OUTPUT_FILE=/tmp/dockerDeps.gv
while getopts 'r:o:' OPTION
do
  case $OPTION in
     r) SRC_ROOT="$OPTARG"
   	    ;;
     o) OUTPUT_FILE="$OPTARG"
   	    ;;
  esac
done

dockerFiles=`find $SRC_ROOT -name Dockerfile`

echo "digraph G {" > $OUTPUT_FILE
for i in $dockerFiles;
do
  tmp=`echo $i | sed "s|/Dockerfile||g"`
  file=${tmp##*/}

  from=`grep "FROM" $i | cut -d' ' -f2`
  noWork=`echo $from | sed "s|:WORK||g"`
  fromImage=${noWork##*/}
 
  pattern="(oracle-xe-11g|java:8-jdk|ubuntu)"
  if [[ $fromImage =~ $pattern ]]
  then
    echo "skip \""$file"\" -> \""$fromImage"\";"
  else
    echo "\""$file"\" -> \""$fromImage"\";" >> $OUTPUT_FILE
  fi
done

echo "}" >> $OUTPUT_FILE

echo --------
cat $OUTPUT_FILE
echo --------
echo "output in $OUTPUT_FILE"
