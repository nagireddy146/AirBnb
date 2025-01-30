package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
