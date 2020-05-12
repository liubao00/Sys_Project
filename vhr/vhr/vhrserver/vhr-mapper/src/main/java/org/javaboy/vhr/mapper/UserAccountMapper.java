package org.javaboy.vhr.mapper;


import org.javaboy.vhr.model.UserAccount;

public interface UserAccountMapper {

    void addUserAccount(UserAccount userAccount);

    UserAccount getUserAccountByName(String name);
}
