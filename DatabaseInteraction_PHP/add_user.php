<?php
 
$con=new mysqli("localhost", "root", "", "OldBookSellingApp");
$st_check=$con->prepare("select * from users where mobile=?"); 
$st_check->bind_param("s",  $_GET["mobile"]);
$st_check->execute();   
$rs=$st_check->get_result();

if($rs->num_rows==0)        // if mobile no is already not present
{   $st=$con->prepare("insert into users values(?,?,?,?)");
     $st->bind_param("ssss", $_GET["mobile"], $_GET["password"], $_GET["name"], $_GET["address"]);
     $st->execute();
     
     echo "1";   }
 else  
{   echo "0";   }
 


/* 
 *  http://localhost/OldBookSellingApp/add_user.php?mobile=8604815500&password=123456&name=Shubham&address=Lucknow 
 * 
 *  http://192.168.1.100/OldBookSellingApp/add_user.php?mobile=8604815500&password=123456&name=Shubham&address=Lucknow 
 */
 
 
 


