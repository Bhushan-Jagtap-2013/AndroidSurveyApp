<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");

	//read the json file contents
	$jsondata = file_get_contents('id.json');

	//convert json object to php associative array
	$data = json_decode($jsondata, true);

	//get the details
	$id = $data['id'];
//	$id=1;
	//retrieve from mysql table
	$sql = "SELECT * FROM survey WHERE id='".$id."';";
//	$sql = "SELECT * FROM survey;";
	
	$result = mysqli_query($con, $sql);
	$responsesurvey = array();
	$row_array = array();

	while($row = mysqli_fetch_array($result))
	{
		$row_array['title'] = $row['title'];
		$row_array['text'] = $row['text'];
		$row_array['id'] = $row['id'];
		$row_array['likes'] = $row['likes'];
		$row_array['dislikes'] = $row['dislikes'];
		$row_array['user'] = $row['user'];
		array_push($responsesurvey, $row_array);
	}
	echo json_encode($responsesurvey);
	
	$sql = "SELECT * FROM comment WHERE id='".$id."';";
	
	$result = mysqli_query($con, $sql);
	$responsecomments = array();
	$row_array = array();
	while($row = mysqli_fetch_array($result))
	{
		$row_array['id'] = $row['id'];
		$row_array['text'] = $row['text'];
		$row_array['user'] = $row['user'];
		array_push($responsecomments, $row_array);
	}
	echo json_encode($responsesurvey);
	
	mysqli_close($con);
	
?>