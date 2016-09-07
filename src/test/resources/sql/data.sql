SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE RECOGNITION;
TRUNCATE TABLE USER;
TRUNCATE TABLE TEAM;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO TEAM (id, name) values (88, 'A Team');
INSERT INTO user (first_name, last_name, username, email, password, activated, is_admin, team_id) VALUES ('admin','root', 'admin', 'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', true, true, 88);

INSERT INTO user (first_name, last_name, username, email, password, activated, is_admin, team_id) VALUES ('test','user1', 'testUser1', 'testUser1@mail.me', '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb', true, false, 88);
INSERT INTO user (first_name, last_name, username, email, password, activated, is_admin, team_id) VALUES ('test','user2', 'testUser2', 'testUser2@mail.me', '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb', true, false, 88);
INSERT INTO user (first_name, last_name, username, email, password, activated, is_admin, team_id) VALUES ('test','user3', 'testUser3', 'testUser3@mail.me', '59746265d7d8fe44e258e168c7f3b0b275c446ffdccfcbd0826b129ada9470e5b5c1e3974a0217eb', true, false, 88);


