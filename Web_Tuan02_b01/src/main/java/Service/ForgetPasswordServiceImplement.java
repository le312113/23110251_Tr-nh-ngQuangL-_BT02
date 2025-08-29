package Service;

import dao.ForgetPasswordDao;
import dao.ForgetPasswordDaoImplement;

public class ForgetPasswordServiceImplement implements ForgetPasswordService {
    ForgetPasswordDao forgetPasswordDao = new ForgetPasswordDaoImplement();
    public void UpdatePassword(String Email,String newPassword) {
        forgetPasswordDao.update(Email,newPassword);
    }
}
