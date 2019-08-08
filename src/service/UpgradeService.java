package service;

import dao.UpgradeDao;

public class UpgradeService {

    public boolean upgrade(String userName) {
        return new UpgradeDao().upgrade(userName);
    }

}
