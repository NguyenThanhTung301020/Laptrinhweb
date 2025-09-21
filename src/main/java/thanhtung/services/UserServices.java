package thanhtung.services;

import thanhtung.models.UserModel;

public interface UserServices {

    UserModel login(String username, String password);

    UserModel findByUserName(String username);

    void insert(UserModel user);

    boolean register(String email, String password, String username, String fullname, String phone);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

}
