package org.jasig.cas.thrift.server.custom;

import java.util.List;

import org.apache.thrift.TException;
import org.custom.cas.mybatis.mapper.UserMapper;
import org.custom.commons.utils.LoggerUtils;
import org.custom.commons.utils.ModelUtils;
import org.jasig.cas.thrift.server.TimedOutException;
import org.jasig.cas.thrift.server.User;
import org.jasig.cas.thrift.server.UserServiceThrift;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersServiceImplThrift implements UserServiceThrift.Iface {
    @Autowired
    public UserMapper userMapper;

    /**
     * 根据 用户名查询Users对象信息, 转换为User对象信息并返回
     *
     * @param userName
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public User getUserByUserName(String userName) throws TimedOutException, TException {
        org.custom.cas.mybatis.model.User users = new org.custom.cas.mybatis.model.User();
        users.setUsername(userName);

        List<org.custom.cas.mybatis.model.User> usersList = userMapper.queryUsersByParams(users);

       /* List<Users> usersList = usersMapper.queryUsersByParams(users);*/

        if (usersList != null && !usersList.isEmpty()) {
            users = usersList.get(0);
        }

        User user = new User();

        if (users != null) {
            user = ModelUtils.UsersToUser(users);
        }
        return user;
    }

    /**
     * 检查用户名是否已经存在<br>
     * 实现仅使用了userName参数
     *
     * @param userName
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkUserName(String userName, int id) throws TimedOutException, TException {
        boolean result = false;
        org.custom.cas.mybatis.model.User users = new org.custom.cas.mybatis.model.User();
        users.setUsername(userName);
        List<org.custom.cas.mybatis.model.User> usersList = userMapper.queryUsersByParams(users);
        if (usersList != null && !usersList.isEmpty()) {
            result = true;
        }
        return result;
    }

    /**
     * @param mobile
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkMobile(String mobile, int id) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [checkMobile] method, don't do everything.Only return [%s]. Parameters [mobile:%s,id:%s]",
                "false", mobile, id);
        return false;
    }

    /**
     * @param email
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkEmail(String email, int id) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [checkEmail] method, don't do everything.Only return [%s]. Parameters [email:%s,id:%s]", "false",
                email, id);
        return false;
    }

    /**
     * @param idCard
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkIdCard(String idCard, int id) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [checkIdCard] method, don't do everything.Only return [%s]. Parameters [idCard:%s,id:%s]",
                "false", idCard, id);
        return false;
    }

    /**
     * @param jobId
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkJobId(String jobId, int id) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [checkJobId] method, don't do everything.Only return [%s]. Parameters [jobId:%s,id:%s]", "false",
                jobId, id);
        return false;
    }

    /**
     * 新增一个用户
     *
     * @param user
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean addNewUser(User user) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [addNewUser] method, don't do everything.Only return [%s]. Parameters [user:%s]", false, user);
        return false;
    }

    /**
     * @param userList
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean addNewUserList(List<User> userList) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [addNewUserList] method, don't do everything.Only return [%s]. Parameters [userList:%s]", false,
                userList);
        return false;
    }

    /**
     * @param user
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean updateUser(User user) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [updateUser] method, don't do everything.Only return [%s]. Parameters [user:%s]", false, user);
        return false;
    }

    /**
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public List<User> lookOnlineUserList() throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(), "Enter [lookOnlineUserList] method, don't do everything.Only return [%s].No parameters.", "null");
        return null;
    }

    /**
     * @param password
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkPassword(String password, int id) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(),
                "Enter [checkPassword] method, don't do everything.Only return [%s].Parameters [password:%s,id:%s].", "false", password, id);
        return false;
    }

    /**
     * @param nickName
     * @param id
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public boolean checkNickName(String nickName, int id) throws TimedOutException, TException {
        // TODO Auto-generated method stub
        LoggerUtils.fmtDebug(this.getClass(),
                "Enter [checkNickName] method, don't do everything.Only return [%s].Parameters [nickName:%s,id:%s].", "false", nickName, id);
        return false;
    }

    /**
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public int findUserCount() throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(),
                "Enter [findUserCount] method, don't do everything.Only return [%s].No parameters.", "0");
        return 0;
    }

    /**
     * @param startIndex
     * @param rowCount
     * @return
     * @throws TimedOutException
     * @throws TException
     * @Created by ZYJ on 2017年1月23日 下午2:01:29
     * @Version 1.0
     */
    @Override
    public List<User> findUserList(int startIndex, int rowCount) throws TimedOutException, TException {
        LoggerUtils.fmtDebug(this.getClass(),
                "Enter [findUserList] method, don't do everything.Only return [%s].Parameters [startIndex:%s,rowCount:%s].", "null", startIndex, rowCount);
        return null;
    }

}
