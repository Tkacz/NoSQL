CREATE TABLE Events (
	id int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	DateOccurred varchar(10),
	DateReported varchar(10),
	Location varchar(128),
	ShortDescription varchar(10),
	Duration varchar(60),
	LongDescription varchar(30720),
	USCity varchar(60),
	USState varchar(60),
	YearMonth varchar(7)
);
