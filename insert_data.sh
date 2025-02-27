#!/bin/bash

# Variables de configuración
CONTAINER_NAME="ticket-db"       # Nombre del contenedor Docker que ejecuta PostgreSQL
PSQL_USER="postgres"               # Usuario de la base de datos
PSQL_PASSWORD="root"  # Contraseña del usuario

# Directorio donde se encuentran los scripts SQL
SCRIPTS_DIR="postgres/scripts"

# Ejecutar scripts en la base de datos type_db (para tipos y categorías)
echo "Ejecutando insert_types.sql en type_db..."
docker exec -e PGPASSWORD="$PSQL_PASSWORD" -i "$CONTAINER_NAME" psql -U "$PSQL_USER" -d type_db < "$SCRIPTS_DIR/insert_type.sql"

echo "Ejecutando insert_categories.sql en type_db..."
docker exec -e PGPASSWORD="$PSQL_PASSWORD" -i "$CONTAINER_NAME" psql -U "$PSQL_USER" -d type_db < "$SCRIPTS_DIR/insert_categories.sql"

# Ejecutar scripts en la base de datos comment_db (para requisitos y comentarios)
echo "Ejecutando insert_requirements.sql en comment_db..."
docker exec -e PGPASSWORD="$PSQL_PASSWORD" -i "$CONTAINER_NAME" psql -U "$PSQL_USER" -d requirement_db < "$SCRIPTS_DIR/insert_requirements.sql"

echo "Ejecutando insert_comments.sql en comment_db..."
docker exec -e PGPASSWORD="$PSQL_PASSWORD" -i "$CONTAINER_NAME" psql -U "$PSQL_USER" -d comment_db < "$SCRIPTS_DIR/insert_comments.sql"

# Ejecutar script en la base de datos user_db (para usuarios externos)
echo "Ejecutando insert_outsiderusers.sql en user_db..."
docker exec -e PGPASSWORD="$PSQL_PASSWORD" -i "$CONTAINER_NAME" psql -U "$PSQL_USER" -d user_db < "$SCRIPTS_DIR/insert_outsideusers.sql"

echo "Todos los scripts se han ejecutado correctamente."
