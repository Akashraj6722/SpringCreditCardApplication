create database credit_card;
use  credit_card;
drop database credit_card;

create table user_details(
id int auto_increment primary key,  
first_name varchar(20),
last_name varchar(20),
dob varchar(20),
aadhaar_number varchar(20) unique,
aadhaar_proof longblob,
pan_number varchar(20) unique,
pan_proof longblob ,
email_id varchar(50) unique, 
phone_number varchar(20) unique,
password varchar(50)

);
drop table user_details;
select* from user_details;
truncate user_details;
alter table  user_details auto_increment=3550;

create table account_details(
customer_id int,
account_type varchar(20) DEFAULT 'savings', 
account_number varchar(20) primary key unique,
ifsc_code varchar(20) unique,
cibil_score int,
account_balance int   DEFAULT 1000,
account_status varchar(10) DEFAULT 'active' ,

FOREIGN KEY (customer_id ) REFERENCES user_details(id)
);
drop table account_details;
Select * from account_details;
truncate account_details;
create table employment_details (
id_number int ,
customer_occupation varchar(20),  
customer_company_name varchar(20),
customer_designation varchar(20),
customer_annual_income long,
income_proof longblob,

FOREIGN KEY(id_number) REFERENCES user_details(id)
);

SELECT * FROM employment_details;
SELECT count(*) FROM employment_details WHERE id_number=21;
truncate employment_details;
drop table employment_details;
DELETE FROM employment_details WHERE id_number=3;

SELECT u.id
FROM user_details u
LEFT JOIN employment_details e ON u.id = e.id_number
WHERE e.id_number IS NULL;

   

create table credit_card_details(
customer_id int,
account_number varchar(20),
credit_card_number varchar(20) primary key unique,
credit_card_type varchar(20),
credit_card_cvv int unique,
credit_card_pin int(4),
credit_card_issue_date varchar(20),
credit_card_valid_till varchar(20),
credit_points int,
credit_card_status varchar(20) DEFAULT 'not active', 
credit_card_approval varchar(20) DEFAULT 'not approved',
FOREIGN KEY (customer_id ) REFERENCES user_details(id),
FOREIGN KEY (account_number ) REFERENCES account_details(account_number)

);
select * from credit_card_details;
drop table credit_card_details;
truncate table credit_card_details;

UPDATE credit_card_details SET credit_card_type='silver' WHERE customer_id =3552 AND credit_card_number=6106368502879175;

UPDATE credit_card_details SET credit_card_approval='Accepted',credit_card_status='Active' WHERE customer_id=3551 AND credit_card_number='6012503138391801';

UPDATE credit_card_details SET credit_card_approval='Rejected',credit_card_status='not active' WHERE customer_id=3551 AND credit_card_number='6012503138391801';

CREATE TABLE transactions (
    id int,
	card_number varchar(20),  
    transaction_id varchar(30) PRIMARY KEY,
    date_time DATETIME,
    amount int,
    description VARCHAR(100),
    FOREIGN KEY (id) REFERENCES user_details(id),
    FOREIGN KEY (card_number) REFERENCES credit_card_details(credit_card_number)

);
select * from transactions;
drop table transactions;