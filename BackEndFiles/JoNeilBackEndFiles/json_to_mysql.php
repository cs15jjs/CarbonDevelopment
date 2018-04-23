<?php

require "connect_sql.php";

$url = "https://api.themoviedb.org/3/discover/movie?api_key=3df5f7f0fa2c63de5a5f0687a402d65f&language=en-US&region=GB&sort_by=release_date.desc&include_adult=false&include_video=false&release_date.lte=2018-09-01";

$json_data = file_get_contents($url);

$result_array = json_decode($json_data, true);
//print_r ($result_array);
//var_dump($result_array);

foreach($result_array['results'] as $row)
{
// 	if (is_array($ra))
// 	{
// 		foreach ($ra as $row)
// 		{
	$query = "INSERT INTO id5457279_zero_db . movie_details (movie_id, movie_title, overview, release_date, image) SELECT * FROM (SELECT '".$row['id']."', '".$row['title']."', '".$row['overview']."', '".$row['release_date']."', '".$row['poster_path']."') AS tmp WHERE NOT EXISTS (SELECT movie_id FROM movie_details WHERE movie_id = '".$row['id']."') LIMIT 1;";
	
	mysqli_query($con, $query);
		}
// 	}
// }

echo "Data Entered! Well Done!";

mysqli_close($con);

?>