package com.magu.bank.services;

import com.magu.bank.models.User;
import com.magu.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Obter todos
    public List<User> getUsers() {
        return repository.findAll();
    }

    // Obter por ID
    public Optional<User> getUser(Integer id) {
        return repository.findById(id);
    }

    // Apagar usuário
    public User deleteUser(Integer id) {
        Optional<User> user = getUser(id);
        if(user.isPresent()) {
            User userModel = user.get();
            repository.delete(userModel);
            return userModel;
        }else {
            throw new RuntimeException("User not found");
        }
    }

    // Regra de negócio: armazenar senhas criptografadas
    public User encryptPassword(User user) {
        // Criptografa senha
        String password = user.getPassword();
        String newPassword;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for(byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            newPassword = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("An error ocurred  while trying to encrypt the password.");
        }

        user.setPassword(newPassword);
        return repository.save(user);
    }


}
