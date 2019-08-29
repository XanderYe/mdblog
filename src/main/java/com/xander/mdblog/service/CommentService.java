package com.xander.mdblog.service;

import com.xander.mdblog.base.BaseService;
import com.xander.mdblog.entity.Comment;

import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
public interface CommentService extends BaseService<Comment> {
    /**
     * 插入一条评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 逻辑删除一条评论
     * @param comment
     */
    void deleteComment(Comment comment);
}
