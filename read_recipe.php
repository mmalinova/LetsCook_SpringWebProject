<?php
// Headers
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: GET');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once '../../config/Database.php';
include_once '../../models/Recipe.php';

// Instantiate DB & connect
$database = new Database();
$db = $database->connect();

// Instantiate recipe object
$recipe = new Recipe($db);

// Recipe read query
$result = $recipe->read();

// Get row count
$num = $result->rowCount();

// Check if any recipes
if ($num > 0) {
    // Recipe array
    $recipes_arr = array();

    while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $recipe_item = array(
            'id' => $id,
            'name' => $name,
            'subcategory' => $subcategory,
            'vegetarian' => $vegetarian,
            'portions' => $portions,
            'steps' => $steps,
            'hours' => $hours,
            'minutes' => $minutes,
            'ingredients' => $ingredients,
            'created_on' => $created_on,
            'approved' => $approved,
            'owner_id' => $owner_id
        );

        array_push($recipes_arr, $recipe_item);
    }

    // Turn to JSON & output
    echo json_encode($recipes_arr);
} else {
    echo json_encode(
        array('message' => "Не са намерени рецепти.")
    );
}
