#include "housewindow.h"
#include "ui_housewindow.h"

HouseWindow::HouseWindow(Central * central, QWidget *parent): QMainWindow(parent), ui(new Ui::HouseWindow)
{
    ui->setupUi(this);
    ui->houseRegion->setScene(&interiorScene);
    ui->alarmRegion->setScene(&alarmScene);
    QObject::connect(ui->armedButton, SIGNAL(clicked()), central, SLOT(armPerimeter()));
    QObject::connect(ui->disarmedButton, SIGNAL(clicked()), central, SLOT(disarm()));
    QObject::connect(central, SIGNAL(setDisplay(QString)), this, SLOT(showMessageDisplay(QString)));
}
void HouseWindow::addHouseHollow(QGraphicsItemGroup * compoundItem){
    interiorScene.addItem(compoundItem);
}
void HouseWindow::addAlarmHollow(QGraphicsItemGroup * compoundItem){
    alarmScene.addItem(compoundItem);
}

void HouseWindow::showMessageDisplay(const QString& message) {
    ui->display4->clear();
    ui->display4->append(message);
}

HouseWindow::~HouseWindow()
{
    delete ui;
}

