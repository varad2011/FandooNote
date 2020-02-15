package com.bridgelabz.fundooNotes.dao;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundooNotes.model.UserDao;
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}