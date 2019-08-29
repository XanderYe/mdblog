package com.xander.mdblog.service;

import com.xander.mdblog.base.BaseService;
import com.xander.mdblog.entity.Topic;

import java.util.List;

/**
 *
 * @author hbxz
 * @date 2018-12-19
 */
public interface TopicService extends BaseService<Topic> {

    /**
     * 逻辑删除一条话题
     * @param topic
     */
    void deleteTopic(Topic topic);
}
