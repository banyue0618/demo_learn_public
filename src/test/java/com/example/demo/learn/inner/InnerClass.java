package com.example.demo.learn.inner;

public class InnerClass {

    private String name = "miss";

    private int age = 8;

    private static String type= "inner";

    public static void main(String[] args) {
        InnerClazz2.inner2();
    }




    public class InnerClazz1{
        public void inner1(){
            System.out.println();
            System.out.println(age);
        }
    }

    public static class InnerClazz2{
        public static void inner2(){
            System.out.println(type);
        }
    }
}

class InnerClassTest{
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
//
        InnerClass.InnerClazz1 innerClazz1 = innerClass.new InnerClazz1();
        innerClazz1.inner1();

        InnerClass.InnerClazz2 clazz2 = new InnerClass.InnerClazz2();
        clazz2.inner2();

    }
}
