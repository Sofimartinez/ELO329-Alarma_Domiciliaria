## Archivos que componen la tarea

* **Etapa 1 Sólo puertas (sin interfaz gráfica ni central)**: 
main,cpp, door.cpp, door.h, magnetic_sensor.cpp, magnetic_sensor.h sensor.cpp y sensor.h 

* **Etapa 2 : Puertas y ventanas más la Central (sin interfaz gráfica ni sirena)**: 
main,cpp, door.cpp, door.h, magnetic_sensor.cpp, magnetic_sensor.h sensor.cpp, sensor.h, central.cpp, central.h, window,cpp y window.h

* **Etapa 3 Puertas y ventanas más la Central (con interfaz gráfica sin sirena)**: 
main,cpp, door.cpp, door.h, magnetic_sensor.cpp, magnetic_sensor.h sensor.cpp, sensor.h, central.cpp, central.h, window,cpp, window.h, doorview.cpp, doorview.h, house.cpp, house.h, housewindow.cpp, housewindow.h, housewindow.ui, magneticsensorview.cpp, magneticsensorview.h, windowview.cpp y windowview.h

* **Etapa 4 Cumplimiento de lo esperado para la descripción de la tarea**: 
main,cpp, door.cpp, door.h, magnetic_sensor.cpp, magnetic_sensor.h sensor.cpp, sensor.h, central.cpp, central.h, window,cpp, window.h, doorview.cpp, doorview.h, house.cpp, house.h, housewindow.cpp, housewindow.h, housewindow.ui, magneticsensorview.cpp, magneticsensorview.h, windowview.cpp, windowview.h, siren.cpp, siren.h, sirenview.cpp y sirenview.h

## Cómo compilar cada etapa

*  Etapa 1 y 2:

    Para hacer la compilación y ejecución de estas etapas se tienen que utilizar los archivos que se encuentran en la rama de git *Stage1-Tarea3* y *Stage2-Tarea3* respectivamente ejecutando el siguiente comando:

    ` g++ *.cpp -o tarea3 ` para compilar todos los archivos y generar el ejecutable llamado *tarea3*

    ` ./tarea3 config.txt  ` para ejecutar el archivo tarea3.exe creado anteriormente y además incorporando como argumento el archivo de confiuracion *config.txt* de las puertas y ventanas

* Etapa 3 y 4:

    Para estas estas etapas se hace el uso de *Qt Creator* en el cual para poder hacer uso del cada programa se tienen que usaqr los archivos en las ramas *Stage3-Tarea3* y *Stage4-Tarea3* respectivamente y seleccionar el archivo *Stage3HelpCode.pro* para la etapa 3 y *Stage4.pro* para la etapa 4, esto abrirá un proyecto en Qt Creator en donde es necesario configurar el argumento antes de ejecutar. Dirigiendose a `Projects > Run > Command line arguments` e indicar la dirección de la carpeta completa en donde se encuentra el archivo "config.txt", una vez configurado se puede ejecutar el programa. 