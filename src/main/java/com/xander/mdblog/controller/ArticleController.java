package com.xander.mdblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xander.mdblog.base.RequestContextHolder;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.entity.Article;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.exception.BusinessException;
import com.xander.mdblog.service.ArticleService;
import com.xander.mdblog.util.ArticleUtil;
import com.xander.mdblog.util.ShortUUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */

@Api(value="文章controller",tags={"文章操作接口"})
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Value("${upload.root}")
    private String uploadRoot;

    @Value("${article.img}")
    private String articleImgPath;

    /**
     * 添加文章
     * @param article
     * @param session
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="添加文章",notes="需要登录")
    @PostMapping("add")
    public ResultBean addArticle(Article article, HttpSession session) {
        User user = (User) session.getAttribute("user");
        article.setAuthorId(user.getId());
        article.setCreator(user.getNickname());
        articleService.save(article);
        return new ResultBean();
    }

    /**
     * 更新文章
     * @param article
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="更新文章",notes="需要登录")
    @PostMapping("update")
    public ResultBean updateArticle(Article article) {
        User user = RequestContextHolder.get();
        article.setAuthorId(user.getId());
        article.setUpdator(user.getNickname());
        article.setUpdateTime(new Date());
        articleService.updateSelective(article);
        return new ResultBean();
    }

    /**
     * 根据id获取文章
     * @param id
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="根据id获取文章",notes="不需要登录，id必填")
    @GetMapping("getById")
    public ResultBean getById(Long id) {
        if (id == null) {
            throw new BusinessException("id不能为空");
        }
        Article article = articleService.findArticleById(id);
        return new ResultBean<>(article);
    }

    /**
     * 获取最近几篇文章
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="获取最近的几篇文章",notes="不需要登录，参数不是必填")
    @GetMapping("getRecentArticles")
    public ResultBean getRecentArticles(Long topicId, Integer page) {
        // 默认第一页
        page = page == null ? 1 : page;
        int rows = 5;
        Article article = new Article();
        if(topicId != null && topicId != 0){
            article.setTopicId(topicId);
        }
        PageInfo<Article> articlePageInfo = articleService.queryPageListByWhereDesc(Article.class, article, page, rows);
        ArticleUtil.cutArticles(articlePageInfo);
        return new ResultBean<>(articlePageInfo);
    }

    /**
     * 上传图片
     * @param files
     * @param request
     * @return java.lang.String
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="上传图片",notes="需要登录")
    @PostMapping("upload")
    public String uploadImg(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        List imgList = new ArrayList<String>();
        File uploadDirectory = new File(uploadRoot + articleImgPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        //这里可以支持多文件上传
        if (files != null && files.length >= 1) {
            try {
                for (MultipartFile file : files) {
                    String originalFilename = file.getOriginalFilename();
                    //判断是否有文件且是否为图片文件
                    if (originalFilename != null && !"".equalsIgnoreCase(originalFilename.trim())) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                        String fileName = sdf.format(new Date()) + "_" + ShortUUIDUtil.getShortUUID() + "." + suffix;
                        file.transferTo(new File(uploadRoot + articleImgPath + File.separator + fileName));
                        imgList.add(articleImgPath + File.separator + fileName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("errno", 1);
            }
            result.put("errno", 0);
        }
        result.put("data", imgList);
        return result.toString();
    }
}
