<?php

$user_name = $_GET["u"];
$password = $_GET["p"];

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());

// Set a user name & password to MySQL table in the selected database
mysql_query("INSERT INTO user (name, password) VALUES ('$user_name', '$password')")
 or die(mysql_error());  

echo "Values has been set!";

?> 