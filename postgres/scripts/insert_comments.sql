INSERT INTO comment_db.public.comment (
    created_at, 
    deleted, 
    description, 
    requirement_id, 
    subject, 
    updated_at, 
    user_id
) VALUES 
    (NOW(), FALSE, 'Comentario de prueba 1', 1, 'Asunto 1', NOW(), 1),
    (NOW(), FALSE, 'Comentario de prueba 2', 2, 'Asunto 2', NOW(), 2),
    (NOW(), FALSE, 'Comentario de prueba 3', 1, 'Asunto 3', NOW(), 3),
    (NOW(), FALSE, 'Comentario de prueba 4', 3, 'Asunto 4', NOW(), 4),
    (NOW(), FALSE, 'Comentario de prueba 5', 2, 'Asunto 5', NOW(), 5),
    (NOW(), FALSE, 'Comentario de prueba 6', 1, 'Asunto 6', NOW(), 6),
    (NOW(), FALSE, 'Comentario de prueba 7', 3, 'Asunto 7', NOW(), 7),
    (NOW(), FALSE, 'Comentario de prueba 8', 2, 'Asunto 8', NOW(), 8),
    (NOW(), FALSE, 'Comentario de prueba 9', 1, 'Asunto 9', NOW(), 9),
    (NOW(), FALSE, 'Comentario de prueba 10', 3, 'Asunto 10', NOW(), 1);
