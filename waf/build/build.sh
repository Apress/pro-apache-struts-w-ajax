#!/bin/sh
echo "Build System"
echo "------------------"

if [ x"$JAVA_HOME" = x"" ]; then
 echo "JAVA_HOME environment variable not found"
 echo "Please set the JAVA_HOME environment variable to the location of your JVM"
 exit 1
fi

if [ x"$ANT_HOME" = x"" ]; then
 echo "ANT_HOME environment variable not found"
 echo "Please set the ANT_HOME environment variable to the location of the Ant lib directory"
 exit 1
fi

LIB_JARS=""

for f in `find $ANT_HOME/lib -iname \*.jar`
do
LIB_JARS=$LIB_JARS:$f
done

export CLASSPATH=$CLASSPATH$LIB_JARS

echo "Building with CLASSPTH:"
echo $CLASSPATH

$JAVA_HOME/bin/java org.apache.tools.ant.Main -buildfile build.xml $1 $2 $3 $4 $5