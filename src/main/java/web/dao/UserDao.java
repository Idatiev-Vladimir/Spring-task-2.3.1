package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> allUser();

    User showUser(long id);

    void addUser(User user);

    void updateUser(long id, User user);

    void removeUserById(long id);
}
