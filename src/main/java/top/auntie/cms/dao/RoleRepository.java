package top.auntie.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.auntie.cms.pojo.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {}
