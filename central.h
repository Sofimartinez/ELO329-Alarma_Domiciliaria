#ifndef CENTRAL_H
#define CENTRAL_H
#include <QObject>
#include <vector>
#include <QTimer>
#include "sensor.h"
#include "siren.h"
//using namespace std; //needed by vector<>

class Central : public QObject
{
    Q_OBJECT
public:
    explicit Central(Siren * siren, QObject *parent = nullptr);
    void addNewSensor(Sensor * ps);
    ~Central();
public slots:
    void armPerimeter();
    void disarm();
signals:
    void setDisplay(const QString& message);
private slots:
    void checkZones(); //ranura que se llama ante la señal del timer
private:
    void checkCloseZones(bool closeZones[]); //Verifica cada sensor por zona
    std::vector<Sensor *> zones;  // keep references to all sensors already installed in doors and windows.
    QTimer * timer; //señal
    Siren * siren;
    bool isArmed; //estado de la central
};

#endif // CENTRAL_H
