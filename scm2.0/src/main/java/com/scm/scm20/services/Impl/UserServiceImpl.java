package com.scm.scm20.services.Impl;


import com.scm.scm20.entities.User;
import com.scm.scm20.helper.AppConstants;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        String id=UUID.randomUUID().toString();
        user.setUserId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);

    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user1= userRepo.findById(user.getUserId()).orElseThrow(()->new RuntimeException("No user found"));
        modelMapper.map(user,user1);
        User save=userRepo.save(user1);
        return Optional.of(save);
    }

    @Override
    public void deleteUser(String id) {
        User user1= userRepo.findById(id).orElseThrow(()->new RuntimeException("No user found"));
        userRepo.delete(user1);
    }

    @Override
    public boolean isUserExist(String userId) {
       return userRepo.existsById(userId);
    }

    @Override
    public boolean isUserExistForEmail(String emailid, String id) {
        User user= userRepo.findByEmail(emailid).orElseThrow(()-> new UsernameNotFoundException("User not found with this email"));
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=userRepo.findAll();
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}
