package com.example.demo.common.support;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListKit {

    private static final Integer MAX_SEND = 1000;


    /**
     * 深拷贝列表，返回一个新的列表
     * 注意：调用此方法的前提是list中的元素都是可序列化对象，也就是实现了 Serializable接口
     * @param src
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteout);
            out.writeObject(src);
            ByteArrayInputStream bytein = new ByteArrayInputStream(byteout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bytein);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param list
     * @return
     */
    public static <T> List<List <T>> splitList(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        // List<List<T>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())).collect(Collectors.toList());
        List<List<T>> result = new ArrayList <>();
        int limit = countStep(list.size());
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            result.add(list.stream().skip(i * MAX_SEND).limit(MAX_SEND).collect(Collectors.toList()));
        });
        return result;
    }


    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_SEND - 1) / MAX_SEND;
    }

}
