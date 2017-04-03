# TravelSimulation
A school project intended to demonstrate the DESMO-J library
discrete event simulation capabilities. This code simulates visitors,
who are travelling around 3 cities, visiting various sights and
spending money then returning home.

![Screenshot of the software](https://github.com/bogolyandras/TravelSimulation/blob/master/Screenshot.png "Logo Title Text 1")

# The DESMO-J library
The desmoj library is included in the libs folder, however you can
download it form the [DESMO-J project website](http://desmoj.sourceforge.net)
or directly from the following link: 
http://sourceforge.net/projects/desmoj/files/desmoj/2.4.2/desmoj-2.4.2-demo-bin-gui.jar

## Building from sources
Make sure we have Java 8 support on our system and the JAVA_HOME
environmental variable has been set correctly. We can issue the
~~~
gradlew jar
~~~
command to build the JAR artifact. We will find the TravelSimulation-1.0.0-RELEASE.jar in the build/libs dir,
so issuing the command
~~~
java -jar ./build/libs/TravelSimulation-1.0.0-RELEASE.jar
~~~
will start the software.