#ifndef SIRENVIEW_H
#define SIRENVIEW_H

#include <QGraphicsItemGroup>
#include <QGraphicsPolygonItem>
#include "siren.h"

class SirenView : public QGraphicsItemGroup
{
public:
    SirenView();
    void startBlinking(); //Cambia el color de la sirena a rojo
    void stopBlinking(); //Cambia el color de la sirena a verde
    ~SirenView(); //delete
private:
    void makeSirenView();
    QGraphicsPolygonItem * siren; //sirena
};

#endif // SIRENVIEW_H
