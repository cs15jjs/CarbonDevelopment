<?php

require "connect_sql.php";

$query = "delete from id5457279_zero_db . movie_details where movie_id = '2244'";


if (mysqli_query($con, $query) === TRUE) {
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " . $con->error;
}

mysqli_close($con);

?>