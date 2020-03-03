package aystzh.com.study.mapper;

import aystzh.com.base.dao.BaseDao;
import aystzh.com.study.entity.db.AuthorEntity;

import java.util.List;

public interface AuthorEntityMapper extends BaseDao<AuthorEntity>{

    List<AuthorEntity> selectAll();
}