#ifndef MAGNETICSENSOR_H
#define MAGNETICSENSOR_H
#include "sensor.h"
#include "magneticsensorview.h"

class MagneticSensor: public Sensor
{
public:
    MagneticSensor(int z=0);
    ~MagneticSensor(); //destructor
    void setSensorOpen(); //Cambia el estado y color del switch a abierto
    void setSensorClose(); //Cambia el estado y color del switch a cerrado
    MagneticSensorView * getView();
private:
    MagneticSensorView * view;
};

#endif // MAGNETICSENSOR_H
