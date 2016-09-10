delete from recognition;
delete from user;
delete from team;

insert into team (id, name)  values (1, 'Mystic'), (2, 'Instinct'), (3, 'Valor');

insert into user (id, username, first_name, last_name, email,  activated, is_admin, team_id, password)
VALUES
(1, 'nturner', 'Nathan', 'Turner', 'nturner@example.com', 1 , 0, 1, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(2, 'fcarr', 'Frank', 'Carr', 'fcarr@example.com', 1 ,0, 1, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(3, 'smcgrath', 'Sam', 'McGrath', 'smcgrath@example.com', 1 ,0, 1, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(4, 'mquinn', 'Michael', 'Quinn', 'mquinn@example.com', 1 ,0, 1, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(5, 'ihenderson', 'Isaac', 'Henderson','ihenderson@example.com', 1 , 0, 1, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(6, 'carnold', 'Carol', 'Arnold','carnold@example.com', 1 , 0, 2, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(7, 'gberry', 'Grace', 'Berry', 'gberry@example.com', 1 ,0, 2, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(8, 'acornish', 'Alan', 'Cornish', 'acornish@example.com', 1 ,0, 2, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(9, 'elewis', 'Elizabeth', 'Lewis','elewis@example.com', 1 , 0, 2, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(10, 'ocarr', 'Owen', 'Carr', 'ocarr@example.com', 1 ,0, 3, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(11, 'joliver', 'Julian', 'Oliver', 'joliver@example.com', 1 ,0, 3, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(12, 'jwallace', 'Jessica', 'Wallace', 'jwallace@example.com', 1 ,0, 3, '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb'),
(13, 'admin', 'admin', 'root', 'admin@example.com', 1, 1, 1, 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1');
;


insert into recognition(id, from_user_id, to_user_id, recognition_type, comment, timestamp)
VALUES
(1, 1, 2, null, 'Love what you did on the Dodge project!', '2016-08-11 12:55:19'),
(2, 2, 1, 'INNOVATION', 'Good idea to use Spring Boot', '2016-08-13 09:07:31'),
(3, 6, 1, 'CREATIVITY', null, '2016-08-21 05:23:40'),
(4, 7, 8, 'TEAM_WORK', null, '2016-08-09 05:49:47'),
(5, 9, 8, 'TEAM_WORK', null, '2016-08-15 18:16:55'),
(6, 9, 8, 'HARD_WORK', null, '2016-08-03 13:37:03');