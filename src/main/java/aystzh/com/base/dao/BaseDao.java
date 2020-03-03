package aystzh.com.base.dao;

import java.util.List;
import java.util.Map;

/**
 * Dao层基本实现 
 * created by zhanghuan on 2019/4/4
 */
public interface BaseDao<T> {

    /**
     * 插入一条对象
     *
     * @param t
     * @return
     */
    int insertSelective(T t);


    int insert(T t);

    int updateByPrimaryKey(T t);

    /**
     * 更新一条对象
     *
     * @param t
     * @return updatenumber
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 删除一条对象
     *
     * @param id
     * @return deltenumber
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量删除多条对象，该方法有可能没实现
     *
     * @param id
     * @return
     */
    int batchDelete(Integer[] id);

    /**
     * 根据主键获取一条对象
     *
     * @param id
     * @return T
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 根据查询条件获取一条对象，该方法有可能没实现
     *
     * @param map
     * @return T
     */
    T queryOne(Map map);

    /**
     * 获取所有的对象
     *
     * @return list
     */
    List<T> getAll();

    /**
     * 获取所有对象，并按照分页显示，该方法有可能没实现
     *
     * @param map
     * @return list
     */
    List<T> allWithPaging(Map map);

    /**
     * 根据查询条件获取多个对象，该方法有可能没实现
     *
     * @param map
     * @return list
     */
    List<T> query(Map map);

    /**
     * 根据查询条件获取多个对象，并按照分页显示，该方法有可能没实现
     *
     * @param map
     * @return list<T>
     */
    List<T> queryWithPaging(Map map);

    /**
     * 行锁，该方法可能没有实现
     * @param id
     * @return
     */
    T findOneForUpdate(Integer id);
}
