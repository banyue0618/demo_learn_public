package com.example.demo.serializable;

import java.io.*;
import java.nio.ByteBuffer;

public class SerializableTest {

    public static void main(String[] args) {
        readTest();
//        serializableTest();
//        externalizeTest();
    }

    public static void serializableTest(){
        Human human = new Human();
        human.setName("xi");

        // 把对象写到文件中
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\XI.txt"))){
            oos.writeObject(human);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readTest(){

        // 从文件中读出对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\XI.txt")))){
            Human human1 = (Human) ois.readObject();
            System.out.println(human1.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void externalizeTest(){
        Student human = new Student();
        human.setName("xi");
        human.setAge(1);

        // 把对象写到文件中
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\XI.txt"))){
            oos.writeObject(human);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 从文件中读出对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\XI.txt")))){
            Student human1 = (Student) ois.readObject();
            System.out.println(human1.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
class Human implements Serializable{

//    private static final long serialVersionUID = 1L;

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 转为二进制字节码
    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.name.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putLong(this.id);
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}

class Student implements Externalizable{
    private transient String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
    }
}
