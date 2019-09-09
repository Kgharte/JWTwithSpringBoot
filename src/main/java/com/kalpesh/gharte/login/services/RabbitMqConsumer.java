package com.kalpesh.gharte.login.services;

import com.kalpesh.gharte.login.model.User;
import com.kalpesh.gharte.login.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
    @Autowired
    UserRepository userRepository;
    @RabbitListener(queues="${jsa.rabbitmq.queue3}", containerFactory="jsaFactory")
    public void recievedMessage(User user) {

        System.out.println("Recieved Message: userId:" + user.getUserId() + ", password:" + user.getPassword());
        userRepository.save(user);

    }
}
