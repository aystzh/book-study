package aystzh.com.base.impl;

import aystzh.com.base.service.BaseService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhanghuan
 * service默认实现
 * @Date: 2019/4/2 15:51
 */
@Service
@Transactional
public abstract class DefaultServiceImpl<T> extends SqlSessionDaoSupport implements BaseService<T> {

    protected String mapperLocation;

    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 保存对象
     *
     * @param t
     * @return t
     * @throws Exception
     */
    @Override
    public T save(T t) throws Exception {
        //FieldUtils.setFieldValue(t, SysConstants.INSERT);
        String m = null;
        mapperLocation = getMapper();
        int row = getSqlSession()
                .insert(mapperLocation + ".insertSelective", t);
        return t;
    };

    /**
     * 更新对象
     *
     * @param t
     * @return
     * @throws Exception
     */
    @Override
    public int modify(T t) throws Exception {
        //FieldUtils.setFieldValue(t, SysConstants.UPDATE);
        int back = 0;
        mapperLocation = getMapper();
        back = getSqlSession().update(
                mapperLocation + ".updateByPrimaryKeySelective", t);
        // }
        return back;
    };

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {

        mapperLocation = getMapper();

        return getSqlSession().delete(mapperLocation + ".deleteByPrimaryKey",
                id);

    };

    /**
     * 批量删除对象(SQL要自己写 id必须为delete)
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer[] id) {
        mapperLocation = getMapper();

        return getSqlSession().delete(mapperLocation + ".delete", id);
    };

    /**
     * 获取对象
     *
     * @param id
     * @return
     */
    @Override
    public T get(Integer id) {
        mapperLocation = getMapper();

        return getSqlSession().selectOne(mapperLocation + ".selectByPrimaryKey",
                id);

    };

    /**
     * 查询单个对象(SQL要自己写 id必须为getOne)
     * @param map
     * @return t
     */
    @Override
    public T findOne(Map<? extends Object, ? extends Object> map) {
        mapperLocation = getMapper();
        return getSqlSession().selectOne(mapperLocation + ".getOne", map);
    };

    /**
     * 列出全部对象并分页显示(SQL要自己写 id必须为getWithPaging 参数名为start，length)
     *
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<T> findWithPaging(int start, int length) {
        mapperLocation = getMapper();
        Map map = new HashMap();
        map.put("start", start);
        map.put("length", length);
        return getSqlSession().selectList(mapperLocation + ".getWithPaging",
                map);
    };

    /**
     * 查询对象(SQL要自己写 id必须为gets)
     *
     * @param map
     * @return
     */
    @Override
    public List<T> find(Map<? extends Object, ? extends Object> map) {
        mapperLocation = getMapper();
        return getSqlSession().selectList(mapperLocation + ".gets", map);
    };

    /**
     * 查询对象并分页显示(SQL要自己写 id必须为getWithPaging2 参数名为start，length)
     *
     * @param map
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<T> findWithPaging(Map<Object, Object> map, int start, int length) {
        mapperLocation = getMapper();

        map.put("start", start);
        map.put("length", length);

        return getSqlSession().selectList(mapperLocation + ".getWithPagingByCondition",
                map);
    };

    /**
     * 取所有对象(SQL要自己写 id必须为getAll)
     *
     * @return List<T>
     */
    @Override
    public List<T> findAll() {
        mapperLocation = getMapper();
        return getSqlSession().selectList(mapperLocation + ".getAll");
    }

    /**
     * 设置Mapper的namespace
     *
     * @return mapper
     */
    public abstract String getMapper();


}