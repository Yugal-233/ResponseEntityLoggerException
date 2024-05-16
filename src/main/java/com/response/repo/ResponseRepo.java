package com.response.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.response.model.User;

public interface ResponseRepo extends JpaRepository<User, Integer>{

}
