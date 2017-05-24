package main.java.org.jks.java8;

import java.util.function.Function;

/**
 * Created by Winasia on 2017/5/23.
 */
public class Learn {
    interface MathOperation {
        int operation(int a, int b);
    }
    public static void main(String args[]){
        MathOperation add = (int a, int b) -> a+b;
        System.out.println(add.operation(1,2));
    }
}
