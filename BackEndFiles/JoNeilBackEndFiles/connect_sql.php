<?php
$host = "localhost";
$user = "id5457279_manual_users";
$pass = "carbon2018";
$db = "id5457279_zero_db";

$con = mysqli_connect($host,$user,$pass,$db);

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
 
?>