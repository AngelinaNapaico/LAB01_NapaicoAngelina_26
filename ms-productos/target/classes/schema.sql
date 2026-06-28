CREATE TABLE IF NOT EXISTS productos (
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(60)    NOT NULL,
    price     DECIMAL(10, 2) NOT NULL,
    stock     INTEGER        NOT NULL DEFAULT 0,
    active    BOOLEAN        DEFAULT TRUE
);
