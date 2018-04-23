<?php

require "connect_sql.php";
  
$query = "insert into id5457279_zero_db . movie_details (movie_id, movie_title, overview, release_date, image) values ('2244', 'test title 2', 'test overview 2', '00/00/2011', 'jgkhagskhjd.jpg')";


if (mysqli_query($con, $query) === TRUE) {
    echo "Movie Data Entered! Well done !";
} else {
    echo "Error inserting record: " . $con->error;
}

mysqli_close($con);

?>