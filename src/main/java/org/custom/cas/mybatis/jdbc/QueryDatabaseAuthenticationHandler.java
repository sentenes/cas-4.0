package org.custom.cas.mybatis.jdbc;

import org.custom.cas.mybatis.model.User;
import org.custom.commons.utils.EncryptUtils;
import org.custom.commons.utils.LoggerUtils;
import org.custom.commons.utils.ModelUtils;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.excption.EmptyPasswordException;
import org.jasig.cas.excption.EmptyUserNameException;
import org.jasig.cas.excption.FailedPasswordException;
import org.springframework.jdbc.core.RowMapper;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("all")
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {
    /**
     * 
     * @Created by ZYJ on 2017年1月23日 下午3:56:38
     * @param transformedCredential
     * @return
     * @throws GeneralSecurityException
     * @throws PreventedException
     * @Version 1.0
     */
    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        String username = credential.getUsername();
        String password = credential.getPassword();
        password = EncryptUtils.getMD5(username+"#"+password);

        LoggerUtils.fmtDebug(this.getClass(),"MD5加密后的字符串：%s",password);

        HandlerResult hr = null;
        try {
            if (username == null) {
                throw new EmptyUserNameException("Username is null");
            }
            if (password == null) {
                throw new EmptyPasswordException("Password is null");
            }

            User users = getJdbcTemplate().queryForObject("select * from user where username=?", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setCreate_time(rs.getDate("create_time"));
                    user.setUsername(rs.getString("username"));
                    user.setId(rs.getInt("id"));
                    user.setLast_online_time(rs.getDate("last_online_time"));
                    user.setNickname(rs.getString("nickname"));
                    user.setPassword(rs.getString("password"));
                    user.setAge(rs.getInt("age"));
                    user.setGender(rs.getString("gender"));
                    user.setPhone(rs.getString("phone"));
                    user.setStatus(rs.getString("status"));
                    return user;
                }

            }, username);

            if (users != null && users.getStatus().equals("0")) {
                throw new AccountLockedException("Account be locked");
            }

            if (users == null) {
                // 登录错误
                throw new AccountNotFoundException("Login failure. Account [" + username + "] not fount!");
            }

            if (!password.equals(users.getPassword())) {
                throw new FailedPasswordException("Login failure. Password error.");
            }
            SimplePrincipal sp = new SimplePrincipal(username, ModelUtils.modelToMap(users));
            hr = createHandlerResult(credential, sp, null);
        } catch (Exception e) {
            LoggerUtils.fmtError(this.getClass(), e, "Authentication error.Account [%s] authentication failure.", username);
            throw new FailedLoginException("Login failed. Account [" + username + "].");
        }
        return hr;
    }
}
