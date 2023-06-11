#ifndef CENTRALVIEW_H
#define CENTRALVIEW_H

#include <QGraphicsItemGroup>
#include <QLabel>
#include <QPushButton>
#include "sirenview.h"

class CentralView : public QGraphicsItemGroup
{
public:
    CentralView(SirenView * siren);
private slots:
    void armAll();
    void disarm();
private:
    QLabel * display;
    QPushButton * aBtn;
    QPushButton * dBtn;
    SirenView * siren;
};

#endif // CENTRALVIEW_H
