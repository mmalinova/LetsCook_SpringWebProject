<?php
class Database
{
  // DB Params
  private $host = 'localhost';
  private $db_name = 'letscook';
  private $username = 'root';
  private $password = '';
  private $conn;

  // DB Connect
  public function connect()
  {
    $this->conn = null;

    try {
      $this->conn = new PDO('mysql:host=' . $this->host . ';dbname=' . $this->db_name, $this->username, $this->password);
      $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    } catch (PDOException $e) {
      echo 'Грешка при свързването към Базата данни: ' . $e->getMessage();
    }

    $this->conn->exec("set names utf8");
    return $this->conn;
  }
}
