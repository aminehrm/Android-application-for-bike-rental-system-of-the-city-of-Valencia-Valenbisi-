<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("eroor in the connection");
 

if($_SERVER['REQUEST_METHOD']=='POST'){
$station_id=$_POST['station_id'];

$query="delete from reports where _id=$station_id ";
if(mysqli_query($conn, $query)){
echo("Update successfully");
}else{
echo("error in update");
}
}else{
echo("error in request method");
}
?>