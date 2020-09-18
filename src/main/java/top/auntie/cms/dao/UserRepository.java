package top.auntie.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.auntie.cms.pojo.User;


public interface UserRepository extends JpaRepository<User, Long> {}
