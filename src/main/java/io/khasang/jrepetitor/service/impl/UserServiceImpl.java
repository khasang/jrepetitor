package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ProfileDao;
import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.dto.UserDTOInterface;
import io.khasang.jrepetitor.dto.impl.UserDTOImpl;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import io.khasang.jrepetitor.utils.CreationProfileStatus;
import io.khasang.jrepetitor.utils.CreationUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDTOImpl userDTO;

    @Autowired
    private ProfileDao profileDao;

    @Override
    public User addUser(User user) {
        return userDao.create(user);
    }

    @Override
    public List<UserDTOInterface> getAllUsers() {
        return userDTO.getUserDTOList(userDao.getList());
    }

    @Override
    public UserDTOInterface getUserById(long id) {
        return userDTO.getUserDTO(userDao.getById(id));
    }

    @Override
    public UserDTOInterface deleteUser(long id) {
        User user = userDao.getById(id);
        if (user == null) {
            return null;
        } else {
            return userDTO.getUserDTO(userDao.delete(user));
        }
    }

    @Override
    public UserDTOInterface getUserByLogin(String login) {
        return userDTO.getUserDTO(userDao.getUserByLogin(login));
    }

    @Override
    public UserDTOInterface updateUser(User user) {
        return userDTO.getUserDTO(userDao.updateUser(user));
    }

    @Override
    public CreationUserStatus createUser(User user) {
        CreationUserStatus creationUserStatus = new CreationUserStatus();
        creationUserStatus.setEmailExist(checkEmailExist(user.getProfile().getEmail()));
        creationUserStatus.setLoginExist(checkLogin(user.getLogin()));
        creationUserStatus.setPhoneExist(checkPhoneExist(user.getProfile().getPhoneNumber()));
        if (creationUserStatus.isOk()) {
            addUser(user);
            return creationUserStatus;
        }
        return creationUserStatus;
    }

    @Override
    public CreationProfileStatus updateProfile(String userName, Profile profile) {
        User user = userDao.getUserByLogin(userName);
        CreationProfileStatus creationProfileStatus = new CreationProfileStatus();
        creationProfileStatus.setEmailExist(checkEmailExist(profile.getEmail()));
        creationProfileStatus.setPhoneExist(checkPhoneExist(profile.getPhoneNumber()));
        if (creationProfileStatus.isOk()) {
            Profile updatedProfile = user.getProfile();
            updatedProfile.setName(profile.getName());
            updatedProfile.setSurname(profile.getSurname());
            updatedProfile.setMiddlename(profile.getMiddlename());
            updatedProfile.setPhoneNumber(profile.getPhoneNumber());
            updatedProfile.setEmail(profile.getEmail());
            userDao.updateUser(user);
            return creationProfileStatus;
        } else {
            return creationProfileStatus;
        }
    }

    @Override
    public Boolean checkPhoneExist(String phone) {
        Profile profile = profileDao.getProfileByPhone(phone);
        return !(profile == null);
    }

    @Override
    public Boolean checkEmailExist(String email) {
        Profile profile = profileDao.getProfileByEmail(email);
        return !(profile == null);
    }

    @Override
    public Boolean checkLogin(String login) {
        User user = userDao.getUserByLogin(login);
        return !(user == null);
    }
}
