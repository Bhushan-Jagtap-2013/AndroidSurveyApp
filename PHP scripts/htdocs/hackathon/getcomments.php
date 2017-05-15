<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");

	//read the json file contents
//	$jsondata = file_get_contents('id.json');

	//convert json object to php associative array
//	$data = json_decode($jsondata, true);

	//get the details
	$id = $_POST['id'];
//	$id=10;

	//retrieve from mysql table
	$sql = "SELECT * FROM comment WHERE id='".$id."';";
	//$sql = "SELECT * FROM comment;";

	$result = mysqli_query($con, $sql);
	$response = array();
	
	while($row = mysqli_fetch_array($result))
	{
		array_push($response,array("id"=>$row[0],"text"=>$row[1], "user"=>$row[2], "tag"=>$row[3], "rating"=>$row[4], "like"=>$row[5], "visibility"=>$row[6]));
	}
	
	echo json_encode(array("server_response"=>$response));
	
	mysqli_close($con);
	
?>