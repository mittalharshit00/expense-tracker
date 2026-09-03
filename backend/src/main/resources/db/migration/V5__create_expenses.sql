CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(12,2) NOT NULL CHECK (amount >0),
    description VARCHAR(500) NOT NULL,
    expense_date DATE NOT NULL,
    category_id INT NOT NULL, 
    FOREIGN KEY (category_id) REFERENCES categories(id)
);