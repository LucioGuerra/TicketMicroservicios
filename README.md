# # Proyecto: Sistema de tickets

## Descripción
Este proyecto es una aplicación desarrollada en Java 17 con arquitectura de microservicios, utilizando Maven como gestor de dependencias y Docker para la contenedorización de los servicios.

---

## Requisitos Previos
Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes componentes en tu sistema:

- **Java 17**: Verifica la instalación con:
    ```bash
    java -version
    ```

- **Maven**: Verifica la instalación con:
    ```bash
    mvn -version
    ```

- **Docker**: Verifica la instalación con:
    ```bash
    docker --version
    ```

---

## Ejecución de la Aplicación

1. Clona este repositorio y navega hasta la carpeta raíz del proyecto:
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd <NOMBRE_DE_LA_CARPETA_DEL_REPOSITORIO>
    ```

2. Asigna permisos de ejecución al script `run_all.sh`:
    ```bash
    chmod +x run_all.sh
    ```

3. Ejecuta el script para levantar la aplicación:
    ```bash
    ./run_all.sh
    ```

---

## Notas Adicionales
- Asegúrate de tener Docker en ejecución antes de ejecutar el script.
- El script `run_all.sh` compila el proyecto y levanta los contenedores necesarios para la aplicación.

---

## Contacto
Si tienes alguna duda o problema, abre un **Issue** en este repositorio.

---

¡Listo para ejecutar! 🚀
