# Maven

## dependency tree
    mvn dependency:tree
     
## dependency tree only for com.google.guava
    mvn dependency:tree -Dincludes=com.google.guava
    
## new java project

mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

## find a version of a library

    https://mvnrepository.com/    