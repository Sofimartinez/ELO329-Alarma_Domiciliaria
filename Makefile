JFLAGS = -g
JC = javac
JVM= java
JFX_OPTIONS = --module-path "/path/to/javafx/lib" --add-modules javafx.controls,java.media
FILE="config.txt"

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(JFX_OPTIONS) $*.java

CLASSES = \
	Stage3.java \
	Door.java \
	DorrView.java \
	Window.java \
	MagneticSensor.java \
	MagneticSensorView.java \
	Sensor.java \
	SwitchState.java \
	Central.java \
	CentralView.java \
	House.java \
	Person.java \
	PersonView.java \
	PIR_Detecter.java \
	PIR_DetecterView.java \
	Siren.java \
	SirenView.java \
	Window.java \
	WindowView.java \
	State.java

MAIN = Stage3

classes:$(CLASSES:.java=.class)

run: $(MAIN)
	$(JVM) $(JFX_OPTIONS) $(MAIN) $(FILE)

clean:
	$(RM) *.class
