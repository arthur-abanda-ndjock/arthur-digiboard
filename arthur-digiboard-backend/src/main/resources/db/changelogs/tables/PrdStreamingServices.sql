-- Streaming Services table
CREATE TABLE PrdStreamingServices (
    ServiceID INT PRIMARY KEY,
    Name VARCHAR(100),
    Platform VARCHAR(50),
    SubscriptionCost DECIMAL(10, 2),
    ContentLibrarySize INT -- Size in GB, for example
);
