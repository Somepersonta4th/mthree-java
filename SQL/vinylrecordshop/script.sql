USE vinylrecordshop;
SET GLOBAL local_infile = 1;

DELETE FROM artist WHERE artistId < 30;

LOAD DATA LOCAL INFILE 'C:/Users/Sean/Desktop/mthree/SQL/vinylrecordshop/vinylrecordshop-data/artist.csv'
INTO TABLE vinylrecordshop.artist 
FIELDS TERMINATED BY ',';

SELECT * FROM artist;