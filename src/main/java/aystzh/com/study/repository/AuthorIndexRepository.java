package aystzh.com.study.repository;

import aystzh.com.study.entity.index.AuthorIndexEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by zhanghuan on 2020/2/19.
 */
@Component
public interface AuthorIndexRepository extends ElasticsearchRepository<AuthorIndexEntity, Integer> {
}
