import com.alibaba.fastjson.JSON;
import com.rrc.Application;
import com.rrc.constant.RedisConstant;
import com.rrc.entity.MessageCollect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName Test_1
 * @Description TODO
 * @Author wang
 * @Date 2021/6/17 21:57
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test_1 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void set() throws InterruptedException {
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
        redisTemplate.convertAndSend(RedisConstant.TOPIC_COLLECT,
                JSON.toJSONString(new MessageCollect("3", "4", "wangql", "nidaye", "9", Arrays.asList("1", "2"))));
        redisTemplate.convertAndSend(RedisConstant.TOPIC_COMMENT,
                JSON.toJSONString(new MessageCollect("3", "4", "wangql", "nidaye", "9", Arrays.asList("1", "2"))));
        System.out.println("333333333333");
        Thread.sleep(3000);
    }
}
