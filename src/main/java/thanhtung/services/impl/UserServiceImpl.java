package thanhtung.services.impl;

import java.sql.Date;

import thanhtung.daos.UserDao;
import thanhtung.daos.impl.UserDaoImpl;
import thanhtung.models.UserModel;
import thanhtung.services.UserServices;

public class UserServiceImpl implements UserServices {

    UserDao userDao = new UserDaoImpl();

    @Override
    public UserModel findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.findByUserName(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void insert(UserModel user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        if (checkExistUsername(username) || checkExistEmail(email) || checkExistPhone(phone)) {
            return false;
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setAvatar(null);
        user.setRoleid(3);
        user.setPhone(phone);
        user.setCreateddate(date);
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }
}