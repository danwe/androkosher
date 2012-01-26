<?php

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());

// Create a MySQL table in the selected database
mysql_query("CREATE TABLE example(
id INT NOT NULL AUTO_INCREMENT, 
PRIMARY KEY(id),
 name VARCHAR(30), 
 age INT)")
 or die(mysql_error());  

echo "Table Created!";

?> 