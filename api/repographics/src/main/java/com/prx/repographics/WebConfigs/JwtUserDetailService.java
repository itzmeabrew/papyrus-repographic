package com.prx.repographics.WebConfigs;

import com.prx.repographics.Model.UserInfo;
import com.prx.repographics.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String regNo) throws UsernameNotFoundException
    {
        UserInfo auth = userRepo.getByRegNo(regNo);
        //System.out.println(auth.getName());

        if (auth == null)
        {
            System.out.println("User not found with regNo: " + regNo);
            throw new UsernameNotFoundException("User not found with regNo: " + regNo);
        }
        else
        {
            //auth.setLastActive(new Date());
            System.out.println("User found with regNo: " + regNo);
            userRepo.setLoggedIn(auth.getId(),new Date());
            return new User(auth.getRegNo(), auth.getPassword(), new ArrayList<>());
        }
    }

    public UserInfo saveUser(String regNo, String password)
    {
        //System.out.println(user.isActive());
        UserInfo user = new UserInfo();
        user.setRegNo(regNo);
        user.setPassword(bcryptEncoder.encode(password));
        user.setLoggedIn(new Date());

        UserInfo newUser= userRepo.save(user);
        return  newUser;
    }

//    public void disableUser(Boolean status,int id)
//    {
//        userRepo.setActive(status,id);
//    }
}
