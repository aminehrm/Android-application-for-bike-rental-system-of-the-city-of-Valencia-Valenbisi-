<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);

$user_name=$_POST['username'];
$pass_word=$_POST['password'];
$query="SELECT * FROM users WHERE username='$user_name' and password='$pass_word'";
$result=mysqli_query($conn, $query);
if(mysqli_num_rows($result)>0){

   $row = mysqli_fetch_assoc($result);
   echo $row["username"].",".$row["account_type"].",".$row["email"];
  

}else{
echo("login failed");
}?>