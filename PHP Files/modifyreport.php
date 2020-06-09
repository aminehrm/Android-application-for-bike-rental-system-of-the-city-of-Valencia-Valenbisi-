<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("connection error");

if($_SERVER['REQUEST_METHOD']=='POST'){
$station_id=$_POST['station_id'];
$name=$_POST['name'];
$description=$_POST['description'];
$status=$_POST['status'];
$type=$_POST['type'];
$query="update reports set name='$name',description='$description',status='$status',type='$type' where _id=$station_id ";
if(mysqli_query($conn, $query)){
echo("Update successfully");
}else{
echo("error in update");
}
}else{
echo("error in request method");
}
?>