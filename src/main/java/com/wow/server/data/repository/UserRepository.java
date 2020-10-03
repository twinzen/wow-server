package com.wow.server.data.repository;

import com.wow.server.data.model.User;
import com.wow.server.data.projection.UserMinimalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select new com.wow.server.data.projection.UserMinimalProjection(u.userId, u.displayName) from User u")
    List<UserMinimalProjection> finAllMinimal();

}
