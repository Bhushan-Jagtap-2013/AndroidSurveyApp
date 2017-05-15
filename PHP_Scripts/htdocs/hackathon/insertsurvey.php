<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");

	//read the json file contents
//	$jsondata = file_get_contents('survey.json');

    //convert json object to php associative array
//	$data = json_decode($jsondata, true);
	
	//get the details
	$title = $_POST["title"];
	$text = $_POST["text"];
	$user = $_POST["user"];

//	$title="sampletitle";
//	$text="sampletexT";
//	$user="adk";
	$likes=0;
	$dislikes=0;

	//insert into mysql table
    $sql = "INSERT INTO survey(title, text, likes, dislikes, user)
	VALUES('$title', '$text', '$likes', '$dislikes', '$user');";
	
	if(mysqli_query($con,$sql))
    {
		echo "Data insertion success";
    }
	else
	{
		echo "Data insertion error..".mysqli_error($con);
	}
	
	mysqli_close($con);
	
?>