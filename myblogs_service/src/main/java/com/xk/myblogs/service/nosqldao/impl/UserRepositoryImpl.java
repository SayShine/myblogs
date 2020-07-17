package com.xk.myblogs.service.nosqldao.impl;

import com.xk.myblogs.client.entity.nosql.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: tian
 * @date: 2020/7/13 19:10
 */
@Repository
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public User findUserByIdOrUserName(Long id, String userName){
        Criteria criteriaID = Criteria.where("id").is(id);
        Criteria criteriaName = Criteria.where("userName").is(userName);

        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaID, criteriaName);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, User.class);

    }
}
