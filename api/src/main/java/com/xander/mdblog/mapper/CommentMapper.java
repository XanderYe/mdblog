package com.xander.mdblog.mapper;

import com.xander.mdblog.base.BaseMapper;
import com.xander.mdblog.entity.Comment;

import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/19 0019
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据文章id获取评论
     * @param articleId
     * @return java.util.List<com.xander.mdblog.entity.Comment>
     * @author yezhendong
     * @date 2019/9/3
     */
    List<Comment> findCommentsByArticleId(Long articleId);

}
