CREATE DATABASE IF NOT EXISTS ArticleRevTravis;
USE ArticleRevTravis;

CREATE TABLE IF NOT EXISTS Publication
(
    pub_id         INTEGER AUTO_INCREMENT,

    title          VARCHAR(100) NOT NULL,
    pub_url        VARCHAR(100) NOT NULL,

    PRIMARY KEY (pub_id)
);

CREATE TABLE IF NOT EXISTS Review
(
    rev_id         INTEGER AUTO_INCREMENT,
    
    pub_id         INTEGER       NOT NULL,

    recommendation VARCHAR(20)   NOT NULL,
    reviewer_name  VARCHAR(100)  NOT NULL,

    PRIMARY KEY (rev_id),
    CONSTRAINT  Fk_Review_refs_Publication FOREIGN KEY (pub_id) REFERENCES Publication(pub_id)
);

CREATE TABLE IF NOT EXISTS Point
(
    point_id       INTEGER AUTO_INCREMENT,

    point_body     VARCHAR(1000),
    point_type     VARCHAR(20),
    rev_id         INTEGER      NOT NULL,

    PRIMARY KEY (point_id),
    CONSTRAINT  Fk_Points_refs_Review FOREIGN KEY (rev_id) REFERENCES Review(rev_id)
);

