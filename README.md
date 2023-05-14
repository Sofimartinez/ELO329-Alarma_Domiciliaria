## Archivos que componen la tarea

* **Etapa 1 Una ventana (sin central ni sirena)**: 
Stage1.java, House.java, MagneticSensor.java, MagneticSensorView.java, Sensor.java, State.java, SwitchState.java, Window.java y WindowView.java 

* **Etapa 2 Propiedad con dos puertas, dos ventanas, central y sirena**: Stage2.java, House.java, MagneticSensor.java, MagneticSensorView.java, Sensor.java, State.java, SwitchState.java, Window.java, WindowView.java, Central.java, Centralview.java, Door.java,DoorView.java, Siren.java y SirenView.java 

* **Etapa 3 Agrega Detectores PIR y una persona movible arrastrando el mouse**: Stage3.java, House.java, MagneticSensor.java, MagneticSensorView.java, Sensor.java, State.java, SwitchState.java, Window.java, WindowView.java, Central.java, Centralview.java, Door.java,DoorView.java, Siren.java y SirenView.java, Person.java, PersonView.java, PIR_Detecter.java y PIR_DetecterView.java 

* **Etapa 4 Cumplimiento de las funcionalidades de la alarma según sección 4**: Stage4.java, House.java, MagneticSensor.java, MagneticSensorView.java, Sensor.java, State.java, SwitchState.java, Window.java, WindowView.java, Central.java, Centralview.java, Door.java,DoorView.java, Siren.java y SirenView.java, Person.java, PersonView.java, PIR_Detecter.java y PIR_DetecterView.java

## Cómo compilar cada etapa

*  Etapa 1:

    Para hacer la compilación de esta etapa solo es necesario el módulo de javaFx *javafx.controls* se puede utilizar el archivo Makefile adjunto en la rama de git *Stage1-Tarea2* ejecutando:

    ` make ` para compilar

    ` make  make JFX_OPTIONS= --module-path "/path/to/javafx/lib" --add-modules javafx.controls ` para cambiar la ruta de los módulos de javafx (/path/to/javafx/lib debe cambiarse por la ruta que corresponda)

    ` make run ` ejecutará la etapa 1 de la tarea 

    ` make clean ` borrará los archivos .class
    
    Otra alternativa es escribir el siguente comando desde la terminal para compilar 
    ` javac --module-path /path/to/javafx/lib" --add-modules javafx.controls Stage1.java ` Y posteriomente 
 ` java --module-path /path/to/javafx/lib" --add-modules javafx.controls Stage1 config.txt ` para ejecutar

* Etapa 2:

    Similar a la etapa anterior, pero para este es necesario incluir el módulo de javaFX *javafx.media* para poder ejecutar el sonido de la sirena cuando la seguridad haya sido violada al estar la central armada. Para esto se puede utilizar el archivo Makefile que se encuentra en la rama *Stage2-Tarea2* y ejecutar los siguientes comandos:

     ` make ` para compilar

    ` make  make JFX_OPTIONS= --module-path "/path/to/javafx/lib" --add-modules javafx.controls,javafx.media ` para cambiar la ruta de los módulos de javafx (/path/to/javafx/lib debe cambiarse por la ruta que corresponda)

    ` make run ` ejecutará la etapa 2 de la tarea 

    ` make clean ` borrará los archivos .class

    Alternativamente se puede ejecutar desde la terminal ` javac --module-path /path/to/javafx/lib" --add-modules javafx.controls Stage2.java ` y posteriomente 
 ` java --module-path /path/to/javafx/lib" --add-modules javafx.controls,javafx.media Stage2 config.txt `

* Etapa 3 y 4:

    No es necesario incluir ningún módulo adicional de javaFX, por lo que se sigue el mismo patrón descrito para la etapa 2 cambiando el nombre del archivo que contiene el método `main` de acuerdo con la etapa es decir `Stage3` o `Stage4`, cada etapa se encuentra en la rama *Stage3-Tarea2* y *Stage4-Tarea2* respectivamente.
    
## Ejecutar en IntelliJ
1. Importar el projecto a IntelliJ: Cada etapa debe ser ejecutada por separado, por lo que para analizar cada etapa se deben abrir las carpetas de cada etapa por separado en la opción `File>Open`.
2. Agregar las librerías del JDK: Una vez se tenga abierta la carpeta de alguna etapa, en la carpeta `src` que en este caso tiene el nombre de, por ejemplo, `ELO329-Alarma_Domiciliaria-Stage4-Tarea2`, se debe precionar clic derecho y seleccionar la opción `Open Module Settings`. En este punto se deben agregar las librerías disponibles en la carpeta `lib` disponible en la ubicación del archivo del JDK.
3. Configuraciones de arranque: Para ejecutar correctamente alguna etapa se debe configurar el arranque en el apartado `Build`. Una vez dentro, se selecciona el JDK con el cual se va a trabajar (idealmente JDK 20), luego se agraga la opción de `Add VM options` en donde se debe ingresar la dirección de las librerías de JavaFX con el siguiente formato `--module-path "{path}\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.media`. Luego, se selecciona la clase fuente que en estos casos serían `Stage1`, `Stage2`, `Stage3` o `Stage4`, para luego finalizar agregando el nombre del archivo `config.txt` a la casilla de `Program Parameters`.
4. Ubicar el archivo `config.txt`: Antes de iniciar el programa, se debe mover el archivo `config.txt` fuera de la carpeta sourse para que IntelliJ lo pueda reconocer.
Una vez se completan estos pasos, se puede ejecutar el programa en IntelliJ.

## Bonus realizado

Se realizó al opción A de bonus que se encuentra implementada en la etapa 4 de la tarea, la cual consistia de dos partes:
* **A1:** "Modifique las clases necesarias para que, al posicionar el mouse sobre una ventana o puerta, ésta cambie de color. Así el usuario sabrá que está en condiciones de cerrarla o abrirla." Para este bonus se modificaron los archivos `DoorView.java` y `WindowView.java` donde añadieron dos eventos *setOnMouseEntered* y *setOnMouseExited* los cuales permiten cambiar el color de la ventana y puerta cuando el mouse se encuentra sobre estos, puntualmente sobre la parte movible de la puerta y ventana, cuando el mouse es quitado estos vuelven a su color original.
* **A2:** "Agregue un menú de contexto a cada sensor para permitir cambiar la zona a la que pertenece." Para dicho bonus se modificó el archivo `DoorView.java`, `WindowView.java` y `PIR_DetectorView` donde se incorporó el evento *setOnContextMenuRequested* el cual permite identificar cuando se realizó click derecho sobre todo el gráfico que corresponde a la puerta, ventana y PIR para poder mostrar un menú contextual que da la opción de cambiar la zona y desde la cual se desprenden 3 menu items que corresponde a las 3 zonas disponibles.

## Adicional

En la etapa 4 existen dos formar de eliminar personas, una de ellas se encuentra la sección de la central donde al elegir dicha opción de elimina a la última persona añadida y la segunda manera corresponde a realizar click derecho sobre la persona y seleccionar la opción *delete* la cual eliminará a la persona seleccionada.



