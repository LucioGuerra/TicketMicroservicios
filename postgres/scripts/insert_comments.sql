INSERT INTO comment_db.public.comment (
    created_at, 
    deleted, 
    description, 
    requirement_id, 
    subject, 
    updated_at, 
    user_id
) VALUES 
    (NOW(), FALSE, 'Comentario de prueba 1', 1, 'Asunto 1', NOW(), 101),
    (NOW(), FALSE, 'Comentario de prueba 2', 2, 'Asunto 2', NOW(), 102),
    (NOW(), FALSE, 'Comentario de prueba 3', 1, 'Asunto 3', NOW(), 103),
    (NOW(), FALSE, 'Comentario de prueba 4', 3, 'Asunto 4', NOW(), 104),
    (NOW(), FALSE, 'Comentario de prueba 5', 2, 'Asunto 5', NOW(), 105),
    (NOW(), FALSE, 'Comentario de prueba 6', 1, 'Asunto 6', NOW(), 106),
    (NOW(), FALSE, 'Comentario de prueba 7', 3, 'Asunto 7', NOW(), 107),
    (NOW(), FALSE, 'Comentario de prueba 8', 2, 'Asunto 8', NOW(), 108),
    (NOW(), FALSE, 'Comentario de prueba 9', 1, 'Asunto 9', NOW(), 109),
    (NOW(), FALSE, 'Comentario de prueba 10', 3, 'Asunto 10', NOW(), 110);
