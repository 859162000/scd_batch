
use fcore_part_0;

--statistics
insert into s_projectlimit values(
1, 'project_code2', 'project_name', 1000000, 200000,
800000, 10000, 11000,200010, 101,
10002, 1, CURRENT_DATE, CURRENT_DATE);

insert into s_fixproductduestat values(
1, CURRENT_DATE, 1000,2000,3000,
4000,5000, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into s_expenditurestat values(
1, CURRENT_DATE,1,10003,1,
CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
);

insert into s_borrowerrepayplan values(
1,CURRENT_DATE, 100, 'BORROWER_NAME', 'project_code2',
'PROJECT_NAME', 1, 100,332,322,
333,222,222,0,CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP
);

insert into s_assetsstat values(
1, CURRENT_DATE,2000, 300,4000,
111,333,0,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

insert into s_fundstat values(
1, 10, 100,CURRENT_DATE, 0,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);