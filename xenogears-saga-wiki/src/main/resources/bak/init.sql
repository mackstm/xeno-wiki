-- Table: WeaponType
CREATE TABLE weapon_types (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Table: MechCategory
CREATE TABLE mech_categories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Table: Weapon
CREATE TABLE weapons (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    type_id INTEGER NOT NULL,
    FOREIGN KEY (type_id) REFERENCES weapon_types(id)
);

-- Table: Mech
CREATE TABLE mechs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    pilot_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (pilot_id) REFERENCES xeno_characters(id)
    FOREIGN KEY (category_id) REFERENCES mech_categories(id)
);

-- Table: XenoCharacter
CREATE TABLE xeno_characters (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    weapon_id INTEGER,
    FOREIGN KEY (weapon_id) REFERENCES weapons(id)
);

-- Table: User
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Table: Role
CREATE TABLE roles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Inserts for WeaponType
INSERT INTO weapon_types (name) VALUES ('Sword');
INSERT INTO weapon_types (name) VALUES ('Staff');

-- Inserts for MechCategory
INSERT INTO mech_categories (name) VALUES ('Gear');
INSERT INTO mech_categories (name) VALUES ('Omnigear');
INSERT INTO mech_categories (name) VALUES ('AGWS');
INSERT INTO mech_categories (name) VALUES ('ES unit');

-- Inserts for Role
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Inserts for Weapon
INSERT INTO weapons (name, type_id) VALUES ('Yamato', 1);
INSERT INTO weapons (name, type_id) VALUES ('Meteor Staff', 2);

-- Inserts for XenoCharacter
INSERT INTO xeno_characters (name) VALUES ('Fei Fong Wong');
INSERT INTO xeno_characters (name, weapon_id) VALUES ('Citan Uzuki', 1);
INSERT INTO xeno_characters (name, weapon_id) VALUES ('Elehayym Van Houten', 2);

-- Inserts for Mech
INSERT INTO mechs (name, pilot_id, category_id) VALUES ('Weltall', 1, 1);
INSERT INTO mechs (name, pilot_id, category_id) VALUES ('Fenrir', 2, 2);
INSERT INTO mechs (name, pilot_id, category_id) VALUES ('Vierge', 3, 1);

-- Inserts for User
INSERT INTO users (name, email, password, role_id) VALUES ('admin', 'admin@example.com', 'admin', 1);

