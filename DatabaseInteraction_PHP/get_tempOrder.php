<?php
 
$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("select id,name,price,qty,mobile from temp_order inner join items on items.id=temp_order.itemid where mobile=?"); 
$st_check->bind_param("s",  $_GET["mobile"]);
$st_check->execute();
$rs=$st_check->get_result();
$arr=array();

while($row=$rs->fetch_assoc())
{
     array_push($arr, $row);
}

echo json_encode($arr);



/*
 *  http://localhost/OldBookSellingApp/get_tempOrder.php?mobile=8604815500
 */


