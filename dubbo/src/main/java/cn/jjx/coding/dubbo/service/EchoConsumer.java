package cn.jjx.coding.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class EchoConsumer {

    @Reference
    private EchoService echoService;

    public String echo(String name){
        return echoService.echo(name);
    }
}
