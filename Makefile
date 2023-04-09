JFLAGS = -g
JC = javac
JVM= java
FILE="config.txt"

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
	Stage2.java \
	Door.java \
	Window.java \
	MagneticSensor.java \
	Sensor.java \
	SwitchState.java \
	State.java \
	AePlayWave.java \
	Central.java \
	Siren.java

MAIN = Stage2

classes:
	$(CLASSES:.java=.class)

run: $(MAIN)
	$(JVM) $(MAIN) $(FILE)

clean:
	$(RM) *.class
