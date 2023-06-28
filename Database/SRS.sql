create database wiprodb;
use wiprodb;

show tables;
drop database wiprodb;


-- Tables:
select * from Route_Details;  -- PK routeId
select * from Ship_Details;   -- PK shipId
select * from Ship_Schedule;  -- FK routeId of Route_Details | FK shipId Ship_Details
select * from Passenger;      -- PK pId
select * from Booking;        -- PK bookingId | FK pEmail of Passenger | FK shipId of  Ship_SChedule
select * from head_count;     -- PK hcName | FK hc_booking_id of Booking

--  ===================================================================================================

desc passenger;
desc booking;
desc ship_details;
desc ship_schedule;
desc route_details;
desc head_count;

-- =======================================================================================================
-- ==:Drop Tables:==
drop table booking;
drop table passenger;
drop table Ship_Schedule;
drop table Ship_Details;
drop table Route_Details;
drop table head_count;


truncate table passenger;
truncate table booking;
truncate table ship_schedule;
truncate table head_count;

delete from ship_schedule where ss_id = 10026;

--  ========================================================================================================

-- ======= Route_Details Table: ======

insert into Route_Details values('R001','chennai',700,'goa');
insert into Route_Details values('R002','chennai',450,'kolkata');
insert into Route_Details values('R003','goa',700,'chennai');
insert into Route_Details values('R004','Kolkata',450,'chennai');

-- ====== Ship_Details Table: =======

insert into Ship_Details (ship_id,ship_name,ship_model,ship_capacity,ship_reservation_capacity,per_km_rate) 
values('S001','USNS Wally Schirra','Catboat',800,'400',1000);
insert into Ship_Details (ship_id,ship_name,ship_model,ship_capacity,ship_reservation_capacity,per_km_rate) 
values('S002','HMS Dreadnought','Brigantine',600,'300',1500);
insert into Ship_Details (ship_id,ship_name,ship_model,ship_capacity,ship_reservation_capacity,per_km_rate) 
values('S003','RMS Mauretania','Schooner',600,'300',1500);
insert into Ship_Details (ship_id,ship_name,ship_model,ship_capacity,ship_reservation_capacity,per_km_rate) 
values('S004','USS Thresher SSN-593','Merchantman',400,'150',3000);

-- ====== Ship_Schedule Table: =====

insert into Ship_Schedule (ss_id, journey_date, journey_time, seat_availability, ship_details_id, route_id)
values(10010,'2023-02-15','7:00:00','80','S001','R001');
insert into Ship_Schedule (ss_id, journey_date, journey_time, seat_availability, ship_details_id, route_id)
values(10011,'2023-02-17','8:00:00','60','S001','R001');
insert into Ship_Schedule (ss_id, journey_date, journey_time, seat_availability, ship_details_id, route_id)
values(10012,'2023-02-16','9:00:00','60','S001','R001');

insert into Ship_Schedule (ss_id, journey_date, journey_time, seat_availability, ship_details_id, route_id)
values(10013,'2023-02-17','8:00','60','S002','R002');
insert into Ship_Schedule (ss_id, journey_date, journey_time, seat_availability, ship_details_id, route_id)
values(10014,'2023-02-18','9:00','60','S002','R002');

-- ====== Passenger Table: ======
insert into Passenger (p_email, p_address, p_name, p_password, p_phone, p_role, url)
values('admin@ad.com','India-WB','Admin','admin','6296712293','ADMIN','admin-admin');
insert into Passenger values('akash@gmail.com', 'Kolkata', 'Akash', 'a123', '6396529637', 'PASSENGER', 'aaaaaaaaa');
insert into Passenger values('som@gmail.com', 'Bihar', 'Somal', 's123', '6396523456', 'PASSENGER', 'sssss');
insert into Passenger values('chini@gmail.com', 'Kolkata', 'Chini', 'c123', '7936523465', 'PASSENGER', 'cccccc');
insert into Passenger values('vijay@gmail.com', 'Mumbai', 'Vijay', 'v123', '9986523123', 'PASSENGER', 'vvvvvvv');
insert into Passenger values('kumar@gmail.com', 'Delhi', 'Kumar', 'k123', '8496523457', 'PASSENGER', 'kkkkkkkk');

insert into Passenger values('piku@gmail.com', 'Kochi', 'Piku', 'p123', '7864530216', 'PASSENGER', 'ppppp');

alter table passenger modify url blob;
alter table passenger modify p_address blob;

-- ====== Booking Table: =======

insert into Booking (booking_id, booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values(12350, '2022-11-08', 3,'Akash',4000,10010,'akash@gmail.com');

insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-08', 3,'Somal',2000,10011,'som@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-08', 3,'kumar',3000,10012,'kumar@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-13', 3,'vijay',2000,10015,'vijay@gmail.com');


insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-10', 3,'Akash',2000,10015,'akash@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-09', 3,'Somal',3000,10012,'som@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-08', 3,'kumar',3000,10013,'kumar@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-14', 3,'Vijay',3000,10017,'vijay@gmail.com');



insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-12', 3,'Akash',4000,10017,'akash@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-11', 3,'Somal',2000,10015,'som@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-08', 3,'kumar',3000,10014,'kumar@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-15', 3,'Vijay',2000,10016,'vijay@gmail.com');


insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-13', 3,'Akash',1000,10012,'akash@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-08', 3,'Somal',2000,10014,'som@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-10', 3,'kumar',3000,10015,'kumar@gmail.com');

insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-12', 3,'Somal',3000,10016,'som@gmail.com');
insert into Booking (booking_date, no_of_head_count, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-12', 3,'kumar',4000,10016,'kumar@gmail.com');


--  Testing of Booking 
insert into Booking (booking_date, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-15','Vijay',2000,10016,'vijay@gmail.com');
insert into Booking (booking_date, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-15','Vijay',2000,10016,'vijay@gmail.com');
insert into Booking (booking_date, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-15','Vijay',2000,10016,'vijay@gmail.com');
insert into Booking (booking_date, p_name, payment_amount, ship_sch_id, p_email)
values('2022-11-15','Vijay',2000,10016,'vijay@gmail.com');

--  ==== Head_Count Table: =====

insert into Head_Count (hc_id, hc_name, hc_age, hc_gender, hc_booking_id)
values(100, 'Akash',45,'MALE','12350');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Rose',38,'FEMALE','12350');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Raju',28,'MALE','12350');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Somal',34,'MALE','12351');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Swata',26,'FEMALE','12351');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Chinmoy',14,'MALE','12351');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('kumar',40,'MALE','12352');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Rockey',35,'MALE','12352');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Subham',28,'MALE','12352');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Vijay',45,'MALE','12353');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Pooja',38,'FEMALE','12353');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Soumen',28,'MALE','12353');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Uttam',56,'MALE','12354');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Madhuri',38,'FEMALE','12354');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Harry',28,'MALE','12354');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Priya',34,'FEMALE','12355');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Chandra',38,'FEMALE','12355');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Anuka',28,'MALE','12355');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Parijat',56,'MALE','12356');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Partha',38,'FEMALE','12356');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Priya',34,'FEMALE','12356');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Vimal',36,'MALE','12357');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Harry',28,'MALE','12357');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sai',38,'MALE','12357');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Rohit',56,'MALE','12358');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Pratima',38,'FEMALE','12358');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Amit',40,'MALE','12358');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sohini',25,'FEMALE','12359');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Pratima',38,'FEMALE','12359');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Biswajit',29,'MALE','12359');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Raka',23,'FEMALE','12360');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Riya',25,'FEMALE','12360');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sohini',25,'FEMALE','12360');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Kobita',18,'FEMALE','12361');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Rinku',34,'FEMALE','12361');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Raka',23,'FEMALE','12361');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Goutom',53,'MALE','12362');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Rakib',43,'MALE','12362');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Situ',25,'MALE','12362');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Jit',53,'MALE','12363');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sabir',43,'MALE','12363');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Kunal',25,'MALE','12363');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sita',45,'FEMALE','12364');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Sabir',43,'MALE','12364');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Gopal',55,'MALE','12364');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Miraj',27,'MALE','12365');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Gopal',55,'MALE','12365');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Jockey',31,'MALE','12365');

insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Meghna',38,'FEMALE','12366');  
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Maruti',19,'MALE','12366');
insert into Head_Count (hc_name, hc_age, hc_gender, hc_booking_id)
values('Abhijit',28,'MALE','12366');


-- ==============================================================================================================
-- ========================================  [For Admin View only]  =============================================

Select bk.booking_Id, bk.p_email, bk.p_name, (bk.payment_amount), bk.booking_date, bk.ship_sch_id,
	   ss.ss_id, ss.journey_time, ss.journey_date, ss.seat_availability, ss.route_id, ss.ship_details_id
from Ship_Schedule ss 
Inner join Booking bk ON
bk.ship_sch_id = ss.ss_Id and bk.p_email = 'agni@gmail.com'; 

Select bk.booking_Id, bk.p_email, bk.p_name, (bk.payment_amount), bk.booking_date, bk.ship_sch_id,
	   ss.ss_id, ss.journey_time, ss.journey_date, ss.seat_availability, ss.route_id, ss.ship_details_id,
       sd.ship_id, sd.per_km_rate, sd.ship_capacity, sd.ship_model, sd.ship_name, sd.ship_reservation_capacity
from Ship_Details sd       
Inner join Ship_Schedule ss ON
ss.ship_details_id = sd.ship_id
Inner join Booking bk ON
bk.ship_sch_id = ss.ss_Id and bk.p_email = 'agni@gmail.com'; 

-- bk.p_email, bk.booking_date
select  *
from Booking bk
Inner Join Ship_Schedule ss ON
bk.ship_sch_id = ss.ss_Id and ss.journey_date='2023-02-15'; 



-- bk.booking_id, bk.p_email,bk.p_name, bk.booking_date, ps.p_phone, ps.p_address 
select bk.booking_id, bk.p_email,bk.p_name, bk.booking_date, bk.no_of_head_count, bk.payment_amount, ps.p_phone, ps.p_address 
from passenger ps
Inner Join Booking bk ON
bk.p_email = ps.p_email
Inner Join Ship_Schedule ss ON
bk.ship_sch_id = ss.ss_Id 
Inner join Ship_Details sd ON
sd.ship_id = ss.ship_details_id
where ss.journey_date = '2023-02-18' and sd.ship_name='Mauretania_HMS';


-- ========================================  [For User View only]  ===============================================
-- sd.ship_name, sd.ship_model, sd.ship_capacity, sd.ship_reservation_capacity, sd.per_km_rate, 
-- 	   ss.journey_date, ss.journey_time, ss.seat_availability
select sd.ship_name, sd.ship_model, sd.ship_capacity, sd.ship_reservation_capacity, sd.per_km_rate, 
	   ss.journey_date, ss.journey_time, ss.seat_availability, ss.ss_id, sd.ship_id
from Ship_Details sd
Inner join Ship_Schedule ss ON
ss.ship_details_id = sd.ship_id
Inner join Route_Details rd ON
rd.source_point = 'goa' and rd.destination_point = 'chennai' and rd.route_id = ss.route_id and ss.seat_availability>10
order by ss.journey_date;
-- $rd.sourcePoint | $rd.destinationPoint


select *
from passenger ps
inner join booking bk
on bk.p_email = ps.p_email
inner join ship_schedule ss
on ss.ss_id = bk.ship_sch_id
inner join route_details rd
on rd.route_id=ss.route_id and rd.route_id='R001';


select distinct bk.booking_id, ps.p_email, ps.p_name, ps.p_phone, ps.p_address, ps.url,
	   ss.journey_date, ss.journey_time,
       rd.source_point, rd.destination_point, rd.distance_km,
       sd.ship_name, sd.ship_model,
       -- hc.hc_name, hc.hc_age, hc.hc_gender,
       bk.no_of_head_count, bk.payment_amount
from passenger ps
inner JOIN booking bk
ON bk.p_email = ps.p_email
inner JOIN ship_schedule ss
ON ss.ss_id = bk.ship_sch_id
inner JOIN head_count hc
ON hc.hc_booking_id = bk.booking_id
inner JOIN route_details rd
ON rd.route_id = ss.route_id
inner JOIN ship_details sd
ON sd.ship_id = ss.ship_details_id
and bk.booking_id=12355;

select hc.hc_name, hc.hc_age, hc.hc_gender
from passenger ps
inner JOIN booking bk
ON bk.p_email = ps.p_email
inner JOIN ship_schedule ss
ON ss.ss_id = bk.ship_sch_id
inner JOIN head_count hc
ON hc.hc_booking_id = bk.booking_id
inner JOIN route_details rd
ON rd.route_id = ss.route_id
inner JOIN ship_details sd
ON sd.ship_id = ss.ship_details_id
and bk.booking_id=12355;
