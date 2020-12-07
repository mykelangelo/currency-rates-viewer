INSERT INTO "roles" ("name", "privileged")
VALUES ('admin', true),
       ('free_user', false),
       ('paid_user', true);
INSERT INTO "users" ("email", "hash", "role_name")
VALUES ('mykola.papenko@gmail.com', '$2a$10$9RevR5.JdaPQGEd0L2/Q5eWT9diqbZ4dpsxxT2v4RC22Ym32X0cPq', 'admin');
