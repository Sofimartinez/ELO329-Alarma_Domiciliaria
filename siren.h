#ifndef SIREN_H
#define SIREN_H

class SirenView; //declaración incompleta
class Siren
{
private:
    Siren();
public:
    Siren(SirenView * view);
    void play(); //Activa la alarma cambiando el color y estado
    void stop(); //Desactiva la alarma cambiando el color y estado
private:
    SirenView * view;
    bool isActive;
};

#endif // SIREN_H
