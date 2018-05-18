package project.service;

import project.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO user);

    List<UserDTO> getUserListByPageNumberAndCount(int pageNumber, int pageCount);

    UserDTO getUserByLogin(String login);

    void deleteUserByLogin(String login);
}
