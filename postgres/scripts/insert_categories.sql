INSERT INTO type_db.public.category (
    description,
    type_id,
    deleted,
    created_at,
    updated_at
) VALUES
      ('Categoría de prueba 1', 1, FALSE, NOW(), NOW()),
      ('Categoría de prueba 2', 2, FALSE, NOW(), NOW()),
      ('Categoría de prueba 3', 3, TRUE, NOW(), NOW()),
      ('Categoría de prueba 4', 1, FALSE, NOW(), NOW()),
      ('Categoría de prueba 5', 2, TRUE, NOW(), NOW()),
      ('Categoría de prueba 6', 3, FALSE, NOW(), NOW()),
      ('Categoría de prueba 7', 1, TRUE, NOW(), NOW()),
      ('Categoría de prueba 8', 2, FALSE, NOW(), NOW()),
      ('Categoría de prueba 9', 3, TRUE, NOW(), NOW()),
      ('Categoría de prueba 10', 1, FALSE, NOW(), NOW());
