insert into platforms (name)
values ('PlayStation 4');

insert into developers (name)
values ('Insomniac Games');

insert into games (name, cover, release_date, summary, developer_id)
values ('Ratchet & Clank', 'https://images.igdb.com/igdb/image/upload/t_cover_big/co230k.jpg', '2016-04-12', 'Ratchet & Clank is a new game based on elements from the original Ratchet & Clank (PS2), featuring more than an hour of new cinematics (including footage from the film) in vibrant 1080p, new locations, weapons, bosses and more. Join Ratchet, Clank, Captain Qwark and new friends as they embark on an intergalactic adventure, and experience the start of an epic friendship (again) on PlayStation 4.', 1);

insert into platforms_games (platform_id, game_id)
values (1, 1);

insert into platforms (name)
values ('Xbox 360'),
       ('Nintendo Switch'),
       ('PC (Microsoft Windows)'),
       ('Xbox One');

insert into developers (name)
values ('Lionhead Studios'),
       ('Atlus'),
       ('Nintendo EPD');

insert into games (name, cover, release_date, summary, developer_id)
values ('Fable II',
        'https://images.igdb.com/igdb/image/upload/t_cover_big/co1y2h.jpg',
        '2008-10-21',
        'Fable 2 is the second game in the fable series by Lionhead Studios, it is an open world role-playing game. It has a simple story line where the hero needs to save Albion from the evil Lord Lucian by collecting together the other three heroes to defeat him. Interactive cut-scenes are a large part of the game however the main character never speaks and all dialogue is uncontrolled.',
        2),
       ('Persona 5',
        'https://images.igdb.com/igdb/image/upload/t_cover_big/co1r76.jpg',
        '2016-09-15',
        'Persona 5, a turn-based JRPG with visual novel elements, follows a high school student with a criminal record for a crime he didn''t commit. Soon he meets several characters who share similar fates to him, and discovers a metaphysical realm which allows him and his friends to channel their pent-up frustrations into becoming a group of vigilantes reveling in aesthetics and rebellion while fighting corruption.',
        3),
       ('Super Mario Odyssey',
        'https://images.igdb.com/igdb/image/upload/t_cover_big/co1mxf.jpg',
        '2017-10-27',
        'The game has Mario leaving the Mushroom Kingdom to reach an unknown open world-like setting, like Super Mario 64 and Super Mario Sunshine.',
        4),
       ('Sunset Overdrive',
        'https://images.igdb.com/igdb/image/upload/t_cover_big/co20td.jpg',
        '2014-10-28',
        'The city’s overrun with mutants. Problem? Not for you. Sunset Overdrive is an open-world playground of post-apocalyptic possibilities. Vault, grind & wall-run while using a deadly & unconventional arsenal. With hyper-agility, unique weapons, & customizable abilities, it rewrites the rules of shooters while delivering an irreverent adventure.',
        1);

insert into platforms_games (platform_id, game_id)
values (2, 2),
       (1, 3),
       (3, 4),
       (4, 5),
       (5, 5);

insert into userroles (code, name)
values ('ROLE_USER', 'Пользователь'),
       ('ROLE_ADMIN', 'Администратор');

insert into users (username, password)
values ('turboVasyan2000', '$2y$10$lTT8Y2Cjwa1dW6SIPChviOOifALmZw939ivfFFrThHyBM37U.2jOi'),
       ('veryCoolAdmin123', '$2y$10$O5Wct5EA/gzVco14JdbdCeFc5hEt58nVK2PspJaOmm99576UL0Vzq');

insert into users_userroles (user_id, userrole_code)
values (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

insert into users_backlog (user_id, game_id, added_date)
values (1, 1, current_date),
       (2, 2, current_date),
       (2, 3, current_date);