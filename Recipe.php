<?php
class Recipe
{
    // DB stuff
    private $conn;
    private $table = 'recipe';

    // Post properties
    public $id;
    public $name;
    public $subcategory;
    public $vegetarian;
    public $portions;
    public $steps;
    public $hours;
    public $ingredients;
    public $minutes;
    public $created_on;
    public $approved;
    public $owner_id;

    // Constructor with DB
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // Get recipes
    public function read()
    {
        // Create query
        $query = 'SELECT id, name, subcategory, vegetarian, portions, steps, hours, minutes, ingredients, created_on, approved, owner_id
         FROM ' . $this->table . '
        ORDER BY created_on DESC';

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        // Execute query
        $stmt->execute();

        return $stmt;
    }

	// Get single recipe
    public function read_single()
    {
        // Create query
        $query = 'SELECT id, name, subcategory, vegetarian, portions, steps, hours, minutes, ingredients, created_on, approved, owner_id
          FROM ' . $this->table . '
        WHERE
          id = ?';

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        // Bind ID
        $stmt->bindParam(1, $this->id);

        // Execute query
        $stmt->execute();

        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($row) {
            // Set properties
            $this->name = $row['name'];
            $this->subcategory = $row['subcategory'];
            $this->vegetarian = $row['vegetarian'];
            $this->portions = $row['portions'];
            $this->steps = $row['steps'];
            $this->hours = $row['hours'];
            $this->minutes = $row['minutes'];
            $this->ingredients = $row['ingredients'];
            $this->created_on = $row['created_on'];
            $this->approved = $row['approved'];
            $this->owner_id = $row['owner_id'];

            return true;
        }
        return false;
    }
	
    // Get recipes by name
    public function read_by_name()
    {
        // Create query
        $query = "SELECT id, name, subcategory, vegetarian, portions, steps, hours, minutes, ingredients, created_on, approved, owner_id
          FROM " . $this->table . "
        WHERE
          name LIKE CONCAT('%', ?, '%')";

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        // Bind name
        $stmt->bindParam(1, $this->name);

        // Execute query
        $stmt->execute();

        return $stmt;
    }

    // Get recipes
    public function read_by_vegetarian()
    {
        // Create query
        $query = "SELECT id, name, subcategory, vegetarian, portions, steps, hours, minutes, ingredients, created_on, approved, owner_id
         FROM " . $this->table . "
        WHERE vegetarian = ?";

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        // Bind vegetarian
        $stmt->bindParam(1, $this->vegetarian);

        // Execute query
        $stmt->execute();

        return $stmt;
    }

    // Get recipes
    public function read_by_subcategory()
    {
        // Create query
        $query = 'SELECT id, name, subcategory, vegetarian, portions, steps, hours, minutes, ingredients, created_on, approved, owner_id
         FROM ' . $this->table . '
        WHERE subcategory = ?';

        // Prepare statement
        $stmt = $this->conn->prepare($query);

        // Bind category
        $stmt->bindParam(1, $this->subcategory);

        // Execute query
        $stmt->execute();

        return $stmt;
    }

    // Delete recipe
    public function delete()
    {
        if ($this->read_single()) {
            // Create query
            $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id';

            // Prepare statement
            $stmt = $this->conn->prepare($query);

            // Clean data
            $this->id = htmlspecialchars(strip_tags($this->id));

            // Bind data
            $stmt->bindParam(':id', $this->id);

            // Execute query
            if ($stmt->execute()) {
                return true;
            }

            // Print error if something goes wrong
            printf("ГРЕШКА: %s.\n", $stmt->error);

            return false;
        }
    }
}
