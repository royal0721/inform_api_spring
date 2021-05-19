package com.animal.api.service;

import com.animal.api.model.MessageModel;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageService(){}

    public static String formatMessage(MessageModel message){

        String formatted_message = "通報"+message.getAddress1()+message.getAnimal_gender()+message.getAnimal_type()+"一隻";
        return formatted_message;

    }

}
