## App de Tareas
<p align="center">
  <img src="https://raw.githubusercontent.com/AlvaroCoder/AppTareario/master/src/Imagenes/BannerAplicaciondeTareas%20.png">
</p>

# Demostración

<p align="center">
  <img src="https://raw.githubusercontent.com/AlvaroCoder/AppTareario/master/src/Imagenes/GifAppTareas.gif">
</p>

## Descripcion
Aplicación de escritorio usando [JAVAX SWING](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) y [MYSQL](https://www.mysql.com/). Sirve para que organices las tareas del día. Puedes guardar tus tareas,tantas como quieras, cambiar de estado y marcarlas como hechas o "terminadas".

## Prerequisitos de uso del código
Para usar el código es necesario que tengas [MYSQL](https://www.mysql.com/) instalado, puedes usar cualquier interfaz que te permita manejar las bases de datos. En mi caso use 
[WORKBENCH](https://www.mysql.com/products/workbench/) pero puedes usar cualquiera. 
Además deberas crear una base de datos llamada "bd_tareas" , puedes usar <code>CREATE DATABASE bd_tarea;</code>
Luego crear una tabla de tareas usando:
```
    CREATE TABLE tareas(
      idtareas INT NOT NULL AUTO_INCREMENT,
      nombre VARCHAR(100) NOT NULL,
      descripcion VARCHAR(300),
      estado INT,
      fechaDia INT NOT NULL,
      fechaMes INT NOT NULL,
      fechaAño INT NOT NULL,
      PRIMARY KEY(idtareas)
    );
```
Ahora ya puedes usar mi código 😊.
