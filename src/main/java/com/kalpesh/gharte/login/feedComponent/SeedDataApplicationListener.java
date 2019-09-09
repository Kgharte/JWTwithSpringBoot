package com.kalpesh.gharte.login.feedComponent;

import com.kalpesh.gharte.login.model.User;
import com.kalpesh.gharte.login.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Data
@Primary
public class SeedDataApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    @Autowired
    public SeedDataApplicationListener(UserRepository userRepository){this.userRepository = userRepository;}
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        userRepository.save(new User("kalpeshgharte@yahoo.com","gharte"));
        userRepository.save(new User("ghartepratik@gmail.com","gharte"));
        userRepository.save(new User("ghartepr@gmail.com","gharte89"));
    }
}
