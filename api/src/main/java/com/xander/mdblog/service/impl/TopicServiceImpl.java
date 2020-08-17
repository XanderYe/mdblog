package com.xander.mdblog.service.impl;

import com.xander.mdblog.base.BaseServiceImpl;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.Topic;
import com.xander.mdblog.mapper.TopicMapper;
import com.xander.mdblog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author hbxz
 * @date 2018-12-19
 */
@Service
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public void deleteTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setDelFlag(Constants.IS_DELETED);
        topicMapper.updateByPrimaryKeySelective(topic);
    }
}
