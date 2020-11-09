GRANT SELECT, INSERT, UPDATE, DELETE
    ON ALL TABLES IN SCHEMA public
    TO game_backlog_tracker;

GRANT USAGE
    ON ALL SEQUENCES IN SCHEMA public
    TO game_backlog_tracker;