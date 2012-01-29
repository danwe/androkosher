<?php

$user = $_POST['u'];
$password = $_POST['p'];

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());

// Get $user & $password:
$result =mysql_query("SELECT * FROM user WHERE name = '$user' && password = '$password'") or die(mysql_error());

$row=mysql_fetch_assoc($result);
$output[]=$result;
echo $row['name'];
echo "<br/>";
echo $row['password'];
echo "<br/>";


?> 