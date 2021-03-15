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

    summary        VARCHAR(2000) NOT NULL,

    recommendation VARCHAR(20)   NOT NULL,
    reviewer_name  VARCHAR(100)  NOT NULL,

    PRIMARY KEY (rev_id),
    CONSTRAINT  Fk_Review_refs_Publication FOREIGN KEY (pub_id) REFERENCES Publication(pub_id)
);

CREATE TABLE IF NOT EXISTS Point
(
    point_id       INTEGER AUTO_INCREMENT,

    rev_id         INTEGER      NOT NULL,

    point_body     VARCHAR(1000),
    point_type     VARCHAR(20),

    PRIMARY KEY (point_id),
    CONSTRAINT  Fk_Points_refs_Review FOREIGN KEY (rev_id) REFERENCES Review(rev_id)
);

INSERT INTO Publication VALUES 
(
    1,
    "Pizza: Why Are You So Delicous?",
    "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
);

INSERT INTO Review VALUES
(
    1,
    1,
    "This paper discusses pizza and mathemically proves why it is the greatest food category on earth.",
    "ACCEPT",
    "Franz Kafka"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "Clearly, pizza is the ultimate food.",
    "pos"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "The only negative is that I do not have some pizza right now.",
    "ngt"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "The writer should be more careful when discussing the downsides of pizza. There is no need of this.",
    "maj"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "The writer states that floppy pizza is acceptable. They are clearly uninoformed on this topic, and should obtain more experinece before giving their opinion on the topic.",
    "maj"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "The writer should provide free pizza for all readers.",
    "min"
);

INSERT INTO Point VALUES
(
    0,
    1,
    "The writer should go into more depth between the differences between such neopolitan, greek, red saurce, etc.",
    "min"
);



INSERT INTO Publication VALUES 
(
    2,
    "E-dietary Planner: A Machine Learning approach to suggest food evaluating food-drug-interaction",
    "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
);

INSERT INTO Review VALUES
(
    2,
    2,
    "Food-Drug-Interactions (FDI’s) is the study of how food compounds interact with drugs prescribed to patients. This is valuable because some drugs have negative side effects when taken with certain foods, which counteract effectiveness of the drugs given to patients. It will be valuable for doctors and patients alike to have a better understanding of which foods should be avoided, to increase the effectiveness of the treatment. The authors managed to download and obtain all related to drug-drug-interactions and all information related to food compounds. The datasets were preprocessed, and a structure similarity profile (SSP) was used on the drug-interactions dataset to obtain final resulting dataset of 869035 drug-drug-interactions for 1683 drugs and found 11561 food-compound pairs for 1244 unique compounds (SMILES) from the food dataset. The authors now need to decide on which problem they want address in the field, for example, to find out how the metabolism is affected by food. Once that has addressed a machine learning model will be produced from the datasets to produce results on the problem they want to address.",
    "REJECT",
    "Mbongeni Ndlovu"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "The authors of the paper managed to create their own datasets and use extensive techniques like the SSP to make the data more meaningful for future work",
    "pos"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "The paper is easy to follow and understand what the authors did and how they got the results posted in the paper",
    "pos"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "The authors acknowledged that their problem definition needs to be refined to solve a more specific problem.",
    "pos"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "The paper can be disconcerting to read due to bad grammar",
    "ngt"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "A lot of data was parsed out of the datasets during preprocessing, I am just wondering if important information were parsed out of the dataset that could be relevant for future work.",
    "ngt"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "How are the authors going to link and find similarities in the SSP of both the drug-drug-interaction datasets and food compound datasets? The SSP results were mentioned in the paper, but not shown. So, it is unclear to me on how they will link between the two datasets.",
    "ngt"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "During the preprocessing steps of the datasets, a huge amount of data was parsed out and lost and I understand the authors were trying to find information that was more related to what they were trying to solve. I would be very cautious and fully understand what type of information is being lost in the process, because that same information you deem is not relevant now may be relevant in the future when you redefine your problem definition.",
    "maj"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "I noticed in the paper that a SMILES representation of the datasets was shown in the paper, but no representation of SSP was shown and makes it hard for me to see how they will make the linkage between drug-drug-interaction and food compound datasets.",
    "maj"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "The grammar in the paper should be improved. Some parts of the paper are hard to read and understand and could easily frustrate the reader of the paper, especially if they have to review many papers.",
    "min"
);

INSERT INTO Point VALUES
(
    0,
    2,
    "A description of the attribute values in the datasets presented in the paper would make the paper clearer. I had to look at the drug-drug-interactions datasets figure multiple times before understanding which two drugs in questions were which in the dataset attribute columns",
    "min"
);
