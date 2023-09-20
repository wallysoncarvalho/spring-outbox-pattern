create table if not exists cool_entity(
    id serial primary key,
    name varchar not null,
);

create table if not exists outbox(
    id serial primary key,
    data jsonb not null,
    created_at timestamp with time zone not null,
    scheduled_to timestamp with time zone not null,
);
