package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.dto.UserDTO;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO getUserByLogin(String login);

    void deleteUserByLogin(String login);
}

