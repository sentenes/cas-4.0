package org.custom.commons.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.thrift.server.User;

public class ModelUtils {

    /**
     * 将org.jasig.cas.entity.Users对象转换为org.jasig.cas.thrift.server.User对象<br>
     * 
     * 如果后者null, 则构造一个 新的对象返回
     * 
     * @Created by ZYJ on 2017年1月23日 下午2:28:16
     * @param users 如果参数为null, 则抛出空指针异常
     * @param user
     * @return
     * @Version 1.0
     */
    public static User UsersToUser(org.custom.cas.mybatis.model.User users, User user) {
        if (users == null) {
            throw new NullPointerException("转换user对象失败, 源数据为 null");
        }
        if (user == null) {
            user = new User();
        }
        user.setId(users.getId());
        user.setNickName(users.getNickname());
        user.setUserName(users.getUsername());
        user.setState(Integer.parseInt(users.getStatus() == null ? "1" : users.getStatus()));
        user.setPassword(users.getPassword());
        user.setMobile(users.getPhone());
        return user;
    }

    /**
     * 将org.jasig.cas.entity.Users对象转换为org.jasig.cas.thrift.server.User对象<br>
     * 
     * @Created by ZYJ on 2017年1月23日 下午2:30:41
     * @param users 如果参数为null, 则抛出空指针异常
     * @return
     * @Version 1.0
     */
    public static User UsersToUser(org.custom.cas.mybatis.model.User users) {
        return UsersToUser(users, null);
    }

    public static Map<String, Object> modelToMap(Object object)
            throws IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldname = field.getName();
            PropertyDescriptor pd = new PropertyDescriptor(fieldname, clazz);
            Method method = pd.getReadMethod();
            Object value = method.invoke(object);
            resultMap.put(fieldname, value);
        }
        return resultMap;
    }

}
