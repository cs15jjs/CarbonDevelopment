<?php

require "connect_sql.php";

$query = "select * from movie_details;";
$result = mysqli_query($con,$query);

$response = array();

while($row = mysqli_fetch_array($result))
{
	array_push($response,array('movie_id'=>$row[0],'movie_title'=>$row[1],'overview'=>$row[2],'release_date'=>$row[3],'image'=>$row[4]));
}

mysqli_close($con);

echo json_encode (array($response));



?>