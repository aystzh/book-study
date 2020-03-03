package aystzh.com.base.service;

import java.util.List;
import java.util.Map;

/**
 * created by zhanghuan
 *
 * @Date: 2019/4/2 15:49
 */
public interface BaseService<T> {
    /**
     * 保存对象
     * @param t
     * @return T
     * @throws Exception
     */
    T save(T t) throws Exception;

    /**
     * 更新对象
     * @param t
     * @return t
     * @throws Exception
     */
    int modify(T t) throws Exception;
    /**
     * 删除对象
     * @param id
     * @return
     */
    int delete(Integer id) throws Exception;

    /**
     * 批量删除对象
     * @param id
     * @return number
     */
    int delete(Integer[] id) throws Exception;

    /**
     * 获取对象
     * @param id
     * @return t
     */
    T get(Integer id);

    /**
     * 查询单个对象
     * @param map
     * @return t
     */
    T findOne(Map<? extends Object, ? extends Object> map) throws Exception;


    /**
     * 列出全部对象并分页显示
     * @param start
     * @param length
     * @return list
     * @throws Exception
     */
    List<T> findWithPaging(int start, int length) throws Exception;

    /**
     * 查询对象
     * @param map
     * @return list
     */
    List<T> find(Map<? extends Object, ? extends Object> map) throws Exception;

    /**
     * 查询对象并分页显示
     * @param map
     * @param start
     * @param length
     * @return list
     */
    List<T> findWithPaging(Map<Object, Object> map, int start, int length) throws Exception;

    /**
     * 查询所有数据
     * @return list<T>
     */
    public List<T> findAll();
}
