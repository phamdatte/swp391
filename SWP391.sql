
--CREATE DATABASE swp391;
--USE swp391;



CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    PhoneNum VARCHAR(20) NOT NULL UNIQUE,
    Address VARCHAR(255) NOT NULL,
    YearOfBirth INT CHECK (YearOfBirth >= 1900 AND YearOfBirth <= YEAR(GETDATE()) - 10),
    Status BIT NOT NULL DEFAULT 1,
	Role VARCHAR(10) NOT NULL;
);

CREATE TABLE Cinema (
    CinemaID INT PRIMARY KEY IDENTITY(1,1),
    CinemaName VARCHAR(255) NOT NULL UNIQUE,
    Address VARCHAR(255) NOT NULL
);

CREATE TABLE CinemaRoom (
    RoomID INT PRIMARY KEY IDENTITY(1,1),
    CinemaID INT NOT NULL,
    RoomName VARCHAR(255) NOT NULL,
    FOREIGN KEY (CinemaID) REFERENCES Cinema(CinemaID) ON DELETE CASCADE
);

CREATE TABLE Movie (
    MovieID INT PRIMARY KEY IDENTITY(1,1),
    MovieName VARCHAR(255) NOT NULL,
    Duration INT CHECK (Duration > 0),
    Genre VARCHAR(255) NOT NULL,
    Director VARCHAR(255) NOT NULL,
    ReleaseDate DATETIME NOT NULL,
	MoviePoster image NOT NULL,
    Description TEXT,
    Rate VARCHAR(10),  
);

CREATE TABLE Showtime (
    ShowtimeID INT PRIMARY KEY IDENTITY(1,1),
    MovieID INT NOT NULL,
    StartTime DATETIME NOT NULL CHECK (StartTime >= GETDATE()),
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID) ON DELETE CASCADE
);

CREATE TABLE Seat (
    SeatID INT PRIMARY KEY IDENTITY(1,1),
    SeatRow VARCHAR(5) NOT NULL,
    SeatNumber INT NOT NULL CHECK (SeatNumber > 0),
    SeatType VARCHAR(50) NOT NULL,
    RoomID INT NOT NULL,
    FOREIGN KEY (RoomID) REFERENCES CinemaRoom(RoomID) ON DELETE CASCADE
);

CREATE TABLE Ticket (
    TicketID INT PRIMARY KEY IDENTITY(1,1),
    SeatID INT NOT NULL,
    ShowtimeID INT NOT NULL,
    FOREIGN KEY (SeatID) REFERENCES Seat(SeatID) ON DELETE CASCADE,
    FOREIGN KEY (ShowtimeID) REFERENCES Showtime(ShowtimeID) ON DELETE CASCADE
);

CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY IDENTITY(1,1),
    Quantity INT NOT NULL CHECK (Quantity > 0),
    Amount FLOAT NOT NULL CHECK (Amount >= 0),
    TicketID INT NOT NULL,
    CustomerID INT NOT NULL,
    FOREIGN KEY (TicketID) REFERENCES Ticket(TicketID) ON DELETE CASCADE,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE
);

select*from Account

insert into Customer values ( 'Dat Pham', 'phamdatte@gmail.com', '123', '0987654321', 'Hung Yen', 2004, 1);
