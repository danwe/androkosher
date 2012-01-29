<?php

$city = $_POST['c'];
$searchName = $_POST['n'];
$searchStreet = $_POST['s'];

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
$srchName="%".$searchName."%";
$srchStreet="%".$searchStreet."%";

// Get $search:
$result =mysql_query("SELECT * FROM Resturants_Jerusalem WHERE Name LIKE '$srchName' && Street LIKE '$srchStreet'") or die(mysql_error());

while($row=mysql_fetch_assoc($result)){
	$output[]=$result;
	echo $row['Name'];
	echo "<br/>";
}

?> 