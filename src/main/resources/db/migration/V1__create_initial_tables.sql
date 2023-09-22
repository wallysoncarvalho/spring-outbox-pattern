create table if not exists cool_entity(
    id serial primary key,
    name varchar not null
);

create table if not exists outbox(
    id serial primary key,
    "type" varchar not null,
    content text not null,
    created_at timestamp with time zone not null,
    scheduled_to timestamp with time zone not null,
    processed_at timestamp with time zone
);

create index if not exists outbox_scheduled_to_idx on outbox(scheduled_to);
