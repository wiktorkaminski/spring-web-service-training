INSERT INTO institutions (id, description, name) VALUES (1, 'Pomagamy dzieciom potrzebującym opieki hipoterapeutycznej.', 'Fundacja "Konik Polski"');
INSERT INTO institutions (id, description, name) VALUES (2, 'Cel i misja: Pomoc w wybudzaniu dzieci ze śpiączki.', 'OPP "Świeca w mroku"');
INSERT INTO institutions (id, description, name) VALUES (3, 'Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja “Dla dzieci"');
INSERT INTO institutions (id, description, name) VALUES (4, 'Nasza misja: Pomoc dla osób nie posiadających miejsca zamieszkania.', 'Fundacja “Bez domu”');

INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Katowice', ' ', '2021-01-04', '18:00:00', 10, 'Roździeńskiego 88/12', '40-203', 1);
INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Tychy', 'Dzwonić przed przyjazdem na numer 500-111-222', '2021-01-04', '18:00:00', 2, 'Kobiórska 77a', '43-100', 2);
INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Wyry', 'Wejście od podwórza', '2021-01-04', '18:00:00', 7, 'Spokojna 1', '43-175', 4);
INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Katowice', 'Proszę o telefon', '2021-01-04', '18:00:00', 1, 'Rodzinna 81/32', '40-203', 3);