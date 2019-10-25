package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
        @Autowired
        private UserRepository userRepository;

        public void registerUser (User user){
            userRepository.save(user);
        }
        public User findUser (String username, String password){
            return userRepository.findByUsernameAndPassword(username, password);
        }
}
