package service;

import dao.UserDetailsDao;
import main.Main;

public class UserDetailsService {

    public int fetchNoOfQuiz() {
        if (Main.user != null)
            return new UserDetailsDao().fetchNoOfQuiz(Main.user.getUserName());
        return 0;
    }

    public int fetchAvgScore() {
        if (Main.user != null)
            return new UserDetailsDao().fetchAvgScore(Main.user.getUserName());
        return 0;
    }
}
