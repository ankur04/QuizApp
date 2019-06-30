package service;

import dao.LoginDao;
import model.Login;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class LoginService {

    public Boolean checkPassword(String name, String userPassword) throws Exception {
        LoginDao loginDao = new LoginDao();

        try {
            CompletableFuture<Boolean> completableFuture
                    = CompletableFuture.supplyAsync(() -> loginDao.checkUserNameOrEmailId(name, "email_id"))
                    .thenCombine(CompletableFuture.supplyAsync(
                            () -> loginDao.checkUserNameOrEmailId(name, "username")), (s1, s2) -> {
                        try {
                            if (s1!= null && s1.getSalt()!= null && !s1.getSalt().isEmpty()) {
                                return authenticate(userPassword, Base64.getDecoder().decode(s1.getEncryptedPassword()), Base64.getDecoder().decode(s1.getSalt()));
                            } else {
                                return authenticate(userPassword, Base64.getDecoder().decode(s2.getEncryptedPassword()), Base64.getDecoder().decode(s2.getSalt()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return false;
                    });
            return completableFuture.get();
        } catch (Exception ex) {
            throw new Exception("User not found");
        }


    }

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to encrypt the original password
        byte[] encryptedAttemptedPassword = EncryptionSaltAndPassword.getEncryptedPassword(attemptedPassword, salt);

        // Authentication succeeds if encrypted password that the user entered is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }




}


