-- E-books table
CREATE TABLE PrdEBooks (
    BookID INT PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(100),
    Genre VARCHAR(50),
    ISBN VARCHAR(20),
    Price DECIMAL(10, 2),
    PublicationDate DATE
);
