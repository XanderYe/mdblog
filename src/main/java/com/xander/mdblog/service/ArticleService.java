package com.xander.mdblog.service;

import com.github.pagehelper.PageInfo;
import com.xander.mdblog.base.BaseService;
import com.xander.mdblog.entity.Article;

/**
 * @author hbxz
 * @date 2018/12/19 0019
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 获取文章
     * @param id
     * @return com.xander.mdblog.entity.Article
     * @author yezhendong
     * @date 2019/9/3
     */
    Article findArticleById(Long id);
}
