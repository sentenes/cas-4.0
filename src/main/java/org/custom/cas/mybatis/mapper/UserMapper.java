package org.custom.cas.mybatis.mapper;

import org.custom.cas.mybatis.model.User;
import java.util.List;

public interface UserMapper {
    /**
     * 根据Users对象中字段不为null也不为空的字段查询users信息
     *
     * @param users
     * @return List<Users> users对象集合
     * @throws Exception
     * @Created by ZYJ on 2017年1月23日 下午2:05:11
     * @Version 1.0
     */
    List<User> queryUsersByParams(User users);
}