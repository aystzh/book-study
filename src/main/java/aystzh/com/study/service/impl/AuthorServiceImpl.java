package aystzh.com.study.service.impl;

import aystzh.com.study.dao.AuthorMapper;
import aystzh.com.study.entity.Author;
import aystzh.com.study.service.AuthorService;
import aystzh.com.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zhanghuan on 2020/03/10.
 */
@Service
@Transactional
public class AuthorServiceImpl extends AbstractService<Author> implements AuthorService {
    @Resource
    private AuthorMapper authorMapper;

}
