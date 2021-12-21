{\rtf1\ansi\ansicpg1250\cocoartf2580
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 --9.1.--\
select kW_performance, nm_torque, ccm_volume from engine;\
\
--9.2.--\
select first_name, last_name, email from name n, contact c\
where c.email = 'matejhrabalek@mail.com'and n.last_name = 'Hrab\'e1lek';\
\
--9.3--\
update car\
set is_available = false\
where licence_plate = '3BA0001';\
\
delete from car\
where licence_plate = '3BA0001';\
select * from car where licence_plate = '3BA0001';\
\
insert into car (licence_plate, tank_condition, is_available, status_id, brand_id, equipment_id)\
values ('3BA0051', 100, true, 2, 33, 1);\
select licence_plate, status_id, brand_id, equipment_id \
from car where licence_plate = '3BA0051';\
\
alter table insurance\
add if not exists scratching_the_car boolean;\
\
update insurance\
set scratching_the_car = false\
where insurance_id > 0;\
select * from insurance;\
\
--9.4.--\
select name, model, year, color from brand where concern = 'VW Group';\
\
select first_name, last_name from name n, employee e \
where first_name like 'M%'and first_name not like '%j';\
\
select substring ('Creator of the project is Mat\uc0\u283 j Hrab\'e1lek', 27, 5) as extra_string;\
\
select trim ('.,!' from 'Mat\uc0\u283 j Hrab\'e1lek.!,') as trimmed;\
\
select count(*) from contact;\
\
select avg (distinct phone_number), sum (distinct phone_number) from contact;\
\
select min (distinct ccm_volume), max (distinct ccm_volume) from engine;\
\
select kW_performance, avg (ccm_volume) from engine\
group by kW_performance;\
\
select kW_performance, avg (ccm_volume) from engine\
group by kW_performance\
having avg(ccm_volume) > 2000;\
\
select kW_performance, avg (ccm_volume) from engine\
where has_turbo = true\
group by kW_performance\
having avg(ccm_volume) > 2000;\
\
select last_name from name n, employee e\
union\
select last_name from name n, customer c\
order by last_name;\
\
select city from address a, employee e\
union all\
select city from address a, customer c\
order by city;\
\
select distinct name from brand where concern = 'VW Group';\
\
select last_name, email from name\
inner join customer on customer.name_id = name.name_id\
inner join contact on contact.contact_id = customer.contact_id\
where contact.email = 'matejhrabalek@mail.com';\
\
select last_name, email from name\
full outer join customer on customer.name_id = name.name_id\
full outer join contact on contact.contact_id = customer.contact_id\
where contact.email = 'matejhrabalek@mail.com';\
\
select last_name, email from name\
right join customer on customer.name_id = name.name_id\
left join contact on contact.contact_id = customer.contact_id\
where contact.email = 'matejhrabalek@mail.com';\
\
--9.5.--\
select distinct brand.name, brand.model from car\
left join brand\
on car.brand_id = brand.brand_id\
group by brand.name, brand.model\
having avg (brand.capacity) = 4\
order by brand.name;\
\
--9.6.--\
-- note: made after 9.7.!--\
select time_of_registration from customer\
where time_of_registration > now () - interval '36 hours';\
\
--9.7.--\
alter table customer\
add if not exists time_of_registration date;\
\
update customer\
set time_of_registration = now()\
where customer_id > 0;\
\
select distinct n.first_name, n.last_name, time_of_registration \
from customer c, name n\
where date_trunc ('month',now()) = date_trunc ('month', time_of_registration)\
order by first_name;\
\
--9.8.--\
insert into name (first_name, last_name)\
values ('\uc0\u344 eho\u345 ', '\u344 e\u345 icha');\
\
create extension if not exists unaccent;\
\
select distinct unaccent (first_name), unaccent (last_name)\
from name where last_name = '\uc0\u344 e\u345 icha';\
\
--9.9.--\
select brand_id, name, model, year, color, capacity\
from brand offset 25 limit 10;\
\
--9.10.--\
select distinct c.nationality, n.first_name, n.last_name\
from (select nationality from customer) as c,\
(select first_name, last_name from name) as n;\
\
--9.11.--\
select licence_plate, car_id from car\
where brand_id = \
(select brand_id from brand where year = 2017);\
\
--9.12.--\
select q.name_id from\
	(select name_id from employee \
	union\
	select name_id from customer) as q\
group by q.name_id\
having q.name_id >= 6\
order by q.name_id;\
\
--9.13.--\
select first_name, last_name email\
from name\
full outer join customer on customer.name_id = name.name_id\
full outer join employee on employee.name_id = name.name_id\
inner join contact on contact.contact_id = customer.contact_id\
inner join credential on credential.credential_id = customer.credential_id\
inner join address on address.address_id = customer.address_id;\
\
--9.14.--\
select first_name, last_name email\
from name\
full outer join customer on customer.name_id = name.name_id\
full outer join employee on employee.name_id = name.name_id\
inner join contact on contact.contact_id = customer.contact_id\
inner join credential on credential.credential_id = customer.credential_id\
inner join address on address.address_id = customer.address_id\
where email like 'm%'\
group by first_name, last_name, email --not necessary in this case, however, matches the condition--\
having first_name like 'M%'\
order by last_name;\
\
--9.15.--\
--cascading made by creating new tables, explained in the document!\
alter table status\
alter column bodywork\
type varchar(100);\
\
--9.16.--\
create unique index engine_note on engine (name);\
\
explain select * from name where title_before_name is not null;\
create index university_idx on name (title_before_name);\
\
--9.17.--\
create procedure car_info()\
	language 'sql'\
	as $car$\
	\
	select distinct b.name, b.model\
	from car, brand b\
$car$;\
\
--9.18.--\
create function check_age()\
	returns trigger\
	language 'plpgsql'\
	as $$\
    begin
	if new.date_of_birth > '14.11.2003' then\
		insert into name (first_name, last_name) \
		values (old.first_name, old.last_name);\
	end if;\
	return new;\
end;\
$$;\
\
create trigger age_control\
	before update on name\
	for each row\
	execute procedure check_age();\
	\
--9.19.--\
create view auto_database_owner as\
select employed_since, place_of_employment, wage\
from employee e;\
\
--9.20.--\
create materialized view moravia_banks as\
select nationality, count(bank_id) from bank\
where residence = 'Brno'or residence = 'Olomouc' or residence = 'Uhersk\'e9 Hradi\'9at\uc0\u283 '\
group by nationality\
having nationality = '\uc0\u268 esk\'e1 republika';\
\
--9.21.--\
create role teacher;\
grant select, insert, update, delete \
on brand, car, engine, equipment, status, tuning to teacher;\
\
create view teacher_auto_view as\
select b.brand_id, c.car_id, e.engine_id, x.equipment_id, s.status_id, d.tuning_id\
from brand b, car c, engine e, equipment x, status s, tuning d;\
\
grant select on teacher_auto_view to teacher;\
\
create role student;\
\
grant select on brand, car, engine, equipment, status, tuning to student;\
\
create view student_auto_view as\
select b.name, c.car_id, e.ccm_volume, e.kW_performance, \
e.nm_torque, s.bodywork, s.fuel, s.mileage, d.mm_lowering\
from brand b, car c, engine e, status s, tuning d;\
\
grant select on student_auto_view to student;\
grant select on student_auto_view to teacher;}