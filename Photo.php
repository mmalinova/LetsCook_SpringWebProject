<?php
class Photo
{
  // DB Stuff
  private $conn;
  private $table = 'image';

  // Properties
  public $id;
  public $image_url;
  public $recipe_id;

  // Constructor with DB
  public function __construct($db)
  {
    $this->conn = $db;
  }

  // Get photos from recipe
  public function read()
  {
    // Create query
    $query = 'SELECT
        id, image_url, recipe_id
      FROM
        ' . $this->table . '
      WHERE
        recipe_id = ?';

    // Prepare statement
    $stmt = $this->conn->prepare($query);

    // Bind ID
    $stmt->bindParam(1, $this->recipe_id);

    // Execute query
    $stmt->execute();

    return $stmt;
  }

  // Get first photo from recipe
  public function read_single()
  {
    // Create query
    $query = 'SELECT id, image_url, recipe_id
      FROM
        ' . $this->table . '
      WHERE 
        id = ?';

    //Prepare statement
    $stmt = $this->conn->prepare($query);

    // Bind ID
    $stmt->bindParam(1, $this->id);

    // Execute query
    $stmt->execute();

    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($row) {
      // Set properties
      $this->id = $row['id'];
      $this->image_url = $row['image_url'];
      $this->recipe_id = $row['recipe_id'];

      return true;
    }
    return false;
  }
}
