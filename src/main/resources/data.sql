INSERT INTO institutions (id, description, name, active) VALUES (1, 'Pomagamy dzieciom potrzebującym opieki hipoterapeutycznej.', 'Fundacja "Konik Polski"', true);
INSERT INTO institutions (id, description, name, active) VALUES (2, 'Cel i misja: Pomoc w wybudzaniu dzieci ze śpiączki.', 'OPP "Świeca w mroku"', true);
INSERT INTO institutions (id, description, name, active) VALUES (3, 'Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja “Dla dzieci"', true);
INSERT INTO institutions (id, description, name, active) VALUES (4, 'Nasza misja: Pomoc dla osób nie posiadających miejsca zamieszkania.', 'Fundacja “Bez domu”', true);
INSERT INTO institutions (id, description, name, active) VALUES (5, 'Pomagamy sądziadom potrzebującym wsparcia.', 'OPP "Sąsiadko"', true);
INSERT INTO institutions (id, description, name, active) VALUES (6, 'Nasz motto: Zero waste', 'OPP "Mam to dam, nie wyrzucam"', true);
INSERT INTO institutions (id, description, name, active) VALUES (7, 'Przekazujemy zużytą odzież osobom potrzebującym', 'Fundacja "Drugi obieg"', true);

INSERT INTO donations (id, city, phone, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (1, 'Katowice', '500-111-222', ' ', '2021-01-04', '18:00:00', 10, 'Roździeńskiego 88/12', '40-203', 1);
INSERT INTO donations (id, city, phone, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (2, 'Tychy', '22 323-22-22', 'Dzwonić przed przyjazdem na numer 500-111-222', '2021-01-04', '18:00:00', 2, 'Kobiórska 77a', '43-100', 2);
INSERT INTO donations (id, city, phone, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (3, 'Wyry', '888-888-001', 'Wejście od podwórza', '2021-01-04', '18:00:00', 7, 'Spokojna 1', '43-175', 4);
INSERT INTO donations (id, city, phone, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (4, 'Katowice', '606-066-660', 'Proszę o telefon', '2021-01-04', '18:00:00', 1, 'Rodzinna 81/32', '40-203', 3);

INSERT INTO categories (id, name) VALUES (1, 'Ubrania');
INSERT INTO categories (id, name) VALUES (2, 'Zabawki');
INSERT INTO categories (id, name) VALUES (3, 'Agd małe');
INSERT INTO categories (id, name) VALUES (4, 'Elektronika');

INSERT INTO users (id, email, enabled, first_name, last_name, password) VALUES (2, 'wiktor.kaminski@mixbox.pl', true, 'Wiktor', 'Kamiński', '$2a$10$xU5OYYsh3Z5KJ.lpZ65Zeu5jNOgX.KeWWnKhM812jk5oSwN03HQqW');
INSERT INTO users (id, email, enabled, first_name, last_name, password) VALUES (1, 'jozef.kwapis@charity.online', true, 'Józef', 'Kwapiś', '$2a$10$xU5OYYsh3Z5KJ.lpZ65Zeu5jNOgX.KeWWnKhM812jk5oSwN03HQqW');

INSERT INTO authorities (id, authority, email) VALUES (2, 'ROLE_USER', 'wiktor.kaminski@mixbox.pl');
INSERT INTO authorities (id, authority, email) VALUES (1, 'ROLE_ADMIN', 'jozef.kwapis@charity.online');

INSERT INTO users_authorities (charity_user_id, authorities_id) VALUES (1, 1);
INSERT INTO users_authorities (charity_user_id, authorities_id) VALUES (2, 2);