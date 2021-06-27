package com.rrc.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rrc.listener.RedisReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class RedisConfig {
    //lettuce客户端连接工厂
    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;
    //日志
    private Logger logger=LoggerFactory.getLogger(RedisConfig.class);
    //json序列化器
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    //缓存生存时间
    private Duration timeToLive = Duration.ofDays(1);
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //redis缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();
        //缓存配置map
        Map<String,RedisCacheConfiguration> cacheConfigurationMap=new HashMap<>();
                //自定义缓存名，后面使用的@Cacheable的CacheName
        cacheConfigurationMap.put("users",config);
        cacheConfigurationMap.put("default",config);
        //根据redis缓存配置和reid连接工厂生成redis缓存管理器
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .withInitialCacheConfigurations(cacheConfigurationMap)
                .build();
        logger.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }
    //redisTemplate模板提供给其他类对redis数据库进行操作
    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //序列化配置
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        logger.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }
    //redis键序列化使用StrngRedisSerializer
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    //redis值序列化使用json序列化器
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
    //缓存键自动生成器
    @Bean
    public KeyGenerator myKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }



    /**
     * redis消息监听器容器
     *            点赞消息订阅处理器
     * @param collectListenerAdapter
     *            关注消息订阅处理器
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                            MessageListenerAdapter collectListenerAdapter,
                                            MessageListenerAdapter commentListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        // 以下为修改默认的序列化方式，网上大多数消息发布订阅都是String类型,但是实际中数据类型肯定不止String类型
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 收藏主题并绑定消息订阅处理器
        collectListenerAdapter.setSerializer(jackson2JsonRedisSerializer);
        container.addMessageListener(collectListenerAdapter, new ChannelTopic("TOPIC_COLLECT"));

        // 收藏主题并绑定消息订阅处理器
        //commentListenerAdapter.setSerializer(jackson2JsonRedisSerializer);
        //container.addMessageListener(commentListenerAdapter, new PatternTopic(RedisConstant.TOPIC_COMMENT));

        return container;
    }

    /**
     * 收藏消息订阅处理器,并指定处理方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter commentListenerAdapter(RedisReceiver receiver) {
        MessageListenerAdapter commentListenerAdapter = new MessageListenerAdapter(receiver);
        //消息的反序列化方式
        commentListenerAdapter.setSerializer(jackson2JsonRedisSerializer);
        return commentListenerAdapter;
    }


}