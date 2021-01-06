@ECHO off
ECHO Java 8.bat - Setting JAVA_HOME
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_121
ECHO setting PATH
SET PATH=%JAVA_HOME%\bin;%PATH%
ECHO Display java version
java -version
javac -version
PAUSE