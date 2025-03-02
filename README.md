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

## Configuración de Variables de Entorno

Antes de ejecutar la aplicación, es necesario crear los archivos `.env` y `.env-build` en la carpeta raíz del proyecto con las siguientes configuraciones:

### Archivo `.env`

Este archivo define las variables de entorno utilizadas en la aplicación en un entorno de contenedores:

```env
# MinIO
MINIO_ACCESS_KEY=admin
MINIO_SECRET_KEY=adminadmin
MINIO_URL=minio:9000

ISSUER_URI=https://dev-22vijuiwzcze21d0.us.auth0.com/

# API Gateway
GATEWAY_DIR=./api-gateway/target

# Eureka Service
EUREKA_DIR=./eureka-sv/target
EUREKA_URL=eureka-sv:8761

# Comment Service
COMMENT_DIR=./comment-sv/target
DB_USER=postgres
DB_PASSWORD=root
DB_URL=ticket-db:5432

# Requirement Service
REQUIREMENT_DIR=./requirement-sv/target
KAFKA_URL=kafka-sv:9092

# Traceability Service
TRACEABILITY_DIR=./traceability-sv/target

# Type Service
TYPE_DIR=./type-sv/target

# User Service
USER_DIR=./user-sv/target

# Comment Service
COMMENT_DIR=./comment-sv/target
```

### Archivo `.env-build`

Este archivo se utiliza para entornos de desarrollo en local:

```env
MINIO_ACCESS_KEY=admin
MINIO_SECRET_KEY=admin
MINIO_URL=localhost:9000
MINIO_BUCKET=admin
DB_USER=postgres
DB_PASSWORD=root
DB_URL=localhost:5432
KAFKA_URL=localhost:29092
ISSUER_URI=https://dev-22vijuiwzcze21d0.us.auth0.com/
```

---

## Ejecución de la Aplicación

1. Clona este repositorio y navega hasta la carpeta raíz del proyecto:

   ```bash
   git clone https://github.com/LucioGuerra/TicketMicroservicios.git
   cd TicketMicroservicios
   ```

2. Crea los archivos `.env` y `.env-build` con las configuraciones mencionadas anteriormente.

3. Asigna permisos de ejecución al script `run_all.sh` e `insert_data.sh`:

   ```bash
   chmod +x insert_data.sh
   chmod +x run_all.sh
   ```

4. Ejecuta el script para levantar la aplicación:

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

