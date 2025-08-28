package Service;

import dao.UserDao;
import dao.UserDaoImplement;
import model.User;

public class UserServiceImplement implements UserService{
    UserDao userDao = new UserDaoImplement();
    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        System.out.println("DEBUG SVC inputPass=" + password
                + " | user=" + (user==null? "null" : user.getUserName()
                + " | dbPass=" + user.getPassWord()));
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;// thất bại
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
}
