package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ProfileDao;
import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.dto.UserDTOInterface;
import io.khasang.jrepetitor.dto.impl.UserDTOImpl;
import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.UserService;
import io.khasang.jrepetitor.model.CreationProfileStatusResponseWrapper;
import io.khasang.jrepetitor.model.CreationUserStatusResponseWrapper;
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
    public CreationUserStatusResponseWrapper createUser(User user) {
        CreationUserStatusResponseWrapper creationUserStatusResponseWrapper = new CreationUserStatusResponseWrapper();
        creationUserStatusResponseWrapper.setEmailExist(checkEmailExist(user.getProfile().getEmail()));
        creationUserStatusResponseWrapper.setLoginExist(checkLogin(user.getLogin()));
        creationUserStatusResponseWrapper.setPhoneExist(checkPhoneExist(user.getProfile().getPhoneNumber()));
        if (creationUserStatusResponseWrapper.isOk()) {
            addUser(user);
            return creationUserStatusResponseWrapper;
        }
        return creationUserStatusResponseWrapper;
    }

    @Override
    public CreationProfileStatusResponseWrapper updateProfile(String userName, Profile profile) {
        User user = userDao.getUserByLogin(userName);
        CreationProfileStatusResponseWrapper creationProfileStatusResponseWrapper = new CreationProfileStatusResponseWrapper();
        creationProfileStatusResponseWrapper.setEmailExist(checkEmailExist(profile.getEmail()));
        creationProfileStatusResponseWrapper.setPhoneExist(checkPhoneExist(profile.getPhoneNumber()));
        if (creationProfileStatusResponseWrapper.isOk()) {
            Profile updatedProfile = user.getProfile();
            updatedProfile.setName(profile.getName());
            updatedProfile.setSurname(profile.getSurname());
            updatedProfile.setMiddlename(profile.getMiddlename());
            updatedProfile.setPhoneNumber(profile.getPhoneNumber());
            updatedProfile.setEmail(profile.getEmail());
            userDao.updateUser(user);
            return creationProfileStatusResponseWrapper;
        } else {
            return creationProfileStatusResponseWrapper;
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
