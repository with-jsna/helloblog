drop table if exists search_word_count CASCADE;

create table search_word_count
(
    id bigint generated by default as identity,
    word varchar(255) not null,
    count bigint not null default 1,
    create_dtm datetime default CURRENT_TIMESTAMP,
    primary key (id)
);
