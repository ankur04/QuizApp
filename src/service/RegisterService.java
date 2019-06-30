package service;

import dao.RegisterDao;
import helper.DateHelper;
import helper.GenderHelper;
import model.User;

public class RegisterService {
    public boolean register(String username, String email, String password, String phoneNumber, String gender, String dob) {
        User user = new User(username, email, password, phoneNumber, GenderHelper.getGender(gender), DateHelper.getDate(dob));
        return new RegisterDao().register(user);
    }
}
