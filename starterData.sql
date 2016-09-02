delete from recognition;
delete from user;
delete from team;

insert into team (id, name)  values (1, 'Mystic'), (2, 'Instinct'), (3, 'Valor');

insert into user (id, username, first_name, last_name, is_admin, team)
VALUES
(1, 'nturner', 'Nathan', 'Turner', 0, 1),
(2, 'fcarr', 'Frank', 'Carr', 0, 1),
(3, 'smcgrath', 'Sam', 'McGrath', 0, 1),
(4, 'mquinn', 'Michael', 'Quinn', 0, 1),
(5, 'ihenderson', 'Isaac', 'Henderson', 0, 1),
(6, 'carnold', 'Carol', 'Arnold', 0, 2),
(7, 'gberry', 'Grace', 'Berry', 0, 2),
(8, 'acornish', 'Alan', 'Cornish', 0, 2),
(9, 'elewis', 'Elizabeth', 'Lewis', 0, 2),
(10, 'ocarr', 'Owen', 'Carr', 0, 3),
(11, 'joliver', 'Julian', 'Oliver', 0, 3),
(12, 'jwallace', 'Jessica', 'Wallace', 0, 3);


insert into recognition(id, from_user, to_user, recognition_type, comment, timestamp)
VALUES
(1, 1, 2, null, 'Love what you did on the Dodge project!', '2016-08-11 12:55:19'),
(2, 2, 1, 'INNOVATION', 'Good idea to use Spring Boot', '2016-08-13 09:07:31'),
(3, 6, 1, 'CREATIVITY', null, '2016-08-21 05:23:40'),
(4, 7, 8, 'TEAM_WORK', null, '2016-08-09 05:49:47'),
(5, 9, 8, 'TEAM_WORK', null, '2016-08-15 18:16:55'),
(6, 9, 8, 'HARD_WORK', null, '2016-08-03 13:37:03');