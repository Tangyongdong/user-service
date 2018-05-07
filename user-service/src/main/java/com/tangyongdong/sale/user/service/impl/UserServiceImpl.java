package com.tangyongdong.sale.user.service.impl;

import com.tangyongdong.sale.base.exception.BusinessException;
import com.tangyongdong.sale.user.config.BusinessErrorCode;
import com.tangyongdong.sale.user.domain.db.User;
import com.tangyongdong.sale.user.mapper.UserMapper;
import com.tangyongdong.sale.user.response.UserResponse;
import com.tangyongdong.sale.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tangyongdong
 * @create 2018-05-07 11:03
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse userLogin(String phone) {
        User user = userMapper.selectByPhone(phone);
        if(user == null){
            throw new BusinessException(BusinessErrorCode.USER_NOT_FOUND);
        }

        //

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .token(user.getToken())
                .name(user.getName())
                .phone(phone)
                .gender(user.getGender())
                .admin(user.getAdmin())
                .build();
        return null;
    }
}
