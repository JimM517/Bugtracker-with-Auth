-- Create the "users" table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Create the "bug_lists" table
CREATE TABLE bug_lists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_by INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE CASCADE
);

-- Create the "tickets" table
CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    created_by INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    bug_list_id INT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (bug_list_id) REFERENCES bug_lists (id) ON DELETE CASCADE
);

-- Create the "comments" table
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    created_by INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    ticket_id INT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON DELETE CASCADE
);

CREATE TABLE assignments (
    id SERIAL PRIMARY KEY,
    ticket_id INT NOT NULL,
    user_id INT NOT NULL,
    assigned_at TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);



-- Insert mock data into the "users" table
INSERT INTO users (username, password_hash, role)
VALUES
    ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
    ('admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');

-- Insert mock data into the "bug_lists" table
INSERT INTO bug_lists (name, description, created_by)
VALUES
    ('Feature Requests', 'A list of feature requests for the application', 1),
    ('Bugs', 'A list of reported bugs', 2);

-- Insert mock data into the "tickets" table
INSERT INTO tickets (title, description, status, created_by, bug_list_id)
VALUES
    ('Improve User Interface', 'Make the UI more user-friendly', 'OPEN', 1, 1),
    ('Login Issue', 'Users are unable to login', 'IN PROGRESS', 2, 2);

-- Insert mock data into the "comments" table
INSERT INTO comments (content, created_by, ticket_id)
VALUES
    ('This is a great suggestion!', 2, 1),
    ('We are investigating the login issue.', 1, 2);

INSERT INTO assignments (ticket_id, user_id)
VALUES
    (1, 2),
    (2, 1),
    (2, 2);





