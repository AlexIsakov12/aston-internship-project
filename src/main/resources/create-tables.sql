CREATE TABLE category (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE mandatory (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE app_user (
    id BIGINT PRIMARY KEY,
    nickname VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE task (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255),
    user_id BIGINT,
    mandatory_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (mandatory_id) REFERENCES mandatory(id)
);

CREATE TABLE task_category (
    task_id BIGINT,
    category_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES task(id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    PRIMARY KEY (task_id, category_id)
);