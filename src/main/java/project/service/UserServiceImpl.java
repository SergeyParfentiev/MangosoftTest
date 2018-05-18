package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dto.UserDTO;
import project.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO user = userRepository.getUserByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(user.getLogin(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

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
