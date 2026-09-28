-- Running this script will DELETE the existing database and all data it contains.
-- Use with caution.

DROP DATABASE IF EXISTS vinylrecordshop;

CREATE DATABASE vinylrecordshop;

USE vinylrecordshop;

CREATE TABLE IF NOT EXISTS album (
    albumId INT(11) NOT NULL,
    albumTitle varchar(50) NOT NULL,
    releaseDate date NOT NULL,
    price float NOT NULL,
    labelId int(11) NOT NULL,
    CONSTRAINT pk_album PRIMARY KEY (albumId)
);

CREATE TABLE artist (
    artistId INT(11) NOT NULL AUTO_INCREMENT,
    fname VARCHAR(40) NOT NULL,
    lname varchar(40) NOT NULL,
    isHallOfFame tinyint(1) NOT NULL,
    CONSTRAINT pk_artist PRIMARY KEY (artistId)
);

CREATE TABLE band (
	bandId INT AUTO_INCREMENT,
	bandName VARCHAR(50) NOT NULL,
	CONSTRAINT pk_band PRIMARY KEY (bandId)
);

CREATE TABLE song (
    songId int NOT NULL AUTO_INCREMENT,
    songTitle varchar(100) NOT NULL,
    videoUrl varchar(100),
    bandId int NOT NULL,
    CONSTRAINT pk_song 
    	PRIMARY KEY (songId),
    CONSTRAINT fk_song_band 
    	FOREIGN KEY (bandID)
    	REFERENCES band(bandId)
);

CREATE TABLE songAlbum (
    songId int,
    albumId int,
    CONSTRAINT pk_songAlbum 
    	PRIMARY KEY (songId, albumId),
    CONSTRAINT fk_songAlbum_song
    	FOREIGN KEY (songId)
    	REFERENCES song(songId),
    CONSTRAINT fk_songAlbum_album
    	FOREIGN KEY (albumId)
    	REFERENCES album(albumId)
);

CREATE TABLE bandArtist (
	bandId INT,
	artistId INT,
	CONSTRAINT pk_bandArtist 
        PRIMARY KEY (bandId, artistId),
	CONSTRAINT fk_bandArtist_band 
        FOREIGN KEY (bandId)
		REFERENCES band(bandId),
	CONSTRAINT fk_bandArtist_artist 
        FOREIGN KEY (artistId)
		REFERENCES artist (artistId)
);

INSERT INTO `artist` VALUES (1,'artist.lname','artist.fname',0),(2,'McCartney','Paul',1),(3,'Harrison','George',1),(4,'Starr','Ringo',1),(5,'Zager','Denny',0),(6,'Evans','Rick',0),(10,'Morrison','Van',1),(11,'Collins','Judy',0),(12,'Simon','Paul',1),(13,'Garfunkel','Art',0),(14,'Wilson','Brian',0),(15,'Wilson','Dennis',0),(16,'Wilson','Carl',0),(17,'Fataar','Ricky',0),(18,'Chaplin','Blondie',0),(19,'Page','Jimmy',0),(20,'Plant','Robert',0),(21,'Jones','John Paul',0),(22,'Bonham','John',0),(23,'Love','Mike ',0),(24,'Jardine','Al ',0),(25,'Marks','David',0),(26,'Johnston','Bruce ',0);
INSERT INTO `album` VALUES (1,'Imagine','1971-09-09',9.99,1),(2,'2525 (Exordium & Terminus)','1969-07-01',25.99,2),(3,'No One\'s Gonna Change Our World','1969-12-12',39.35,3),(4,'Moondance Studio Album','1969-08-01',14.99,4),(5,'Clouds','1969-05-01',9.99,5),(6,'Sounds of Silence Studio Album','1966-01-17',9.99,6),(7,'Abbey Road','1969-01-10',12.99,1),(9,'Smiley Smile','1967-09-18',5.99,7);
INSERT INTO `band` VALUES (1,'The Beatles'),(2,'Zager and Evans'),(3,'Van Morrison'),(4,'Judy Collins'),(5,'Simon and Garfunkel'),(7,'Beach Boys'),(8,'Led Zeppelin');
INSERT INTO `song` VALUES (1,'Imagine','https://youtu.be/DVg2EJvvlF8',1),(2,'In the Year 2525','https://youtu.be/izQB2-Kmiic',2),(3,'Across the Universe','https://youtu.be/Tjq9LmSO1eI',1),(4,'Moondance','https://youtu.be/6lFxGBB4UG',3),(5,'Both Sides Now','https://youtu.be/rQOuxByR5VI',4),(6,'Sounds of Silence','https://youtu.be/qn0QBXMYXsM',5),(7,'Something','https://youtu.be/xLGe-QzCK4Q',1),(9,'Good Vibrations','https://youtu.be/d8rd53WuojE',7),(10,'Come Together','https://youtu.be/_HONxwhwmgU',1),(11,'Something','https://youtu.be/UKAp-jRUp2o',1),(12,'Maxwell\'s Silver Hammer','https://youtu.be/YQgsob_o1io',1);
INSERT INTO `bandartist` VALUES (1,1),(1,2),(1,3),(1,4),(2,5),(2,6),(3,10),(4,11),(5,12),(5,13),(7,14),(7,15),(7,16),(7,17),(7,18),(8,19),(8,20),(8,21),(8,22),(7,23),(7,24),(7,25),(7,26);
INSERT INTO `songalbum` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(10,7),(11,7),(12,7),(9,9);