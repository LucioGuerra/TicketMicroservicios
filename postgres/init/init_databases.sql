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


INSERT INTO comment_db.public.comment (
    created_at,
    deleted,
    description,
    requirement_id,
    subject,
    updated_at,
    user_id
)
SELECT
    NOW() - (interval '1 day' * random()) AS created_at, -- fecha aleatoria en las últimas 24 horas
    (random() < 0.5) AS deleted, -- booleano aleatorio
    (ARRAY[
        'Este es un comentario sobre la funcionalidad del sistema.',
        'La implementación requiere revisión.',
        'Se ha detectado un comportamiento inesperado en el módulo de pagos.',
        'El sistema responde de manera óptima en condiciones de alta carga.',
        'Es necesario realizar ajustes en la configuración del servidor.'
        ])[floor(random() * 5)::int + 1] AS description,
    floor(random() * 100)::int AS requirement_id, -- requirement_id aleatorio entre 0 y 99
    (ARRAY[
        'Revisión de sistema',
        'Ajuste requerido',
        'Notificación de error',
        'Alerta de rendimiento',
        'Observación técnica'
        ])[floor(random() * 5)::int + 1] AS subject,
    NOW() - (interval '1 day' * random()) AS updated_at, -- fecha aleatoria en las últimas 24 horas
    floor(random() * 50)::int AS user_id -- user_id aleatorio entre 0 y 49
FROM generate_series(1, 30);


