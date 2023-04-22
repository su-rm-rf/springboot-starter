package com.yuhualing.service;

import com.yuhualing.model.Todo;
import com.yuhualing.model.TodoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2023/4/21 0021.
 */
public interface TodoService {
    long countByExample(TodoExample example);

    int deleteByExample(TodoExample example);

    int deleteByPrimaryKey(Integer id);

    int deleteAll();

    int insert(Todo record);

    int insertSelective(Todo record);

    List<Todo> selectByExample(TodoExample example);

    Todo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example);

    int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example);

    int updateByPrimaryKeySelective(Todo record);

    int updateByPrimaryKey(Todo record);
}
