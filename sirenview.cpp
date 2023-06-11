#include "sirenview.h"
#include <QBrush>
#include <iostream>

SirenView::SirenView(){
    makeSirenView();
}
void SirenView::makeSirenView(){
    siren=new QGraphicsPolygonItem(this);
    QPolygonF p;
    p.append(QPoint(0,30));
    p.append(QPoint(0,50));
    p.append(QPoint(40,80));
    p.append(QPoint(40,0));
    siren->setPolygon(p);
    siren->setBrush(Qt::green);
    addToGroup(siren);
}
void SirenView::startBlinking(){
    siren->setBrush(Qt::red);
}
void SirenView::stopBlinking(){
    siren->setBrush(Qt::green);
}
SirenView::~SirenView(){
    delete siren;
}
