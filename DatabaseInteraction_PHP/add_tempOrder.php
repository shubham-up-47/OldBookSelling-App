<?php

$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("insert into temp_order values(?,?,?)"); 
$st_check->bind_param("sii",  $_GET["mobile"], $_GET["itemid"], $_GET["qty"]);
$st_check->execute();

  

/* 
 *  http://localhost/OldBookSellingApp/add_tempOrder.php?mobile=8604815500&itemid=6&qty=2
 */


