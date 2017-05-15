<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");

	//get the details
	$tag = $_POST['tag'];

	//retrieve from mysql table
	$sql1 = "SELECT * FROM survey WHERE id IN (SELECT id FROM comment WHERE tag='".$tag."');";
//	$sql1 = "SELECT * FROM survey";

	$result = mysqli_query($con, $sql1);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result))
	{
		array_push($response,array("title"=>$row[0],"text"=>$row[1], "id"=>$row[2], "likes"=>$row[3], "dislikes"=>$row[4], "user"=>$row[5]));
	}
	
	echo json_encode(array("server_response"=>$response));
	
	mysqli_close($con);
	
 ?>