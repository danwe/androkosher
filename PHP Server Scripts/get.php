<?php

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());

// Get $input:
$result =mysql_query("SELECT * FROM Resturants_Jerusalem") or die(mysql_error());
$row = mysql_fetch_array($result) or die(mysql_error());

while($row = mysql_fetch_array($result) or die(mysql_error())){
	$output[]=$result;
	echo $row['Name'];
	echo "<br/>";
}

//echo json_encode($output);

 
mysql_close();

?> 