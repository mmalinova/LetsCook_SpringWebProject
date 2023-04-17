<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

include_once 'phpmailer/src/PHPMailer.php';
include_once 'phpmailer/src/Exception.php';
include_once 'phpmailer/src/SMTP.php';

// Headers
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Get raw posted data
$data = json_decode(file_get_contents("php://input"));

if (isset($_POST)) {
	$email_subject = "Let's cook Feedback";
    $name = $data->name;
    $email = $data->email;
    $mess = $data->mess;
	$email_exp = '/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/';
    if (!isset($email) || !preg_match($email_exp, $email)) {
        echo json_encode(array('message' => 'email'));
    }
    else if (!isset($name) || empty($name)) {
        echo json_encode(array('message' => 'name'));
    }
    else if (!isset($mess) || empty($mess)) {
        echo json_encode(array('message' => 'message'));
    } else {
		$email_body = "Име: " . $name . "\n";
        $email_body .= "Имейл: " . $email . "\n";
        $email_body .= "Съобщение: " . $mess . "\n";
	
		$mail = new PHPMailer(true);
		$mail->isSMTP();
		$mail->CharSet = "UTF-8";
		$mail->Host = 'smtp.abv.bg';
		$mail->SMTPAuth = true;
		$mail->Username = #username here;
		$mail->Password = #password here;
		$mail->SMTPSecure = 'ssl';
		$mail->Port = 465;
	
		$mail->setFrom(...); #the email sender(username)
		$mail->addAddress(...); #the email recipient
	
		$mail->isHTML(true);
		$mail->Subject = $email_subject;
		$mail->Body = $email_body;
		$mail->send();
	}
}