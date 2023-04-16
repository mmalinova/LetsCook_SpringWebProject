<?php
// Headers
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once '../../config/Database.php';
include_once '../../models/Photo.php';

// Instantiate DB & connect
$database = new Database();
$db = $database->connect();

// Instantiate photo object
$photo = new Photo($db);

// Get ID
$photo->recipe_id = isset($_GET['recipe_id']) ? $_GET['recipe_id'] : die();

// Photo query
$result = $photo->read();

// Get row count
$num = $result->rowCount();

// Check if any photos
if ($num > 0) {
  // Photo array
  $photos_arr = array();

  while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
    extract($row);

    $photo_item = array(
      'id' => $id,
      'image_url' => $image_url,
      'recipe_id' => $recipe_id,
    );

    array_push($photos_arr, $photo_item);
  }

  // Turn to JSON & output
  echo json_encode($photos_arr);
} else {
  echo json_encode(
    array('message' => 'Не са намерени снимки')
  );
}
