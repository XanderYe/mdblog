package com.xander.mdblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.Topic;
import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.exception.BusinessException;
import com.xander.mdblog.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
@Api(value="主题controller",tags={"主题操作接口"})
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
    @ApiOperation(value="删除主题",notes="需要登录，必填id")
    @PostMapping("delete")
    public ResultBean delTopic(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCodeEnum.PARAMETER_EMPTY);
        }
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
    @ApiOperation(value="获取所有主题",notes="不需要登录")
    @GetMapping("getAll")
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
    @ApiOperation(value="根据id获取主题",notes="需要登录，id必填")
    @GetMapping("getById")
    public ResultBean getTopicById(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCodeEnum.PARAMETER_EMPTY);
        }
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
    @ApiOperation(value="保存主题",notes="添加和修改都是这个接口")
    @PostMapping("save")
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
