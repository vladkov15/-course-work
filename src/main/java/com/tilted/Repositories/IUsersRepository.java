package com.tilted.Repositories;

import com.tilted.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepository extends JpaRepository<User, Integer> {
}
