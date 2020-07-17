package com.xk.myblogs.service.nosqldao;

import com.xk.myblogs.client.entity.nosql.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB  JPA操作
 * @author: tian
 * @date: 2020/7/13 15:38
 */
//标志为dao层
@Repository
public interface UserRepository extends MongoRepository<User, Long>{

    /**
     * 符合JPA规范命名方法， 则不需要实现也可以使用
     * 根据用户名模糊查询
     * @param userName 用户名称
     * @return
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 不符合jpa规范 自定义查询方法 ?0代表第一个参数  ?2代表第二个参数
     * @param id
     * @param userName
     * @return
     */
    @Query("{'id': ?0, 'userName': ?1}")
    User findByMyMethod(Long id, String userName);

    /**
     * 自定义查询
     * @param id
     * @param userName
     * @return
     */
    User findUserByIdOrUserName(Long id, String userName);
}
