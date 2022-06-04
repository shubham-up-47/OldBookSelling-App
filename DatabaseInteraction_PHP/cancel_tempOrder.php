<?php


$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("delete from temp_order where mobile=?"); 
$st_check->bind_param("s",  $_GET["mobile"]);
$st_check->execute();



/* 
 *  http://localhost/OldBookSellingApp/cancel_tempOrder.php?mobile=0788030600
 */
