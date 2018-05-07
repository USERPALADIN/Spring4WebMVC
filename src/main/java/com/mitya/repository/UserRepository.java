package com.mitya.repository;

import com.mitya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
//    void delete(long id);
//    void insert(User user);
//    void update(long id);
//    List<User> getall(long id);

}
