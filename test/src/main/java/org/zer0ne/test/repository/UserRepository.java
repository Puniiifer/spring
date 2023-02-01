package org.zer0ne.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zer0ne.test.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
