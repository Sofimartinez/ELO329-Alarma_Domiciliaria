#ifndef DOORVIEW_H
#define DOORVIEW_H

#include <QGraphicsItemGroup>
#include <QGraphicsRectItem>
#include <QGraphicsPolygonItem>
#include <QGraphicsSceneMouseEvent>
#include "door.h"
#include "magneticsensorview.h"

class DoorView : public QGraphicsItemGroup
{
public:
    explicit DoorView(int x, int y, int angle, MagneticSensorView * msView); //explicit: el compilar no haga cambio de tipo automatico para os atributos que hay en el contructor
    void setDoorModel(Door * model);
    void setOpen(); //Cambia la posición de la puerta y el iman a abierto
    void setClose(); //Cambia la posición de la puerta y el iman a cerrado
    virtual void mousePressEvent(QGraphicsSceneMouseEvent *); //evento que abre o cierra la puerta
    ~DoorView();
private:
    void installMagneticSensor(MagneticSensorView & msView); //posición de los elementos gráficos
    void makeDoorView(); //genera el grupo de elementos que componen la puerta
    Door * model;
    QGraphicsRectItem * doorPanel; //parte movible
    QGraphicsPolygonItem * switchPillar; //el pilar del switch magnetico
    QGraphicsRectItem * magnet; //iman que se mueve con la puerta (se tiene que mover con la puerta)
};

#endif // DOORVIEW_H
