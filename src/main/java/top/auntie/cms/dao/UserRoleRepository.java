package top.auntie.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.auntie.cms.pojo.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}
