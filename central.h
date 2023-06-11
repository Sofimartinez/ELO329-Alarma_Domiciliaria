#ifndef CENTRAL_H
#define CENTRAL_H
#include <vector>
#include "sensor.h"
using namespace std; //needed by vector<>

class Central
{
public:
    Central();
    void addNewSensor(Sensor * ps);
    void checkZones();
private:
    void checkCloseZones(bool closeZones[]);
    vector<Sensor *> zones;  // keep references to all sensors already installed in doors and windows.
};

#endif // CENTRAL_H