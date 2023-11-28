-- Orders table
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    ProductType VARCHAR(20), -- Indicates whether it's a software application, e-book, etc.
    ProductID INT, -- ID of the specific product (SoftwareID, BookID, etc.)
    OrderDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES PrdCustomers(CustomerID)
    -- ,
    -- CONSTRAINT CHK_ProductType CHECK (ProductType IN ('Software', 'EBook', 'PrdDigitalMusic', 'OnlineCourse')),
    -- CONSTRAINT FK_ProductID CHECK (
    --     (ProductType = 'Software' AND ProductID IN (SELECT SoftwareID FROM PrdSoftwareApplications)) OR
    --     (ProductType = 'EBook' AND ProductID IN (SELECT BookID FROM PrdEBooks)) OR
    --     (ProductType = 'PrdDigitalMusic' AND ProductID IN (SELECT TrackID FROM PrdDigitalMusic)) OR
    --     (ProductType = 'OnlineCourse' AND ProductID IN (SELECT CourseID FROM PrdOnlineCourses))
    -- )
);