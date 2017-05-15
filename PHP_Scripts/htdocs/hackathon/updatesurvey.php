<?php

	$con = mysqli_connect("localhost","root","") or die('Could not connect: ' . mysqli_error());
    mysqli_select_db($con, "hackathon_survey");

	//read the json file contents
//	$jsondata = file_get_contents('id.json');

	//convert json object to php associative array
//	$data = json_decode($jsondata, true);

	//get the details
//	$id = $data['id'];

	$id=1;
//	$likes = $data['likes'];
//	$dislikes = $data['dislikes'];
	$likes=1;
	$dislikes=1;
	
	//insert into mysql table
    $sql = "UPDATE survey SET likes='".$likes."', dislikes='".$dislikes."'
			WHERE id='".$id."';";
	
	echo $sql;
	if(!mysqli_query($con,$sql))
    {
        die('Error : ' . mysqli_error());
    }
	else
	{
		echo "success";
	}
	mysqli_close($con);
	
?>