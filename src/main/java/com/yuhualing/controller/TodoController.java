package com.yuhualing.controller;

import com.yuhualing.config.ConfigInfo;
import com.yuhualing.model.Todo;
import com.yuhualing.model.TodoExample;
import com.yuhualing.service.TodoService;
import com.yuhualing.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2023/4/21 0021.
 */
@RestController
@RequestMapping
public class TodoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ConfigInfo configInfo;

    @Autowired
    private TodoService todoService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/")
    public String hello() {
        return "Hello SpringBoot " + serverPort + configInfo.getUsername();
    }

    @GetMapping("/todolist")
    public @ResponseBody Object list(@RequestParam String completed) {
        //设置 redisTemplate 对象 key 的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        List<Todo> todos = (List<Todo>) redisTemplate.opsForValue().get("todos");

        if (null == todos) {
            TodoExample example = new TodoExample();
            if (!"".equals(completed)) {
                example.createCriteria()
                        .andCompletedEqualTo(Integer.parseInt(completed));
            }
            todos = todoService.selectByExample(example);

            redisTemplate.opsForValue().set("todos", todos, 15, TimeUnit.SECONDS);
        }

        ResponseData rc = new ResponseData();
        rc.setCode("ok");
        rc.setData(todos);
        rc.setMsg("查询列表成功");
        return rc;
    }

    @GetMapping("/tododetail/{id}")
    public @ResponseBody Object defail(@PathVariable("id") int id) {
        Todo todo = todoService.selectByPrimaryKey(id);
        ResponseData responseData = new ResponseData();
        responseData.setCode("ok");
        responseData.setData(todo);
        responseData.setMsg("查询详情成功");
        return responseData;
    }

    @PostMapping("/todoadd")
    public @ResponseBody Object add(@RequestBody Map<String, String> map){
        String text = map.get("text");
        Integer completed = Integer.parseInt(map.get("completed"));
        Todo todo = new Todo();
        todo.setText(text);
        todo.setCompleted(completed);
        int num = todoService.insertSelective(todo);
        ResponseData responseData = new ResponseData();
        responseData.setData(num);
        responseData.setMsg("添加成功");
        return responseData;
    }

    @PostMapping("/todoupdate")
    public @ResponseBody Object update(@RequestBody Map<String, String> map) {
        Integer id = Integer.parseInt(map.get("id"));
        String text = map.get("text");
        Integer completed = Integer.parseInt(map.get("completed"));
        Todo todo = todoService.selectByPrimaryKey(id);
        if (!"".equals(text)) {
            todo.setText(text);
        }
        if (null != completed) {
            todo.setCompleted(completed);
        }
        int num = todoService.updateByPrimaryKeySelective(todo);
        ResponseData responseData = new ResponseData();
        responseData.setData(num);
        responseData.setMsg("修改成功");
        return responseData;
    }

    @PostMapping("/tododelete")
    public @ResponseBody Object delete(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        int num = todoService.deleteByPrimaryKey(id);
        ResponseData responseData = new ResponseData();
        responseData.setData(num);
        responseData.setMsg("删除成功");
        return responseData;
    }

    @PostMapping("/todoclear")
    public @ResponseBody Object clear() {
        int num = todoService.deleteAll();
        ResponseData responseData = new ResponseData();
        responseData.setData(num);
        responseData.setMsg("清空成功");
        return responseData;
    }
}
