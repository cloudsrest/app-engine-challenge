DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS oauth_refresh_token;
DROP TABLE IF EXISTS recognition;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS team;

CREATE TABLE recognition (
  id bigint NOT NULL AUTO_INCREMENT,
  comment longtext,
  recognition_type varchar(255) DEFAULT NULL,
  timestamp datetime DEFAULT NULL,
  from_user_id bigint DEFAULT NULL,
  to_user_id bigint DEFAULT NULL
);

CREATE TABLE team (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL
);

CREATE TABLE user (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  activated     BIT    NOT NULL,
  activationkey VARCHAR(255) DEFAULT NULL,
  email         VARCHAR(255) DEFAULT NULL,
  first_name    VARCHAR(255) DEFAULT NULL,
  is_admin      BIT (1) NOT NULL,
  last_name     VARCHAR(255) DEFAULT NULL,
  password      VARCHAR(255) DEFAULT NULL,
  resetpasswordkey VARCHAR(255) DEFAULT NULL,
  username      VARCHAR(255) DEFAULT NULL,
  team_id       BIGINT DEFAULT NULL
);

CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
);
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
);