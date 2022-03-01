package cn.jjx.coding.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EchoServiceImpl implements EchoService{
    @Override
    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + now +"] Hello"+message+", request from consumer: "
                + RpcContext.getContext().getRemoteAddress());
        return message;
    }
}
