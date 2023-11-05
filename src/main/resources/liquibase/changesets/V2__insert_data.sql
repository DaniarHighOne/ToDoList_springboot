insert into users (name, username, password)
values ('John Doe', 'johndoe@email.com', '$2a$12$oI.qnkVy/X46ZS7GqPMnLeTIIPj3DXD4AQ/aZ.WMzZRPFQVkjhOHO'), --bcrypt as option
       ('Mike Smith', 'mikesmith@email.com', '$2a$12$oI.qnkVy/X46ZS7GqPMnLeTIIPj3DXD4AQ/aZ.WMzZRPFQVkjhOHO');--bcrypt as option

insert into tasks (title, description, status, expiration_date)
values ('Buy chesse', null, 'TODO', '2023-08-27 12:00:00'),
       ('Do homework', 'Math, physics', 'IN_PROGRESS', '2023-08-28 15:00:00'),
       ('Clean rooms', null, 'DONE', null),
       ('Call Mike', 'Ask about code review', 'TODO', '2023-08-29 10:00:00');

insert into users_tasks (task_id, user_id)
values (1,2),
       (2,2),
       (3,2),
       (4,1);

insert into users_role (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');
