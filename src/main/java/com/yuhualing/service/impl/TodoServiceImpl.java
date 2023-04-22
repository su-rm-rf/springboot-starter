package com.yuhualing.service.impl;

import com.yuhualing.dao.TodoMapper;
import com.yuhualing.model.Todo;
import com.yuhualing.model.TodoExample;
import com.yuhualing.service.TodoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2023/4/21 0021.
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public long countByExample(TodoExample example) {
        return todoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TodoExample example) {
        return todoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return todoMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int deleteAll() {
        return todoMapper.deleteAll();
    }

    @Override
    public int insert(Todo record) {
        return todoMapper.insert(record);
    }

    @Transactional
    @Override
    public int insertSelective(Todo record) {
        return todoMapper.insertSelective(record);
    }

    @Override
    public List<Todo> selectByExample(TodoExample example) {
        return todoMapper.selectByExample(example);
    }

    @Override
    public Todo selectByPrimaryKey(Integer id) {
        return todoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example) {
        return todoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example) {
        return todoMapper.updateByExample(record, example);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(Todo record) {
        return todoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Todo record) {
        return todoMapper.updateByPrimaryKey(record);
    }
}
