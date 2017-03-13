#!/usr/bin/env bash

CountDownTimer(){
    count=$1
    step=$2
    i=1
    while(( $i <= $count ))
    do
        echo $i
        #let "i++"
        ((i=i+step))
        sleep 1
    done
}

CountDownTimer 100 2