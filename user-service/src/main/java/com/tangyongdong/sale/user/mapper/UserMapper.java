package com.tangyongdong.sale.user.mapper;

import com.tangyongdong.sale.user.domain.db.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户手机号查询用户信息
     *
     * @param phone
     * @return
     */
    User selectByPhone(String phone);

    /**
     * 根据userId查询用户信息
     *
     * @param userToken
     * @return
     */
    User selectByUserToken(String userToken);
}