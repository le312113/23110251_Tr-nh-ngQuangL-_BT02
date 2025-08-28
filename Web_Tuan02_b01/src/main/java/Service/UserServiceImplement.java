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
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        // kiểm tra trùng
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email) || userDao.checkExistPhone(phone)) {
            return false;
        }
        // tạo user & lưu
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        // roleid = 5 theo slide (tuỳ bạn quy ước)
        User u = new User(email, username, fullname, password, null, 5, phone, now);
        userDao.insert(u);
        return true;
    }
    @Override public boolean checkExistEmail(String email)       { return userDao.checkExistEmail(email); }
    @Override public boolean checkExistUsername(String username) { return userDao.checkExistUsername(username); }
    @Override public boolean checkExistPhone(String phone)       { return userDao.checkExistPhone(phone); }
}
