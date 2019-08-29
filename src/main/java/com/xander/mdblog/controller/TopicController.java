package com.xander.mdblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.Topic;
import com.xander.mdblog.service.TopicService;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    /**
     * 假删主题
     * @param id
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-10
     */
    @RequestMapping("delete")
    public ResultBean delTopic(Long id) {
        Topic topic = new Topic();
        topic.setId(id);
        topicService.deleteTopic(topic);
        return new ResultBean();
    }

    /**
     * 获取所有主题
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-10
     */
    @RequestMapping("getAll")
    public ResultBean getAllTopic() {
        Topic topic = new Topic();
        topic.setDelFlag(Constants.IS_NOT_DELETED);
        List<Topic> existTopicList = topicService.queryListByWhere(topic);
        return new ResultBean<>(existTopicList);
    }

    /**
     * 根据id获取主题
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-10
     */
    @RequestMapping("getById")
    public ResultBean getTopicById(Long id) {
        Topic topic = topicService.queryById(id);
        return new ResultBean<>(topic);
    }

    /**
     * 新增或修改主题
     * @param topic
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-10
     */
    @RequestMapping("save")
    public ResultBean saveTopic(Topic topic) {
        if (topic.getId() == null) {
            topic.setCreateTime(new Date());
            topicService.save(topic);
        } else {
            topic.setUpdateTime(new Date());
            topicService.updateSelective(topic);
        }
        return new ResultBean();
    }

}