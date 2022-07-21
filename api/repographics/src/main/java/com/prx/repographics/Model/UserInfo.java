package com.prx.repographics.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String regNo;
    private String name;
    private String email;
    private String phNo;
    private String password;
    private Integer type;
    private Date loggedIn;

    public UserInfo()
    {
    }

    public UserInfo(String regNo, String name, String email, String phNo, String password, Integer type, Date loggedIn)
    {
        this.regNo = regNo;
        this.name = name;
        this.email = email;
        this.phNo = phNo;
        this.password = password;
        this.type = type;
        this.loggedIn = loggedIn;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRegNo()
    {
        return regNo;
    }

    public void setRegNo(String regNo)
    {
        this.regNo = regNo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhNo()
    {
        return phNo;
    }

    public void setPhNo(String phNo)
    {
        this.phNo = phNo;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Date getLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn(Date loggedIn)
    {
        this.loggedIn = loggedIn;
    }
}
