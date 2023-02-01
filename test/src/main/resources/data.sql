CREATE TABLE Users(
                      id integer unique auto_increment,
                      login varchar,
                      email varchar,
                      password varchar,
                      phone long,
                      role varchar
);
CREATE TABLE Blogs(
                      id integer unique auto_increment ,
                      author integer,
                      news longvarchar
);
INSERT into Blogs values (default, 4, 'hello its a trap');
INSERT into Blogs values (default, 22, 'hello its a two');
INSERT into Blogs values (default, 1, 'hello its a four');

INSERT into Users values (default, 'user', 'user@mail.ru', 'u', 89432417761, 'USER');
INSERT into Users values (default, 'admin', 'admin@mail.ru', 'a', 89516473822, 'ADMIN');