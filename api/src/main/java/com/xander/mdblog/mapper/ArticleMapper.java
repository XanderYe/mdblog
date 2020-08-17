package com.xander.mdblog.mapper;

import com.xander.mdblog.base.BaseMapper;
import com.xander.mdblog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/19 0019
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取文章
     * @param id
     * @return com.xander.mdblog.entity.Article
     * @author yezhendong
     * @date 2019/9/3
     */
    Article findArticleById(Long id);
}
