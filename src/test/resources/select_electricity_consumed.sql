select village.villagename as village_name, 
sum(electricityconsumption.amount_in_watts) as amount 
from village 
inner join building on village.villageid=building.village 
inner join apartment on building.buildingid=apartment.building 
inner join electricitycounter on apartment.apartmentid=electricitycounter.apartment 
inner join electricityconsumption on electricitycounter.electricitycounterid=electricityconsumption.electricitycounter 
where electricityconsumption.starttime between '2019-07-09 00:27:00' and '2019-07-10 00:27:00' 
group by village.villagename