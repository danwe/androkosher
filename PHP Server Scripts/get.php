<?php

$databasehost = "androkosher.x10.mx:3306";
$databasename = "androk_main";
$databaseusername ="androk";
$databasepassword = "abc123";

$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());

// Get $input:
$sql=mysql_query("select * from Rest where Rest_NAME like $input[]");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();

echo "Data sent!";

?> 