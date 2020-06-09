<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("eroor in the connection");
 

if($_SERVER['REQUEST_METHOD']=='POST'){
$username=$_POST['username'];
$email=$_POST['email'];
$password=$_POST['password'];
$account_type=$_POST['account_type'];

$query="INSERT INTO users (username,email,password,account_type) VALUES ('$username', '$email', '$password', '$account_type')";
if(mysqli_query($conn, $query)){
echo("insert successfully");
}else{
echo("error in insertion");
}
}else{
echo("error in request method");
}
?>