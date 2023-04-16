<?php
// Headers
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Get raw posted data
$data = json_decode(file_get_contents("php://input"));

if (isset($_POST)) {
    $email_to = "mihaela__eli@abv.bg";
    $email_subject = "Let's cook Feedback";
    $name = $data->name;
    $email = $data->email;
    $mess = $data->mess;
    $email_exp = '/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/';
    if (!isset($email) || !preg_match($email_exp, $email)) {
        echo json_encode(array('message' => 'email'));
    }
    if (!isset($name) || empty($name)) {
        echo json_encode(array('message' => 'name'));
    }
    if (!isset($mess) || empty($mess)) {
        echo json_encode(array('message' => 'message'));
    } else {
        $email_body = "Data:\n\n";
        $email_body .= "Име: " . $name . "\n";
        $email_body .= "Имейл: " . $email . "\n";
        $email_body .= "Съобщение: " . $mess . "\n";

        ini_set('SMTP', 'mail.34sp.com');
        ini_set('smtp_port', 25);
        //ini_set('sendmail_from', 'malinova29@gmail.com');
        // create email headers
        $headers = 'From: ' . $email . "\r\n" .
            'Reply-To: ' . $email . "\r\n" .
            'X-Mailer: PHP/' . phpversion();
        if (mail($email_to, $email_subject, $email_body, $headers)) {
            echo json_encode(array('message' => 'thanks'));
        }
    }
}
