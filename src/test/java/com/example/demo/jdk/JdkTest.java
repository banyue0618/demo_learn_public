package com.example.demo.jdk;

import java.util.*;

/**
 * @author banyue
 * date 2020-07-07
 */
public class JdkTest {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        System.out.println("------------------------------------");
        Collections.sort(names, Comparator.naturalOrder());

        names.forEach(System.out::println);

        DefaultTest defaultTest = new DefaultTest() {
            @Override
            public boolean checkData(int a) {
                return isNull(a);
            }
        };
        defaultTest.checkData(100);

        names.stream().sorted(Comparator.reverseOrder()).forEach(a->System.out.println(a));

        /**
         *     Collections.sort(employees, Comparator.comparing(
         *                     Employee::getName, (s1, s2) -> {
         *                         return s2.compareTo(s1);
         *                     }));
         */

    }

    /**
     * Match 匹配
     * Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是最终操作，并返回一个boolean类型的值。
     *
     *
     * List<String> stringCollection = new ArrayList<>();
     * stringCollection.add("ddd2");
     * stringCollection.add("aaa2");
     * stringCollection.add("bbb1");
     * stringCollection.add("aaa1");
     * stringCollection.add("bbb3");
     * stringCollection.add("ccc");
     * stringCollection.add("bbb2");
     * stringCollection.add("ddd1");
     *
     *
     * boolean anyStartsWithA =
     *     stringCollection
     *         .stream()
     *         .anyMatch((s) -> s.startsWith("a"));
     * System.out.println(anyStartsWithA);      // true
     *
     * boolean allStartsWithA =
     *     stringCollection
     *         .stream()
     *         .allMatch((s) -> s.startsWith("a"));
     *
     * System.out.println(allStartsWithA);      // false
     *
     * boolean noneStartsWithZ =
     *     stringCollection
     *         .stream()
     *         .noneMatch((s) -> s.startsWith("z"));
     *
     * System.out.println(noneStartsWithZ);      // true
     *
     */



}
