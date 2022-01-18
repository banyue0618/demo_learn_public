package com.example.demo.common.netty.client;

import com.example.demo.common.netty.MethodInvokeMeta;
import com.example.demo.common.netty.NullWritable;
import com.example.demo.common.netty.ObjectCodec;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author banyue
 * date 2020-08-13
 */
public class CustomChannelInitializerClient extends ChannelInitializer {

    private Logger logger = LoggerFactory.getLogger(CustomChannelInitializerClient.class);

    private MethodInvokeMeta methodInvokeMeta;

    private Object response;

    public CustomChannelInitializerClient(MethodInvokeMeta methodInvokeMeta) {
        if (!"toString".equals(methodInvokeMeta.getMethodName())) {
            logger.info("[CustomChannelInitializerClient] 调用方法名：{}，入参：{},参数类型：{}，返回值类型{}"
                    , methodInvokeMeta.getMethodName()
                    , methodInvokeMeta.getArgs()
                    , methodInvokeMeta.getParameterTypes()
                    , methodInvokeMeta.getReturnType());
        }
        this.methodInvokeMeta = methodInvokeMeta;
    }

    public Object getResponse() {
        if (response instanceof NullWritable) {
            return null;
        }
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 2, 0, 2));
        pipeline.addLast(new ObjectCodec());
        pipeline.addLast(new ClientChannelHandlerAdapter(methodInvokeMeta, this));
    }

}
