package com.example.demo.learn.jvm;

import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;
import org.openjdk.jol.vm.VM;

/**
 * @ClassName MarkWordTest
 * @Description 对象的布局问题
 * 关闭指针压缩：-XX:-UseCompressedOops
 *
 * @Author: zhangsp
 * @date 2022/2/28 16:36
 * @Version 1.0
 */
public class MarkWordTest {

    /**
     * |-----------------------------------------------------------------------------------------------------------------|
     * |                                             Object Header(128bits)                                              |
     * |-----------------------------------------------------------------------------------------------------------------|
     * |                                   Mark Word(64bits)               |  Klass Word(64bits)    |      State         |
     * |-----------------------------------------------------------------------------------------------------------------|
     * | unused:25|identity_hashcode:31|unused:1|age:4|biase_lock:1|lock:2 | OOP to metadata object |      Nomal         |
     * |-----------------------------------------------------------------------------------------------------------------|
     * | thread:54|      epoch:2       |unused:1|age:4|biase_lock:1|lock:2 | OOP to metadata object |      Biased        |
     * |-----------------------------------------------------------------------------------------------------------------|
     * |                     ptr_to_lock_record:62                 |lock:2 | OOP to metadata object | Lightweight Locked |
     * |-----------------------------------------------------------------------------------------------------------------|
     * |                    ptr_to_heavyweight_monitor:62          |lock:2 | OOP to metadata object | Heavyweight Locked |
     * |-----------------------------------------------------------------------------------------------------------------|
     * |                                                           |lock:2 | OOP to metadata object |    Marked for GC   |
     * |-----------------------------------------------------------------------------------------------------------------|
     *
     *
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * |                                             Object Header(128bits)                                              |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * |                                   Mark Word(64bits)               |  Klass Word(64bits)    |      State         |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * | unused:25|identity_hashcode:31|unused:1|age:4|biase_lock:0|lock:01 | OOP to metadata object |      Nomal         |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * | thread:54|      epoch:2       |unused:1|age:4|biase_lock:1|lock:01 | OOP to metadata object |      Biased        |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * |                     ptr_to_lock_record:62                 |lock:00 | OOP to metadata object | Lightweight Locked |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * |                    ptr_to_heavyweight_monitor:62          |lock:10 | OOP to metadata object | Heavyweight Locked |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     *      * |                                                           |lock:11 | OOP to metadata object |    Marked for GC   |
     *      * |-----------------------------------------------------------------------------------------------------------------|
     * @param args
     */

    public static void main(String[] args) throws Exception{
//        System.out.println(VM.current().details());

        Thread.sleep(5000);

        MarkWordVo o = new MarkWordVo();
//        out.println("before hash");
        out.println(ClassLayout.parseInstance(o).toPrintable());

        //jvm计算HashCode
//        out.println("jvm----------" + Integer.toHexString(o.hashCode()));

//        out.println("after hash");
//        out.println(ClassLayout.parseInstance(o).toPrintable());

    }
}
