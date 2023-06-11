#ifndef WINDOWVIEW_H
#define WINDOWVIEW_H

#include <QGraphicsItemGroup>
#include <QGraphicsRectItem>
#include <QGraphicsPolygonItem>
#include <QGraphicsSceneMouseEvent>
#include "window.h"
#include "magneticsensorview.h"

class WindowView : public QGraphicsItemGroup
{
public:
    explicit WindowView(int x, int y, int angle, MagneticSensorView * msView);
    void setWindowModel(Window * model);
    void setOpen(); //Cambia la posición de la puerta y el iman a abierto
    void setClose(); //Cambia la posición de la puerta y el iman a cerrado
    virtual void mousePressEvent(QGraphicsSceneMouseEvent *); //evento que abre o cierra la puerta
    ~WindowView();
private:
    void installMagneticSensor(MagneticSensorView & msView); //posición de los elementos gráficos
    void makeWindowView(); //genera el grupo de elementos que componen la puerta
    Window * model;
    QGraphicsRectItem * windowPanel; //parte movible
    QGraphicsRectItem * switchPillar; //el pilar del switch magnetico
    QGraphicsRectItem * magnet; //iman que se mueve con la puerta (rectangulo, se tiene que mover con la puerta)
};

#endif // WINDOWVIEW_H
