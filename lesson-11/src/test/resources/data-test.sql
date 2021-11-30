insert into author (full_name)
values ('a1');
insert into author (full_name)
values ('a2');
insert into author (full_name)
values ('a3');

insert into genre (name)
values ('g1');
insert into genre (name)
values ('g2');
insert into genre (name)
values ('g3');

insert into book (title, author_id, genre_id)
values ('b1', 1, 1);
insert into book (title, author_id, genre_id)
values ('b2', 2, 2);
insert into book (title, author_id, genre_id)
values ('b3', 3, 3);

insert into comment(text, book_id)
values ('c1', 1);
insert into comment(text, book_id)
values ('c2', 2);
insert into comment(text, book_id)
values ('c3', 3);