INSERT INTO requirement_db.public.requirement (
    subject,
    code,
    description,
    state,
    priority,
    creator_id,
    assignee_id,
    category_id,
    type_id,
    is_deleted,
    created_at,
    updated_at
) VALUES
      ('Requisito 1', 'REQ-000000000000001', 'Descripción del requisito 1', 'OPEN', 'MEDIUM', 101, NULL, 1, 1, FALSE, NOW(), NOW()),
      ('Requisito 2', 'REQ-000000000000002', 'Descripción del requisito 2', 'ASSIGNED', 'HIGH', 102, 201, 2, 2, FALSE, NOW(), NOW()),
      ('Requisito 3', 'REQ-000000000000003', 'Descripción del requisito 3', 'CLOSED', 'LOW', 103, 202, 3, 1, TRUE, NOW(), NOW()),
      ('Requisito 4', 'REQ-000000000000004', 'Descripción del requisito 4', 'OPEN', 'URGENT', 104, 203, 1, 3, FALSE, NOW(), NOW()),
      ('Requisito 5', 'REQ-000000000000005', 'Descripción del requisito 5', 'ASSIGNED', 'MEDIUM', 105, NULL, 2, 2, FALSE, NOW(), NOW()),
      ('Requisito 6', 'REQ-000000000000006', 'Descripción del requisito 6', 'CLOSED', 'HIGH', 106, 204, 3, 3, TRUE, NOW(), NOW()),
      ('Requisito 7', 'REQ-000000000000007', 'Descripción del requisito 7', 'OPEN', 'LOW', 107, NULL, 1, 1, FALSE, NOW(), NOW()),
      ('Requisito 8', 'REQ-000000000000008', 'Descripción del requisito 8', 'ASSIGNED', 'URGENT', 108, 205, 2, 2, FALSE, NOW(), NOW()),
      ('Requisito 9', 'REQ-000000000000009', 'Descripción del requisito 9', 'CLOSED', 'MEDIUM', 109, 206, 3, 1, TRUE, NOW(), NOW()),
      ('Requisito 10', 'REQ-000000000000010', 'Descripción del requisito 10', 'OPEN', 'HIGH', 110, NULL, 1, 3, FALSE, NOW(), NOW());
