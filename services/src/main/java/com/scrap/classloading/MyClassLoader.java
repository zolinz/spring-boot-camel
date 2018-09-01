package com.scrap.classloading;

import java.util.Scanner;

public class MyClassLoader {


    public static void main(String ... args)throws Exception{
        String path = "com.scrap.classloading";

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter class name : ");
        String classname = sc.nextLine();


        Class cls = Class.forName(path+ "." + classname);


        Object obj = cls.newInstance();

        ((Animal) obj).run();


        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class clsAnimal = classLoader.loadClass(path+ "." + classname);

    }
}
