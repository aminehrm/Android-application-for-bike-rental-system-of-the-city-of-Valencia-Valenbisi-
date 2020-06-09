<?php 

$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="valenbisi";
$con=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname) or die("eroor in the connection");

$station_id=$_GET['station_id'];

$sql="SELECT * FROM reports WHERE station_id=$station_id ";


$r = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($r)){
    array_push($result,array(
    	'_id'=>$row['_id'],
      	'station_id'=>$row['station_id'],
        'name'=>$row['name'],
        'description'=>$row['description'],
        'status'=>$row['status'],
        'type'=>$row['type']
     
    ));
}

echo json_encode(array('result'=>$result));

mysqli_close($con);

?>