package service;

import constants.Constant;
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

    String emailid = null;
    String username = null;


    public Boolean checkPassword(String name, String userPassword) throws Exception {
        LoginDao loginDao = new LoginDao();


        try {
            CompletableFuture<Boolean> completableFuture
                    = CompletableFuture.supplyAsync(() -> loginDao.checkUserNameOrEmailId(name, Constant.EMAIL_ID))
                    .thenCombine(CompletableFuture.supplyAsync(
                            () -> loginDao.checkUserNameOrEmailId(name, Constant.USERNAME)), (s1, s2) -> {
                        try {
                            if (s1 != null && s1.getSalt() != null && !s1.getSalt().isEmpty()) {
                                emailid = s1.getEmailId();
                                return authenticate(userPassword, Base64.getDecoder().decode(s1.getEncryptedPassword()), Base64.getDecoder().decode(s1.getSalt()));
                            } else {
                                username = s2.getUsername();
                                return authenticate(userPassword, Base64.getDecoder().decode(s2.getEncryptedPassword()), Base64.getDecoder().decode(s2.getSalt()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return false;
                    });
            if (completableFuture.get()) {
                int updatedRows = 0;
                if (username != null) {
                    updatedRows = loginDao.updateLastLoginTime(username, Constant.USERNAME);
                } else {
                    updatedRows = loginDao.updateLastLoginTime(emailid, Constant.EMAIL_ID);
                }
                if (updatedRows > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
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


