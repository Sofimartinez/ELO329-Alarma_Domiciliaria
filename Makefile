JFLAGS = -g
JC = javac
JVM= java
JFX_OPTIONS = --module-path "/Users/sofia/Downloads/javafx-sdk-20.0.1/lib/" --add-modules javafx.controls,javafx.media
FILE="config.txt"


.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(JFX_OPTIONS) $*.java


CLASSES = \
	Stage1.java \
	House.java \
	MagneticSensor.java \
	MagneticSensorView.java \
	Sensor.java \
	State.java \
	SwitchState.java \
	Window.java \
	WindowView.java

MAIN = Stage1

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(JFX_OPTIONS) $(MAIN) $(FILE)

clean:
	$(RM) *.class

