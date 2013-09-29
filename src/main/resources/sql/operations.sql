// get access of employee

select access from employee where id =

// get access of employee group

select g.access from employee e, employee_group g where e.groupid = g.id;

