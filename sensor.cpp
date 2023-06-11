#include "sensor.h"
#include <iostream>
//using namespace std;

Sensor::Sensor(int z, bool close){
    this->zone = z;
    this->close = close;
}
bool Sensor::isClose() const {
    return close;
}
int Sensor::getZone() const {
    return zone;
}
void Sensor::setClose(bool isClose) {
    this->close = isClose;
    std::cout << "Sensor is "<< (close?"close.":"open.") << std::endl;
}
