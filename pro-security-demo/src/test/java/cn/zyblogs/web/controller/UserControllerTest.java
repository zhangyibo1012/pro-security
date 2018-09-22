package cn.zyblogs.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Title: UserController.java
 * @Package cn.zyblogs.web.controller
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        // mvc 测试环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username" , "jojo")
                .param("age" , "18")
                .param("ageTo" , "60")
                .param("xxx" , "yyy")

                // 分页参数 Pageable  查询第三页的数据 每页返回15条 年龄降序排列
//                .param("size" , "15")
//                .param("page" , "3")
//                .param("sort" , "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())

                // githun  jsonPath项目
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }
}
