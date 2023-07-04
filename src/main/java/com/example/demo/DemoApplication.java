package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


        // right triangle
//        for (int x = 0; x < 5; x++) {
//            for (int c = 0; c <= x; c++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }

        //fibonacci
//        int z = 0;
//        int x = 1;
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Fn: ");
//        int param = sc.nextInt();
//        for (int c = 1; c < param; c++) {
//            int res = z + x;
//            System.out.print(res + ((c + 1) != param ? "," : ""));
//            z = x;
//            x = res;
//        }


    }

}
