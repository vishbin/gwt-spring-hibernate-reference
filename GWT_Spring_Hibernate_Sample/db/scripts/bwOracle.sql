--
-- Author :       Rodrigo López-Guzmán
-- Target DBMS : Oracle 10g
--
DROP TABLE classification CASCADE CONSTRAINTS
;
-- 
-- TABLE: classification 
--

CREATE TABLE classification(
    classification_id      NUMBER(4, 0)     NOT NULL,
    classification_name    VARCHAR2(80)     NOT NULL,
    description            VARCHAR2(255),
    mandatory              CHAR(1)          NOT NULL
                           CHECK (UPPER(mandatory) in ('Y', 'N')),
    multiple               CHAR(1)          NOT NULL
                           CHECK (UPPER(multiple) in ('Y', 'N')),
    CONSTRAINT pk_classification PRIMARY KEY (classification_id)
)
;
