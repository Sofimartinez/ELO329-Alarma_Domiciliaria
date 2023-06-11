#ifndef DOOR_H
#define DOOR_H
#include "magneticsensor.h"

class DoorView; //declaracion incompleta dependencia circular entre clase Door y DoorView
class Door
{
private:
    Door(); // door creation without sensor is not allowed.
public:
    Door(MagneticSensor * sensor, DoorView * view);
    void changeState(); //Cambia el estado entre abierto y cerrado
private:
    MagneticSensor * magneticSensor;
    DoorView * view;
    bool isClose;
};

#endif // DOOR_H
