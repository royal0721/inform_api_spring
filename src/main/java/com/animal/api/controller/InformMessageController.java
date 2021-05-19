package com.animal.api.controller;
import com.animal.api.model.MessageModel;
import com.animal.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class InformMessageController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/inform")
    @SendTo("/type/messages")
    public String send(MessageModel message) throws Exception {
        return MessageService.formatMessage(message);
    }
}

