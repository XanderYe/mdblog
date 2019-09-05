package com.xander.mdblog.service.impl;

import com.xander.mdblog.base.BaseServiceImpl;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.Comment;
import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.mapper.CommentMapper;
import com.xander.mdblog.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.xander.mdblog.util.CheckUtil.check;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        check(StringUtils.isNotEmpty(comment.getNickname()), ErrorCodeEnum.PARAMETER_EMPTY,"comment={}",comment);
        check(comment.getNickname().length() < Constants.MAX_LENGTH, ErrorCodeEnum.NICKNAME_TOOLONG,"comment={}",comment);
        check(comment.getEmail().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"), ErrorCodeEnum.EMAIL_ERROR,"comment={}",comment);
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        comment.setUpdateTime(new Date());
        comment.setDelFlag(Constants.IS_DELETED);
        commentMapper.updateByPrimaryKeySelective(comment);
    }
}
