# TravelSimulation
A school project about discrete event simulation. This code simulates visitors,
who are travelling around cities and visiting sights.

# Installing the DESMO-J library
We have to download the file called [desmoj-2.4.2-demo-bin-gui.jar]
(http://sourceforge.net/projects/desmoj/files/desmoj/2.4.2/desmoj-2.4.2-demo-bin-gui.jar) from the
[DESMO-J project website](http://sourceforge.net/).

## Installing to our local maven repository
We should issue the corresponding maven command, similar to that  
```
mvn install:install-file -Dfile=C:\Users\youruser\Downloads\desmoj-2.4.2-demo-bin-gui.jar -DgroupId=desmoj -DartifactId=desmoj -Dversion=2.4.2 -Dpackaging=jar  
```  
to install the library correctly, referenced in our pom.xml.
Change the ```-Dfile=``` parameter accordingly to your filesystem.

# To build an executable jar
Use the following maven command:  
```
clean compile assembly:single
```