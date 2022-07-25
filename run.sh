#!/bin/sh
mkdir -p out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Blob.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Database.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Application.java -d out
java -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Application
