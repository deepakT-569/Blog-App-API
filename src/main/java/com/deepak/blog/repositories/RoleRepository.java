package com.deepak.blog.repositories;

import com.deepak.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
   Role findByName(String name);
}
