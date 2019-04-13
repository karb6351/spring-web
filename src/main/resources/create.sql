CREATE TABLE users (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_id INTEGER NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO users(username, password) VALUES ('admin', '123456');
INSERT INTO user_roles(user_id, role) VALUES (1, 'ROLE_LECTURER');

INSERT INTO users(username, password) VALUES ('david', '123456');
INSERT INTO user_roles(user_id, role) VALUES (2, 'ROLE_STUDENT');


CREATE TABLE lectures (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO lectures(title) VALUES ('Lecture 1: Overview of Web Applications');
INSERT INTO lectures(title) VALUES ('Lecture 2: Servlet');

CREATE TABLE materials (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    lecture_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id)
);

CREATE TABLE questions (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    question VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE responses (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    response VARCHAR(255) NOT NULL,
    question_id INTEGER DEFAULT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(id),
    PRIMARY KEY (id)
);

CREATE TABLE votes (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  response_id INTEGER DEFAULT NULL,
  user_id INTEGER DEFAULT NULL,
  FOREIGN KEY (response_id) REFERENCES responses(id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  PRIMARY KEY (id)
)

CREATE TABLE comments (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  content MESSAGE_TEXT DEFAULT NOT NULL,
  user_id INTEGER DEFAULT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  PRIMARY KEY (id)
)

