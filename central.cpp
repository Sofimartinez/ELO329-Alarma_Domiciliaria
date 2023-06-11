#include "central.h"
#include <iostream>
//using namespace std;

Central::Central(Siren * siren, QObject *parent)
    : QObject(parent), timer(new QTimer(this)) {
    connect(timer, SIGNAL(timeout()), this, SLOT(checkZones()));
    timer ->start(200);
    isArmed=false;
    this->siren = siren;
}
void Central::addNewSensor(Sensor * ps){
    zones.push_back(ps);
}
void Central::checkZones() {
    if(isArmed){
        bool closeZones[2];
        checkCloseZones(closeZones);
        if (closeZones[0] == false || closeZones[1] == false)
            siren->play();
    }
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
void Central::armPerimeter(){
    //Si no esta armada previamente se arma
    if(!isArmed){
        bool closeZones[2];
        checkCloseZones(closeZones);
        if(closeZones[0] && closeZones[1]){
            isArmed = true;
            emit setDisplay("Armed perimeter");
        }
        else{
            emit setDisplay(QString("Open zone(s): %1, %2").arg(closeZones[0]?"-":"0").arg(closeZones[1]?"-":"1"));
        }
    }
}
void Central::disarm(){
    isArmed = false;
    siren->stop();
    emit setDisplay("Disarmed perimeter");
}
Central::~Central(){
    delete timer;
}
