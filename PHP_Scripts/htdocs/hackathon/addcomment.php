<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");
	
	//read the json file contents
//	$jsondata = file_get_contents('comment.json');

	//convert json object to php associative array
//	$data = json_decode($jsondata, true);

	//get the details
	
    $id = $_POST['id'];
	$text = $_POST['commnt'];
	$user = $_POST['user'];
	$tag = $_POST['tag'];
	$rating = $_POST['vote'];
	$like = $_POST['like'];
	$visibility = $_POST['priv'];
	
	/*
    $id = 10;
	$text = "this is sample comment text";
	$user = "adk";
	$tag = "git";
	$rating = 4.5;
	$like = 1;
	$visibility = 1;
	*/
	//insert into mysql table
    $sql = "INSERT INTO comment(id, text, user, tag, rating, liked, visibility)
			VALUES('$id', '$text', '$user', '$tag', '$rating', '$like', '$visibility');";

	if(!mysqli_query($con,$sql))
    {
        die('Error : ' . mysql_error());
    }

	mysqli_close($con);
?>