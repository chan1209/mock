package dao;

import dao.service.MockService;
import dao.vo.MockParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zejunweizj on 2017/3/22.
 */
@ContextConfiguration(locations={"/spring.xml"})
public class TestAll extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockService mockService;

    @Test
    public void doTest(){


        int acb = mockService.getCountByMockUrlPath("/fsdfsdf");



        System.out.println(acb);
    }

}
