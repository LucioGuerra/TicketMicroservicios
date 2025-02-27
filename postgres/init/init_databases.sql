-- Archivo: init-databases.sql
-- Este script se ejecutará automáticamente en el primer arranque del contenedor,
-- siempre que el directorio de datos esté vacío.

-- Crear comment_db si no existe
SELECT 'CREATE DATABASE comment_db'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'comment_db'
)
\gexec

-- Crear requirement_db si no existe
SELECT 'CREATE DATABASE requirement_db'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'requirement_db'
)
\gexec

-- Crear traceability_db si no existe
SELECT 'CREATE DATABASE traceability_db'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'traceability_db'
)
\gexec

-- Crear type_db si no existe
SELECT 'CREATE DATABASE type_db'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'type_db'
)
\gexec

-- Crear user_db si no existe
SELECT 'CREATE DATABASE user_db'
WHERE NOT EXISTS (
    SELECT 1 FROM pg_database WHERE datname = 'user_db'
)
\gexec




