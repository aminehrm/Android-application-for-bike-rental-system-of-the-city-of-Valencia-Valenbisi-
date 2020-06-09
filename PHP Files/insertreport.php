<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("eroor in the connection");

 

if($_SERVER['REQUEST_METHOD']=='POST'){
$station_id=$_POST['station_id'];
$name=$_POST['name'];
$description=$_POST['description'];
$status=$_POST['status'];
$type=$_POST['type'];
$query="INSERT INTO reports (station_id,name,description,status,type) VALUES ($station_id, '$name', '$description', '$status', '$type')";
if(mysqli_query($conn, $query)){
echo("insert successfully");
}else{
echo("error in insertion");
}
}else{
echo("error in request method");
}
?>