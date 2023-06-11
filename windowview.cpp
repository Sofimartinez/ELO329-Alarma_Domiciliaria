#include "windowview.h"
#include "window.h"
#include <QBrush>

WindowView::WindowView(int x, int y, int angle, MagneticSensorView * mv){
    makeWindowView();
    mv->setParentItem(this);
    installMagneticSensor(*mv);
    magnet = &(mv->getMagnetView());
    QTransform transform;
    transform.translate(x,y);
    transform.rotate(angle);
    setTransform(transform);
}

void WindowView::makeWindowView(){
    QGraphicsRectItem * origenPillar = new QGraphicsRectItem(0, 0, 20, 20, this); //dimensiones de la puerta
    origenPillar->setBrush(Qt::blue); //color del pilar
    switchPillar=new QGraphicsRectItem(180,0,20,20,this);
    switchPillar->setBrush(Qt::blue);
    QGraphicsRectItem * fixedWindow = new QGraphicsRectItem(21,4,82,6);
    fixedWindow->setBrush(Qt::blue);
    windowPanel = new QGraphicsRectItem(97, 11, 82, 6, this); //dimensiones de la ventana movible
    windowPanel->setBrush(Qt::blue);
    windowPanel->setTransformOriginPoint(windowPanel->rect().left(), windowPanel->rect().bottom());
    addToGroup(origenPillar); //se agrega el item al grupo
    addToGroup(switchPillar);
    addToGroup(fixedWindow);
    addToGroup(windowPanel);
}
void WindowView::setWindowModel(Window * m){
    model=m;
}
void WindowView::installMagneticSensor(MagneticSensorView & mv){
    mv.getMagnetView().setRect(windowPanel->rect().right()-mv.getMagnetView().rect().width(),
                               windowPanel->rect().bottom(),
                               mv.getMagnetView().rect().width(),
                               mv.getMagnetView().rect().height());
    mv.getSwitchView().setRect(switchPillar->boundingRect().x()+switchPillar->boundingRect().width()/2,
                               switchPillar->boundingRect().height(),
                               mv.getSwitchView().rect().width(),
                               mv.getSwitchView().rect().height());
    mv.getMagnetView().setTransformOriginPoint(windowPanel->rect().left(), windowPanel->rect().bottom());
    addToGroup(&mv.getMagnetView());
    addToGroup(&mv.getSwitchView());
}
void WindowView::setOpen(){
    magnet->setPos(-75,0);
    windowPanel->setPos(-75,0);
}
void WindowView::setClose(){
    magnet->setPos(0,0);
    windowPanel->setPos(0,0);
}
void WindowView::mousePressEvent(QGraphicsSceneMouseEvent * event){
    if (model!= NULL && event->button()==Qt::LeftButton) //si se presiona con el boton izquierdo
        model->changeState();
}
WindowView::~WindowView(){
    delete windowPanel;
    delete switchPillar;
}
