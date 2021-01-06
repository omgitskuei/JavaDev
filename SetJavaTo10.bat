@ECHO off
ECHO Java 10.bat - Setting JAVA_HOME
SET JAVA_HOME=C:\Program Files\Java\jdk-10.0.2
ECHO setting PATH
SET PATH=%JAVA_HOME%\bin;%PATH%
ECHO Display java version
java -version
javac -version
PAUSE