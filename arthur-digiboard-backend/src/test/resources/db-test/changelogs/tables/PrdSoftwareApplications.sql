-- Software Applications table
CREATE TABLE PrdSoftwareApplications (
    SoftwareID INT PRIMARY KEY,
    Name VARCHAR(100),
    Version VARCHAR(20),
    Developer VARCHAR(100),
    LicenseType VARCHAR(50),
    Price DECIMAL(10, 2),
    ReleaseDate DATE,
    Platform VARCHAR(50)
);
