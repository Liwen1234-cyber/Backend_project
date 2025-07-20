package cn.itheima.consultant.controller;

import cn.itheima.consultant.aiservice.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(value = "/chat",produces = "text/html;charset=UTF-8")
    public Flux<String> chat(@RequestParam("message") String message){
        Flux<String> result = consultantService.chat(message);
        return result;
    }

//    @Autowired
//    private OpenAiChatModel model;
//
//    @RequestMapping("/chat")
//    public String chat(@RequestParam("message") String message){//用户的问题
//        String result = model.chat(message);//调用业务层的chat方法
//        return result;
//
//    }

}
