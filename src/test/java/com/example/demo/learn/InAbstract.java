package com.example.demo.learn;

/**
 * @author banyue
 * date 2020-04-01
 */
public class InAbstract extends TestAbstract{

    @Override
    public void play(){
        System.out.println("InAbstract play");
    }

    public static void eat(TestAbstract testAbstract){
        testAbstract.play();
//        InAbstract inAbstract = testAbst;
    }

    public static void main (String[] args){

        eat(new InAbstract());
    }



}
