package com.xander.mdblog.controller;

import com.xander.mdblog.base.RequestContextHolder;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.entity.Comment;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @RequestMapping("add")
    public ResultBean addComment(Comment comment){
        User user = RequestContextHolder.get();
        if(user != null){
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            comment.setAuthorId(user.getId());
            comment.setCreator(user.getNickname());
        }else {
           comment.setCreator("游客："+comment.getNickname());
        }
        commentService.insertComment(comment);
        return new ResultBean<>();
    }
}
