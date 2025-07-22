package com.magu.bank.services;

import com.magu.bank.models.User;
import com.magu.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Obter todos
    public List<User> getUsers() {
        return repository.findAll();
    }

    // Regra de negócio: armazenar senhas criptografadas, verificar CPF
    public User validateUser(User user) throws Exception {
        // Valida CPF
        String cpf = user.getCpf().replaceAll("[^0-9]", "");
        if(cpf.length() == 11) {
            String lastTwo = cpf.substring(cpf.length() - 2 );
            for(int i = 0; i < cpf.length(); i++) {
                int c = (int)(cpf.charAt(cpf.length() - i));
                
            }
        } else {
            throw new Exception("CPF lenght must equal 11.");
        }



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
            e.printStackTrace();
            return null;
        }

        user.setPassword(newPassword);
        return repository.save(user);
    }


}
