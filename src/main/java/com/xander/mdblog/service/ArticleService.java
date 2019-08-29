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
     * 通过文章编号对文章进行逻辑删除
     * @param article
     */
    void deleteArticleByAid(Article article);
}
