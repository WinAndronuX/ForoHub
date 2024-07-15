CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       num_topics_created INTEGER,
                       num_comments INTEGER
);

CREATE TABLE IF NOT EXISTS courses (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         category VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS topics (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        content TEXT NOT NULL,
                        user_id BIGINT NOT NULL,
                        course_id BIGINT NOT NULL,
                        num_responses INTEGER,
                        created_at TIMESTAMP NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS replies (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         message TEXT NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         replies_to INTEGER,
                         topic_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (topic_id) REFERENCES topics(id),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);
