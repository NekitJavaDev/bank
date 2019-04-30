CREATE TABLE IF NOT EXISTS document
(
  version INTEGER     NOT NULL,
  name    VARCHAR(50) NOT NULL COMMENT 'Название',
  code    VARCHAR(5)  NOT NULL COMMENT 'Уникальный код' PRIMARY KEY
);
COMMENT ON TABLE document IS 'Информация о документах, удостоверящих личность';
CREATE UNIQUE INDEX UX_Document_Name ON document (name ASC);
CREATE UNIQUE INDEX UX_Document_Code ON document (code ASC);

CREATE TABLE IF NOT EXISTS country
(
  version INTEGER     NOT NULL,
  name    VARCHAR(50) NOT NULL COMMENT 'Название',
  code    VARCHAR(5)  NOT NULL COMMENT 'Уникальный код' PRIMARY KEY
);
COMMENT ON TABLE country IS 'Информация о странах по их уникальному коду';
CREATE UNIQUE INDEX UX_Country_Name ON country (name ASC);
CREATE UNIQUE INDEX UX_Country_Code ON country (code ASC);

CREATE TABLE IF NOT EXISTS organization
(
  version   INTEGER     NOT NULL,
  id        INTEGER COMMENT 'Идентификатор' PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(25) NOT NULL COMMENT 'Сокращенное название',
  full_name VARCHAR(50) NOT NULL COMMENT 'Полное название',
  inn       VARCHAR(12) NOT NULL COMMENT 'ИНН',
  kpp       VARCHAR(9)  NOT NULL COMMENT 'КПП',
  address   VARCHAR(50) NOT NULL COMMENT 'Адресс',
  phone     VARCHAR(12) COMMENT 'Телефон',
  is_active BOOLEAN COMMENT 'Работает ли на данный момент'
);
COMMENT ON TABLE organization IS 'Организация';
CREATE UNIQUE INDEX UX_Organization_Name ON organization (name ASC);
CREATE UNIQUE INDEX UX_Organization_INN ON organization (inn ASC);
ALTER TABLE organization ADD UNIQUE (full_name);
ALTER TABLE organization ADD UNIQUE (kpp);
ALTER TABLE organization ADD UNIQUE (address);
ALTER TABLE organization ADD UNIQUE (phone);

CREATE TABLE IF NOT EXISTS office
(
  version   INTEGER     NOT NULL,
  id        INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  org_id    INTEGER     NOT NULL COMMENT 'Идентификатор организации',
  name      VARCHAR(50) NOT NULL COMMENT 'Название',
  address   VARCHAR(50) NOT NULL COMMENT 'Адрес',
  phone     VARCHAR(12) COMMENT 'Телефон',
  is_active BOOLEAN COMMENT 'Работает ли?'
);
COMMENT ON TABLE office IS 'Офис';
ALTER TABLE office
  ADD FOREIGN KEY (org_id) REFERENCES organization (id);
CREATE INDEX IX_Office_Organization ON office (org_id);
CREATE UNIQUE INDEX UX_Office_Name ON office (name);
CREATE UNIQUE INDEX UX_Office_Phone ON office (phone);

CREATE TABLE IF NOT EXISTS employee
(
  version          INTEGER     NOT NULL,
  id               INTEGER COMMENT 'Идентификатор' PRIMARY KEY AUTO_INCREMENT,
  office_id        INTEGER     NOT NULL COMMENT 'Идентификатор офиса',
  first_name       VARCHAR(20) NOT NULL COMMENT 'Имя',
  second_name      VARCHAR(50) COMMENT 'Фамилия',
  middle_name      VARCHAR(25) COMMENT 'Отчество',
  position         VARCHAR(40) NOT NULL COMMENT 'Должность',
  phone            VARCHAR(12) COMMENT 'Сотовый телефон',
  doc_code         INTEGER COMMENT 'Уникальный код документа, удостоверяющего личность',
  doc_number       VARCHAR(45) COMMENT 'Серия и номер документа',
  doc_date         DATE,
  citizenship_code INTEGER COMMENT 'Уникальный код страны (гражданство)',
  is_identified    BOOLEAN COMMENT 'Статус занятости (Работает или в отпуске/на больничном)'
);
COMMENT ON TABLE employee IS 'Работник';
ALTER TABLE employee
  ADD FOREIGN KEY (office_id) REFERENCES office (id);
CREATE INDEX IX_Employee_Office ON employee (office_id);
ALTER TABLE employee
  ADD FOREIGN KEY (doc_code) REFERENCES document (code);
CREATE INDEX IX_Employee_Document ON employee (doc_code);
ALTER TABLE employee
  ADD FOREIGN KEY (citizenship_code) REFERENCES country (code);
CREATE INDEX IX_Employee_Country ON employee (citizenship_code);
CREATE INDEX IX_Employee_FirstName on employee (first_name);
CREATE INDEX IX_Employee_LastName on employee (second_name);
CREATE INDEX IX_Employee_MiddleName on employee (middle_name);
CREATE INDEX IX_Employee_Position on employee (position);
ALTER TABLE employee ADD UNIQUE (phone);
ALTER TABLE employee ADD UNIQUE (doc_number);