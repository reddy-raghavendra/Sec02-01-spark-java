# This project is to determine how to find the page rank for the files saved in web04 folder.

[My Wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Raghavendra-Reddy)

## A sample wordcount example pipeline using apache beam Java SDK

### Check your java verion using
java --version

### check maven verion with below command, if not present install it
maven --version

### Generate a maven project using following command
mvn archetype:generate `
 -D archetypeGroupId=org.apache.beam `
 -D archetypeArtifactId=beam-sdks-java-maven-archetypes-examples `
 -D archetypeVersion=2.36.0 `
 -D groupId=org.example `
 -D artifactId=word-count-beam `
 -D version="0.1" `
 -D package=org.apache.beam.examples `
 -D interactiveMode=false

 ### Run word count using maven 
mvn compile exec:java -D exec.mainClass=edu.nwmsu.sec02grp1.reddyedu.nwmsu.sec02grp1.reddy.MinimalPageRankReddy