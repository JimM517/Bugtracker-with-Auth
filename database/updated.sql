-- Drop the existing tables from the project template.
BEGIN TRANSACTION;


DROP TABLE IF EXISTS ticket_assignments;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS bug_list;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    role_id SERIAL,
    name varchar(50),
    CONSTRAINT PK_roles PRIMARY KEY (role_id)
);

CREATE TABLE users (
    user_id SERIAL,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT PK_users PRIMARY KEY (user_id),
    CONSTRAINT FK_users_roles FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE bug_lists (
    bug_list_id SERIAL,
    title varchar(100),
    description TEXT,
    user_id int NOT NULL,
    CONSTRAINT PK_bug_lists PRIMARY KEY (bug_list_id),
    CONSTRAINT FK_bug_lists_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE tickets (
    ticket_id SERIAL,
    title varchar(100),
    description TEXT,
    user_id int NOT NULL,
    bug_list_id int NOT NULL,
    priority varchar(50),
    status varchar(50),
    ticket_category varchar(50),
    CONSTRAINT PK_tickets PRIMARY KEY (ticket_id),
    CONSTRAINT FK_tickets_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_tickets_bug_lists FOREIGN KEY (bug_list_id) REFERENCES bug_lists(bug_list_id)
);

CREATE TABLE comments (
    comment_id SERIAL,
    ticket_id int NOT NULL,
    user_id int NOT NULL,
    message TEXT,
    CONSTRAINT PK_comments PRIMARY KEY (comment_id),
    CONSTRAINT FK_comments_tickets FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    CONSTRAINT FK_comments_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE ticket_assignments (
    ticket_assignment_id SERIAL,
    ticket_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT ticket_assignments PRIMARY KEY (ticket_assignment_id),
    CONSTRAINT ticket_assignments_tickets FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
    CONSTRAINT ticket_assignments_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Sample data for roles
INSERT INTO roles (name) VALUES
    ('ADMIN'),
    ('USER'),

-- Sample data for users
INSERT INTO users (first_name, last_name, email, username, password, role_id) VALUES
    ('John', 'Doe', 'john.doe@example.com', 'john.doe', 'password123', 1),
    ('Jane', 'Smith', 'jane.smith@example.com', 'jane.smith', 'pass456', 2),
    ('Michael', 'Johnson', 'michael.johnson@example.com', 'michael.johnson', 'securePass', 3),
    ('Emily', 'Williams', 'emily.williams@example.com', 'emily.williams', '123abc', 2);

INSERT INTO bug_lists (title, description, user_id) VALUES
    ('Login Page Bug', 'Users unable to log in.', 1),
    ('Data Display Issue', 'Data not showing up on dashboard.', 2),
    ('Error Message Problem', 'Incorrect error message displayed.', 3),
    ('UI Alignment Bug', 'Elements misaligned on the settings page.', 4);

--Sample data for tickets
INSERT INTO tickets (title, description, user_id, bug_list_id, priority, status, ticket_category) VALUES
    ('Login Button Issue', 'Login button not responsive.', 1, 1, 'High', 'Open', 'UI'),
    ('Data Loading Error', 'Data not loading on Firefox browser.', 2, 2, 'Medium', 'In Progress', 'Data'),
    ('Error Message Typo', 'Spelling mistake in the error message.', 3, 3, 'Low', 'Resolved', 'Text'),
    ('Settings Icon Missing', 'Settings icon not visible on mobile.', 4, 4, 'High', 'Open', 'UI');


--Sample data for comments
INSERT INTO comments (ticket_id, user_id, message) VALUES
    (1, 1, 'Will look into this issue.'),
    (1, 2, 'The problem might be related to CSS.'),
    (2, 3, 'I can reproduce the issue on Firefox.'),
    (3, 4, 'Typo fixed in the latest commit.'),
    (4, 1, 'Will investigate why the icon is missing.');

--Sample data for ticket assignments
INSERT INTO ticket_assignments (ticket_id, user_id) VALUES
    (1, 2),
    (2, 3),
    (3, 1),
    (4, 4);

COMMIT TRANSACTION;