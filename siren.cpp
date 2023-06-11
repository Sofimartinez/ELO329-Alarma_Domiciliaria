#include "siren.h"
#include "sirenview.h"
#include <iostream>

Siren::Siren(){

}
Siren::Siren(SirenView * view): view(view) {
    isActive = false;
}
void Siren::play() {
    isActive = true;
    view->startBlinking();
}
void Siren::stop() {
    isActive = false;
    view->stopBlinking();
}
