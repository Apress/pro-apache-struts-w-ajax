@echo off

set ANT_HOME=c:\java\apache-ant-1.6.5
set JAVA_HOME=C:\java\jdk1.5.0_02

echo Build System
echo ------------

rem -----------------------------------------
rem Set Environmental variables for the Build
rem -----------------------------------------


rem set the ant home directory - where ant is installed


rem ===============================================
rem check for existence of JAVA_HOME
if "%JAVA_HOME%" == "" goto no_java_home

rem ===============================================
rem check for existence of ANT_HOME
if "%ANT_HOME%" == "" goto no_ant_home

rem ===============================================
set LIB_JARS=

rem add all jar files in %ANT_HOME% directory to LIB_JARS
for %%i in (%ANT_HOME%\lib\*.jar) do call cpappend.bat %%i


rem ===============================================
set CLASSPATH=

rem ant requires tools.jar in the classpath
set CLASSPATH=%CLASSPATH%;%JAVA_HOME%\lib\tools.jar

rem add LIB_JARS to CLASSPATH
set CLASSPATH=%CLASSPATH%;%LIB_JARS%


rem ===============================================
echo Building with classpath %CLASSPATH%
echo Starting Ant...

%JAVA_HOME%\bin\java org.apache.tools.ant.Main -buildfile build.xml %1 %2 %3 %4 %5

goto end

:no_java_home

echo ERROR: JAVA_HOME not found in your environment.
echo Please, set the JAVA_HOME variable in your environment to match the
echo location of the Java Virtual Machine you want to use.

:no_ant_home

echo ERROR: ANT_HOME not found in your environment.
echo Please, set the ANT_HOME variable in your environment to match the
echo location of the Ant lib directory.

:end


pause
