<?php

 
$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st=$con->prepare("select price,qty from items inner join bill_detail on items.id=bill_detail.itemid where bill_detail.bill_no=?"); 
$st->bind_param("i",  $_GET["bill_no"]);           // i = integer
$st->execute();
$rs=$st->get_result();
$total=0;


while($row=$rs->fetch_assoc())
{
      $total = $total + ($row["price"] * $row["qty"]);
}


echo $total;



/*
*  http://localhost/OldBookSellingApp/get_total.php?bill_no=1
*/


