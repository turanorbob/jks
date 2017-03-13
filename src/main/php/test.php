<?php
/**
 * Created by IntelliJ IDEA.
 * User: liaojian
 * Date: 2017/3/10
 * Time: 16:51
 */
echo "hello world\n";
$x = 5;
$y = 6;
$z = $x + $y;
echo $z."\n";

function AA(){
    $a = "aaa";
    echo $a."\n";
}
AA();

$arr = array("A", "B", "C");
echo $arr[1]."\n";
var_dump($arr);

$x = 2.4e3;
var_dump($x);
$x = 8E-5;
var_dump($x);

Class Car
{
    var $color;
    function Car($color="green"){
        $this->color = $color;
    }
    function what_color(){
        return $this->color;
    }
}
?>