package com.xander.mdblog.service.impl;

import com.xander.mdblog.base.BaseServiceImpl;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.Article;
import com.xander.mdblog.mapper.ArticleMapper;
import com.xander.mdblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hbxz
 * @date 2018/12/19 0019
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article findArticleById(Long id) {
        return articleMapper.findArticleById(id);
    }
}
