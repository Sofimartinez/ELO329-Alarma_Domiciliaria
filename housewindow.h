#ifndef HOUSEWINDOW_H
#define HOUSEWINDOW_H

#include <QMainWindow>
#include <QGraphicsScene>
#include <QGraphicsItemGroup>
#include <QObject>
#include "central.h"

QT_BEGIN_NAMESPACE
namespace Ui { class HouseWindow; }
QT_END_NAMESPACE

class HouseWindow : public QMainWindow
{
    Q_OBJECT

public:
    HouseWindow(Central * central, QWidget *parent = nullptr);
    void addHouseHollow(QGraphicsItemGroup * ); // doors and windows
    void addAlarmHollow(QGraphicsItemGroup *); //siren and central
    ~HouseWindow();
private slots:
    void showMessageDisplay(const QString& message);
private:
    Ui::HouseWindow *ui;
    QGraphicsScene interiorScene;
    QGraphicsScene alarmScene;
};
#endif // HOUSEWINDOW_H
