<?php

$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("select * from users where mobile=? and password=?"); 
$st_check->bind_param("ss",  $_GET["mobile"], $_GET["password"]);
$st_check->execute();   
$rs=$st_check->get_result();

if($rs->num_rows==0)        // if mobile no is already not present
{   echo "0";   }
 else  
{   echo "1";   }



/*
 *   http://localhost/OldBookSellingApp/login.php?mobile=8604815500&password=123456
 */



