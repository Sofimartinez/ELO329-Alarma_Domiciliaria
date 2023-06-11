#include "central.h"
#include <iostream>
//using namespace std;

Central::Central(QObject *parent)
    : QObject(parent), timer(new QTimer(this)) {
    connect(timer, SIGNAL(timeout()), this, SLOT(checkZones()));
    timer ->start(200);
}
void Central::addNewSensor(Sensor * ps){
    zones.push_back(ps);
}
void Central::checkZones() {
    bool closeZones[2];
    checkCloseZones(closeZones);
    if (closeZones[0] == false || closeZones[1] == false)
        std::cout << "Alguna zona está abierta." << std::endl;
    else
        std::cout << "Zonas cerradas" << std::endl;
}
void Central::checkCloseZones(bool closeZones[]) {
    closeZones[0]= closeZones[1] = true;
    for (uint i=0; i<zones.size(); i++){
        if(!zones[i]->isClose()){
            int zone = zones[i]->getZone();
            closeZones[zone] = false;
        }
    }
}
Central::~Central(){
    delete timer;
}
