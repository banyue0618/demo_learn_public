package com.example.demo.learn.annotation;

import com.example.demo.learn.designPattern.SingletonTread;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author banyue
 * date 2020-04-13
 */
public class UseAnnotation {

    @SimpleAnnotation
    private SingletonTread singletonTread;

    @SimpleAnnotation
    public void testAnnotation(){

    }

    public static void main(String[] args){
        annotationLogic();
    }

    private static void annotationLogic() {
        Class useAnnotationClass = UseAnnotation.class;
        for(Method method : useAnnotationClass.getMethods()) {
            SimpleAnnotation simpleAnnotation = method.getAnnotation(SimpleAnnotation.class);
            if(simpleAnnotation != null) {
                System.out.println(" Method Name : " + method.getName());
                System.out.println(" value : " + simpleAnnotation.value());
                System.out.println(" --------------------------- ");
            }
        }
        Field[] declaredFields = useAnnotationClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            SimpleAnnotation simpleAnnotation = declaredField.getAnnotation(SimpleAnnotation.class);
            if(simpleAnnotation != null) {
                System.out.println(" field Name : " + declaredField.getName());
                System.out.println(" value : " + simpleAnnotation.value());
                System.out.println(" --------------------------- ");
            }
        }

    }

}
