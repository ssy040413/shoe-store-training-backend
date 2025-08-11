package com.xys.mapper;

import com.xys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户 (为了安全，不查询密码字段)
     */
    List<User> findAll();

    /**
     * 根据ID查询单个用户 (同样不查询密码)
     */
    User findById(Long id);

    /**
     * 使用动态SQL进行多条件分页查询 (已移除时间筛选)
     * @param keyword 搜索关键词 (对应 username, nickname, email)
     * @param gender  性别
     * @param status  状态
     * @return 符合条件的用户列表
     */
    List<User> findByConditions(@Param("keyword") String keyword,
                                @Param("gender") Integer gender,
                                @Param("status") Integer status);

    /**
     * 新增用户
     * 注意：这里假设密码已经是加密过的。实际项目中，加密应在Service层完成。
     */
    int insert(User user);

    /**
     * 更新用户信息
     * 动态SQL会更好，但这里为了简单，我们全字段更新。
     * 通常不在此处更新密码，密码更新应有单独接口。
     */
    int update(User user);

    /**
     * 根据ID删除用户
     */
    int deleteById(Long id);
}