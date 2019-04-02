INSERT INTO document (version ,name, code) VALUES (0, 'Паспорт гражданина РФ', '21');
INSERT INTO document (version, name, code) VALUES (0, 'Водительское удостоверение иностранного гражданина', '10');
INSERT INTO document (version, name, code) VALUES (0, 'Удостоверение беженца', '13');
INSERT INTO document (version, name, code) VALUES (0, 'Разрешение на временное проживание в РФ', '15');
INSERT INTO document (version, name, code) VALUES (0, 'Водительское удостоверение РФ', '44');
INSERT INTO document (version, name, code) VALUES (0, 'Военный билет', '7');

INSERT INTO country (version, name, code) VALUES (0, 'Российская Федерация', '601');
INSERT INTO country (version, name, code) VALUES (0, 'Швеция', '652');
INSERT INTO country (version, name, code) VALUES (0, 'Португалия', '703');
INSERT INTO country (version, name, code) VALUES (0, 'Мексика', '613');
INSERT INTO country (version, name, code) VALUES (0, 'Куба', '1333');

INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active) VALUES
(0, 'ПАO "Газпром"', 'Публичное Акционерное Общество "Газпром"', '7736050003', '772801001', 'ул. Наметкина, 16, Москва', '84957193001', TRUE);
INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active) VALUES
(0, 'ООO "Наш дом"', 'Общество с ограниченной ответсвенностью "Наш дом"', '2309096333 ', '230901001', 'ул. Пришвина, 14, Москва', '89184412491', TRUE);

INSERT INTO office (version, org_id, name, address, phone, is_active) VALUES
(0, 1, 'Головной офис Газпрома', 'ул. Балчуг, 7, Москва', '88001000701', TRUE);
INSERT INTO office (version, org_id, name, address, phone, is_active) VALUES
(0, 1, 'Дополнительный офис "Останкино"', 'ул. Академика Королева, д. 12, Москва', '88005553535', FALSE);
INSERT INTO office (version, org_id, name, address, phone, is_active) VALUES
(0, 2, 'Головной офис Наш Дом', 'ул. Космонавта Волкова, 10, Москва', '84951130285', TRUE);
INSERT INTO office (version, org_id, name, address, phone, is_active) VALUES
(0, 2, 'Офис 19101', 'Дмитровское ш., 157, БЦ "Гефест", Москва', '88001560712', TRUE);

INSERT INTO employee (version, office_id, citizenship_code, doc_code, first_name, second_name, middle_name, position, phone, doc_number, doc_date, is_identified ) VALUES
(0, 1, 601, 21, 'Никита', 'Газманов', 'Андреевич', 'программист', '89167278834', '4511 254688', '2005-11-14', TRUE);
INSERT INTO employee (version, office_id, citizenship_code, doc_code, first_name, second_name, middle_name, position, phone, doc_number, doc_date, is_identified ) VALUES
(0, 2, 601, 21, 'Лариса', 'Максимова', 'Андреевна', 'секретарь', '89152228847', '2213 616660', '2014-10-13', FALSE);
INSERT INTO employee (version, office_id, citizenship_code, doc_code, first_name, second_name, middle_name, position, phone, doc_number, doc_date, is_identified ) VALUES
(0, 3, 601, 15, 'Борис', 'Мурин', 'директор', 'Алексеевич', '89997654473', '3244 772831', '2016-12-15', TRUE);
INSERT INTO employee (version, office_id, citizenship_code, doc_code, first_name, second_name, position, phone, doc_number, doc_date, is_identified ) VALUES
(0, 4, 652, 10, 'Златан', 'Ларсен', 'водитель',  '89665234109', '55555 65', '2001-10-25', TRUE);
INSERT INTO employee (version, office_id, citizenship_code, doc_code, first_name, second_name, middle_name, position, phone, doc_number, doc_date, is_identified ) VALUES
(0, 4, 601, 44, 'Олег', 'Титов', 'Михайлович', 'водитель', '89037751918', '66666 43', '2007-12-11', TRUE);