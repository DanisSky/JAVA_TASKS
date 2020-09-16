1. SELECT model, speed, hd from PC WHERE price<500
2. SELECT DISTINCT maker from Product where type = 'Printer'
3. select model, ram, screen from laptop where price > 1000
4. Select * from printer where color='y'
5. select model, speed, hd from PC where (cd='12x' or cd='24x') and price<'600'
6. select  distinct maker, speed
from product, laptop
where product.model = laptop.model and hd >= 10
7. select laptop.model, laptop.price
from laptop, product 
where product.maker='B' and product.model = laptop.model
union
select pc.model, pc.price
from pc, product 
where product.maker='B' and product.model = pc.model
union
select printer.model, printer.price
from printer, product 
where product.maker='B' and product.model = printer.model
8. Select maker from product where type='PC'
except
select maker from product where type='Laptop'
9. Select distinct maker 
from product
join pc on product.model = pc.model
where 
pc.speed >=450
10. select model, price from printer
where price = (select max(price) from printer)
11. Select AVG(speed) from pc
12. Select avg(speed) from laptop
where price > 1000
13. Select avg(speed) from pc
join product on (product.model = pc.model)
where product.maker ='A'
14. Select ships.class, ships.name, country
from ships
join classes on (classes.class = ships.class)
where numGuns >=10
15. Select hd
from pc
group by hd
having count(hd) >= 2
16. SELECT DISTINCT A.model AS mod1, B.model AS mod2, A.speed, A.ram
FROM PC AS A, PC B
where A.speed = B.speed and A.ram = B.ram and A.model > B.model
17. SELECT distinct product.type, laptop.model, laptop.speed
from laptop
join product ON laptop.model=product.model
where laptop.speed <(select min(speed) from pc)
18. select distinct maker, price
from product
join printer on product.model = printer.model
where price = (select min(price) from printer where color='y') and color='y'
19. Select maker, avg(laptop.screen)
from product
join laptop on product.model = laptop.model
group by maker
20. Select maker, count(model) from product 
where type = 'pc' 
group by maker 
having count(model) >= 3
21. Select maker, max(price)
from product
join pc on product.model = pc.model
group by maker
22. Select speed, avg(price)
from pc
where speed > 600
group by speed
23. Select maker from product
join pc on pc.model = product.model
where pc.speed >= 750 
intersect
Select maker from product
join laptop on laptop.model = product.model
where laptop.speed >= 750
24. select model
from (
	select model, price from pc
	union
	select model, price from laptop
	union
	select model, price from printer
) all_models
where price = (
		select max(price)
		from (
			select model, price from pc
			union
			select model, price from laptop
			union
			select model, price from printer
	  	) max_price
	  )
25. select distinct product.maker 
from product 
where product.type='Printer'  
intersect
select distinct product.maker from product join pc on pc.model=product.model  
where product.type='PC' and pc.ram=(select min(ram) from pc)  
and pc.speed = (select max(speed) from (select distinct speed from pc 
where pc.ram=(select min(ram) from pc)) as res)
26. select avg(res.price)
from (select price from product as pr join pc on pr.model = pc.model where maker='A'
union all
select price from product as pr join laptop on pr.model = laptop.model where maker='A')
as res
27. select maker,avg(hd)  
from product 
join pc on product.model=pc.model   
where maker in(select maker  from product  where type='printer')  group by maker
28. select count (*) from (select maker from product 
group by maker 
having count(model) = 1) as res
29. select inc.point, inc.date, inc, out
from Income_o as inc 
left join Outcome_o as out on inc.point = out.point and inc.date = out.date
union
select out.point, out.date, inc, out
from Outcome_o as out 
left join Income_o as inc on inc.point = out.point and inc.date = out.date
30. select point, date, sum(out), sum(inc) 
from( select point, date, sum(inc) as inc, null as out from Income Group by point, date  
Union 
select point, date, null as inc, sum(out) as out 
from Outcome 
group by point, date ) as res
group by point, date order by point
