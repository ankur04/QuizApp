package service;

import dao.UpdateUserInfoDao;
import helper.DateHelper;
import helper.GenderHelper;
import model.User;

import java.io.FileInputStream;

public class UpdateUserInfoService {
    public boolean update(String username, String email, String password, String phoneNumber, String gender, String dob) {
        User user = new User(username, email, password, phoneNumber, GenderHelper.getGender(gender), DateHelper.getDate(dob));
        return new UpdateUserInfoDao().update(user);
    }

    public boolean updateImage(String imagePath, String username){
        return new UpdateUserInfoDao().updateImage(imagePath, username);
    }
}
