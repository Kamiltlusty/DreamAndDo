CREATE TABLE dream (
    id INT NOT NULL,
    title VARCHAR(128) NOT NULL,
    description VARCHAR(1000),
    PRIMARY KEY (id)
);

INSERT INTO dream (id, title, description)
VALUES ('1', 'Zrobić śniadanie', ''),
       ('2', 'Odrobić lekcje', ''),
       ('3', 'Przeczytać lekturę', ''),
       ('4', 'Spakować walizkę', '');