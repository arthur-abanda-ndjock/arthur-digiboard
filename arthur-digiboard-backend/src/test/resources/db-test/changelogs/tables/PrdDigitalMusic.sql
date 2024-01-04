-- Digital Music table
CREATE TABLE PrdDigitalMusic (
    TrackID INT PRIMARY KEY,
    Title VARCHAR(100),
    Artist VARCHAR(100),
    Album VARCHAR(100),
    Genre VARCHAR(50),
    Duration TIME,
    ReleaseDate DATE,
    Price DECIMAL(10, 2)
);
