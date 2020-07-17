package com.xk.myblogs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xk.myblogs.client.entity.nosql.User;
import com.xk.myblogs.service.NosqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tian
 * @date: 2020/7/12 18:58
 */
@Service
public class NoSqlServiceImpl implements NosqlService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void savaMdList(User user) {
        //使用集合名称
        mongoTemplate.save(user, "user");
        //如果文档采用类名
    }

    @Override
    public User getUserById(Long id) {
        return mongoTemplate.findById(id, User.class);
        //如果只需要获取第一个，也可以采用如下查询方法
//        Criteria criteriaId = Criteria.where("id").is(id);
//        Query queryId = Query.query(criteriaId);
//        return mongoTemplate.findOne(queryId, User.class);
    }

    @Override
    public DeleteResult deleteUserById(Long id) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query queryId = new Query(criteriaId);
        return mongoTemplate.remove(queryId, User.class);
    }

    @Override
    public List<User> findUser(String userName, String note, Integer skip, Integer limit) {
        //将用户名称和备注设置为模糊查询准则
        Criteria criteria = Criteria.where("userName").regex(userName)
                .and("note").regex(note);
        //构建查询条件，并设置分页跳过前skip个，至多返回limit个
        Query query = Query.query(criteria).limit(limit).skip(skip);
        //执行
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }

    @Override
    public UpdateResult updateUser(Integer id, String userName, String note) {
        //确定要更新的数据id
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        //定义更新对象, 后续可变化的字符串代表排除在外的属性
        Update update = Update.update("userName", userName);
        update.set("note", note);
        //更新第一个文档
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        //更新多个对象
//        UpdateResult result1 = mongoTemplate.updateMulti(query, update, User.class);
        return result;
    }
}
