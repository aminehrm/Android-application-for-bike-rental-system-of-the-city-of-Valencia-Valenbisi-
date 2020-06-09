<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("eroor in the connection");

$user_name=$_POST['username'];
$email=$_POST['email'];
$query="SELECT * FROM users WHERE username='$user_name' or email='$email'";
$result=mysqli_query($conn, $query);
if(mysqli_num_rows($result)>0){

  
   echo "exist";
  

}else{
echo "notexist";
}?>