#!/bin/sh
mkdir -p out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Object.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Workspace.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Blob.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Entry.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Tree.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Commit.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Database.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Application.java -d out
java -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out Application
