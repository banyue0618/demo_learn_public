package com.example.demo.common.netty;

import java.io.Serializable;

/**
 *  服务器可能返回空的处理
 *
 * @author banyue
 * date 2020-08-04
 */
public class NullWritable implements Serializable {

    private static final long serialVersionUID = -8191640400484155111L;
    private static NullWritable instance = new NullWritable();

    private NullWritable() {
    }

    public static NullWritable nullWritable() {
        return instance;
    }


}
