package com.almond.mallthirdparty;

import com.aliyun.oss.OSS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallThirdPartyApplicationTests {

    @Resource
    private OSS oss;

    @Test
    public void upload() throws FileNotFoundException {
        oss.putObject("almond-mall", "xixixi.jpg",  new FileInputStream("D:\\img\\lbt.jpg"));
        System.out.println(111);
    }

}
