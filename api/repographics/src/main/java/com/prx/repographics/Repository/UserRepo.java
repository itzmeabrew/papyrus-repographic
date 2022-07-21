package com.prx.repographics.Repository;

import com.prx.repographics.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Integer>
{
    UserInfo getByRegNoAndPassword(String userName, String password);
    UserInfo getByRegNo(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_info SET logged_in = ?2 WHERE  user_info.id = ?1", nativeQuery = true)
    void setLoggedIn(int id, Date date);
}
