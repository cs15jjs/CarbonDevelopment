<?php

require "connect_sql.php";
  
$query = "update id5457279_zero_db . movie_details set movie_title = 'New Test' where movie_id = 2244";


if (mysqli_query($con, $query) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

mysqli_close($con);

?>