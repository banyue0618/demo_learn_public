package com.example.demo.learn.jvm;

/**
 * @author banyue
 * date 2020-05-04
 */
public class ReferenceCount {

    public static void main(String[] args){

        ReferenceCount referenceCount = new ReferenceCount();


        ReferenceCount referenceCount1 = new ReferenceCount();

//        referenceCount = referenceCount1;
//
//        referenceCount1 = referenceCount;

//        referenceCount = null;
//
//        referenceCount1 =null;

        System.gc();


    }

}
