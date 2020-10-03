package com.wow.server.user;

import com.wow.server.user.UserMinimalProjection;
import com.wow.server.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    @Query("select new com.wow.server.data.projection.UserMinimalProjection(u.userId, u.displayName) from UserEntity u")
    List<UserMinimalProjection> finAllMinimal();

}
