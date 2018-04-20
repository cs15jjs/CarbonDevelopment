<?php

require_once 'user_functions.php';

//json response array
$response = array("error" => FALSE);

if (isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['first_name']) && isset($_POST['last_init'])){

	//receiving the POST parameters
	$username = $_POST['username'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$first_name = $_POST['first_name'];
	$last_init = $_POST['last_init'];
	
		//check if user already exists with same email
		if(emailExists($email)){
		//email already exists
		$response["error"] = TRUE;
		$response["error_msg"] = "Account already exists with " . $email;
		echo json_encode($response);
		}else{
		//create new user
		$user = storeUser($username, $email, $password, $first_name, $last_init);
			if ($user){
			//user stored successfully
			$response["error"] = FALSE;
			//$response["user"]["iser_id"] = $user["user_id"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["username"] = $user["username"];
            $response["user"]["password"] = $user["password"];
            $response["user"]["first_name"] = $user["first_name"];
            $response["user"]["last_init"] = $user["last_initial"];
            echo json_encode($response);
			}else{
			//user failed to store
			$response["error"] = TRUE;
			$response["error_msg"] = "User failed to store";
			echo json_encode($response);
			}
	}
}else{
	$response["error"] = TRUE;
	$response["error_msg"] = "Required parameters missing!";
	echo json_encode($response);
}
?>