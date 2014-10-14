connect 'jdbc:derby:testdb5';

DROP TABLE albums;
DROP TABLE groups;
DROP TABLE studios;

CREATE TABLE groups (
  groupName VARCHAR(15),
  leadSinger VARCHAR(20),
  yearFormed VARCHAR(4),
  genre VARCHAR(12),
  CONSTRAINT pk_groups PRIMARY KEY (groupName)
  );
  
CREATE TABLE studios (
  studioName VARCHAR(15),
  studioAddress VARCHAR(20),
  studioOwner VARCHAR(15),
  studioPhone VARCHAR(12),
  CONSTRAINT pk_studios PRIMARY KEY (studioName)
  );
  
CREATE TABLE albums (
  albumTitle VARCHAR(20),
  dateRecorded VARCHAR(15),
  length VARCHAR(6),
  numberOfSongs INTEGER,
  groupName VARCHAR(15) REFERENCES groups (groupName),
  studioName VARCHAR(15) REFERENCES studios (studioName),
  CONSTRAINT fk_albums_groups FOREIGN KEY (groupName) REFERENCES groups (groupName),
  CONSTRAINT fk_albums_studios FOREIGN KEY (studioName) REFERENCES studios (studioName),
  CONSTRAINT pk_albums PRIMARY KEY (albumTitle, dateRecorded)
  );
  
INSERT INTO studios (studioName, studioAddress, studioOwner, studioPhone)
 VALUES ('Trendy Songs', '123 Main Street', 'Jeff Trendy', '408-555-2323');
INSERT INTO studios (studioName, studioAddress, studioOwner, studioPhone)
 VALUES ('Jazz Hands', '124 Main Street', 'Jazzmine Handy', '408-555-2525');

INSERT INTO groups (groupName, leadSinger, yearFormed, genre)
 VALUES ('The Who', 'Roger Daltrey', '1964', 'Rock');
INSERT INTO groups (groupName, leadSinger, yearFormed, genre)
 VALUES ('The What', 'Alan Rickman', '1992', 'Alternative');
INSERT INTO groups (groupName, leadSinger, yearFormed, genre)
 VALUES ('The When', 'John Foggerty', '1972', 'Rock');
INSERT INTO groups (groupName, leadSinger, yearFormed, genre)
 VALUES ('The Where', 'Jay-Z', '1991', 'Rap');

INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('My Generation', 'April 1965', '36:13', 12, 'The Who', 'Trendy Songs');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('A Quick One', 'September 1966', '38:46', 10, 'The Who', 'Trendy Songs');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('The Who Sell Out', 'May 1967', '36:09', 13, 'The Who', 'Trendy Songs');

INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('No More', 'June 1992', '36:43', 7, 'The What', 'Trendy Songs');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Please No More', 'August 1992', '27:03', 5, 'The What', 'Trendy Songs');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Ok, Maybe A Little More', 'January 1993', '39:53', 8, 'The What', 'Trendy Songs');

INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Take A Break', 'November 1972', '32:00', 5, 'The When', 'Jazz Hands');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Now Get Back To Work', 'February 1973', '22:07', 5, 'The When', 'Jazz Hands');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Lunch Time', 'October 1973', '41:13', 8, 'The When', 'Jazz Hands');

INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('My Homies', 'July 1992', '31:14', 6, 'The Where', 'Jazz Hands');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('My Little Homies', 'December 1992', '29:23', 5, 'The Where', 'Jazz Hands');
INSERT INTO albums (albumTitle, dateRecorded, length, numberOfSongs, groupName, studioName)
 VALUES ('Something Clever', 'April 1993', '26:44', 4, 'The Where', 'Jazz Hands');

select * from studios;
select * from groups;
select * from albums;