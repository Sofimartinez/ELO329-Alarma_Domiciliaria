#ifndef MAGNETICSENSORVIEW_H
#define MAGNETICSENSORVIEW_H

#include <QGraphicsItemGroup>

class MagneticSensorView : public QGraphicsItemGroup
{
public:
    MagneticSensorView();
    void setCloseView(); //Cambia el color del switch a rojo
    void setOpenView(); //Cambia el color del switch a verde
    QGraphicsRectItem & getSwitchView();
    QGraphicsRectItem & getMagnetView();
private:
    QGraphicsRectItem switchView, magnetView;
};

#endif // MAGNETICSENSORVIEW_H
