#include "centralview.h"
#include <QHBoxLayout>
#include <QVBoxLayout>
#include <QGraphicsRectItem>
#include <QTextFormat>
#include <QObject>
#include <iostream>
#include "sirenview.h"

CentralView::CentralView(SirenView * siren){
    display = new QLabel();
    display->setText("Disarmed");
    display->setAlignment(Qt::AlignCenter);
    display->setFrameStyle(QFrame::Panel);

    aBtn = new QPushButton("A");
//    QObject::connect(aBtn,  SIGNAL(clicked()), this, SLOT(armAll()));
    dBtn = new QPushButton("D");

    QWidget *widget = new QWidget;
    QHBoxLayout *layoutH = new QHBoxLayout();
//    layoutH->addWidget(siren);
    layoutH->addWidget(aBtn);
    layoutH->addWidget(dBtn);
    layoutH->setSpacing(5);
    QVBoxLayout *layoutV = new QVBoxLayout();
    layoutV->addWidget(display);
    layoutV->addLayout(layoutH);
    layoutV->setSpacing(15);
    widget->setLayout(layoutV);
    widget->show();
//    addToGroup(layoutV);
}
void CentralView::armAll(){
    std::cout << "arm";
    display->setText("Armed");
}
void CentralView::disarm(){
    std::cout << "disarm";
    display->setText("Disarm");
}

