CREATE TABLE TBL_USER (
    USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USER_PASS VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255),
    USER_EMAIL VARCHAR(255),
    USER_ROLE VARCHAR(50),
    USER_STATE VARCHAR(50)
);





