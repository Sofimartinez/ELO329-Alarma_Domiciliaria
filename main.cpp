#include "housewindow.h"
#include <QApplication>
#include <iostream>
#include <fstream>
#include <vector>
#include "door.h"
#include "doorview.h"
#include "central.h"
#include "window.h"
#include "windowview.h"
#include <QtWidgets>
//using namespace std;

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    HouseWindow gui;  // gui: Graphical User Interface
    Central central;
    std::ifstream fin;
    int nDoors, nWindows;
    if (argc != 2) {
        std::cout << "Usage: "<<argv[0]<<" <configuration_file>" << std::endl;
        return -1;
    }
    fin.open(argv[1]);
    std::cout << argv[1] << std::endl;
    if (fin.fail()){
        std::cout << "Could not read file" << std::endl;
        return -2;
    }
    std::cout << "Argument:" << argv[1] << std::endl;
    fin >> nDoors;
    fin >> nWindows;
    std::cout << "nDoors:" << nDoors << " nWindows: " << nWindows << std::endl;
    //Creación de puertas
    for( int i=0; i<nDoors; i++) {
        int x, y, angle, zone;
        fin >> x >> y >> angle >> zone;
        MagneticSensor * sensor = new MagneticSensor(zone);
        DoorView * doorView = new DoorView(x,y,angle, sensor->getView());
        new Door(sensor, doorView);
        central.addNewSensor(sensor);
        gui.addHouseHollow(doorView);
    }
    //Creación de ventanas
    for( int i=0; i<nWindows; i++) {
        int x, y, angle, zone;
        fin >> x >> y >> angle >> zone;
        MagneticSensor * sensor = new MagneticSensor(zone);
        WindowView * windowView = new WindowView(x,y,angle, sensor->getView());
        new Window(sensor, windowView);
        central.addNewSensor(sensor);
        gui.addHouseHollow(windowView);
    }
    gui.show();
    return a.exec();
}
