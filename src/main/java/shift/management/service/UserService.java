package shift.management.service;

import shift.management.entity.User;
import shift.management.model.LoginRequest;

import java.util.Date;
import java.util.List;

public interface UserService {
    public User createAccount(User user) throws Exception;

    public User updateAccount(User user) throws Exception;

    public List<User> searchByUsernameAndName(String keyword);

    List<User> getListAccount();

    User getTotalSalary(Date fromDate,Date toDate,String username);

    User checkLogin(LoginRequest loginRequest);

    User checkManagerLogin(LoginRequest loginRequest);
}
