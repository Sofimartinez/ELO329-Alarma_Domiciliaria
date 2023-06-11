#ifndef WINDOW_H
#define WINDOW_H
#include "magneticsensor.h"

class WindowView; //declaracion incompleta dependencia circular entre clase Window y WindowView
class Window
{
private:
    Window(); //No se puede crear una ventana sin un sensor
public:
    Window(MagneticSensor * sensor, WindowView * view);
    void changeState(); //Cambia el estado entre abierto y cerrado
private:
    MagneticSensor * magneticSensor;
    WindowView * view;
    bool isClose;
};

#endif // WINDOW_H
