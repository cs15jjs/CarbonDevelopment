<?php

if (isset($_POST['movie_id'])) {

	//receiving the POST parameters
	$movie_id = $_POST['movie_id'];
}else{
	$response["error"] = TRUE;
	$response["error_msg"] = "Required parameters missing!";
	echo json_encode($response);
}

$url = "https://api.themoviedb.org/3/movie/".$movie_id. "/videos?api_key=3df5f7f0fa2c63de5a5f0687a402d65f&language=en-US";

$json_data = file_get_contents($url);

$result_array = json_decode($json_data, true);

$final_response = $result_array['results'][0]['key'];

echo $final_response;

?>