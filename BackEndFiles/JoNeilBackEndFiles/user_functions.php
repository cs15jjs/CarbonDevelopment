<?php require_once "connect_sql.php"; ?>

<?php

function storeUser($username, $email, $password, $first_name, $last_init){
	global $con;

	$query = "INSERT INTO id5457279_zero_db . manual_users (username, email, password, first_name, last_initial) VALUES('$username', '$email', '$password', '$first_name', '$last_init')";
	
	$result = mysqli_query($con, $query);
	
	if ($result){
		$user = "SELECT * FROM manual_users WHERE email = '$email'";
		$res = mysqli_query($con, $user);
		
		while($user = mysqli_fetch_assoc($res)){
			return $user;
		}
	} else {
		return false;
	}
}

function getUserByEmailAndPassword($email, $password){
	global $con;
	
	$query = "SELECT * FROM manual_users WHERE email = '$email' AND password = '$password'";
	
	$user = mysqli_query($con, $query);
	
	if($user)
	{
		while ($res = mysqli_fetch_assoc($user))
		{
		return $res;
		}
	}else 
	{
		return false;
	}
}

function emailExists($email){
	global $con;
	
	$query = "SELECT email FROM manual_users WHERE email = '$email'";
	
	$result = mysqli_query($con, $query);
	
	if(mysqli_num_rows($result) > 0){
		return true;
	}else{
		return false;
	}
}

?>