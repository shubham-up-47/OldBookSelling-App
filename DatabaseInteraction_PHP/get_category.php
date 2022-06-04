<?php


$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("select distinct category from items"); 
$st_check->execute();
$rs=$st_check->get_result();
$arr=array();

while($row=$rs->fetch_assoc())
{
    array_push($arr, $row);
}

echo json_encode($arr);



/*
 *  http://localhost/OldBookSellingApp/get_category.php
 */
 
