package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.dto.UserDTO;
import project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDTO getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public List<UserDTO> getUserListByPageNumberAndCount(int pageNumber, int pageCount) {
        List<UserDTO> list = new ArrayList<>();
        PageRequest request = new PageRequest(pageNumber - 1, pageCount, Sort.Direction.ASC, "id");
        userRepository.findAll(request).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void deleteUserByLogin(String login) {
        userRepository.deleteUserByLogin(login);
    }

    @Override
    public UserDTO saveUser(UserDTO user) {
        return userRepository.save(user);
    }
}
