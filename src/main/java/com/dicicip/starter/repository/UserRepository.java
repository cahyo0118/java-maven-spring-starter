package com.dicicip.starter.repository;

import com.dicicip.starter.model.User;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    @Query("SELECT u FROM User u where")
//    Iterable<User> findAll(Pageable pageable);
}
