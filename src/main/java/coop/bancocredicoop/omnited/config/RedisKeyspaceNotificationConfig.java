package coop.bancocredicoop.omnited.config;

import coop.bancocredicoop.omnited.service.OperatorKeyspaceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisKeyspaceNotificationConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private OperatorKeyspaceListener operatorKeyspaceListener;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        
        // Nos suscribimos a notificaciones para claves que comienzan con "operator:"
        container.addMessageListener(
            new MessageListenerAdapter(operatorKeyspaceListener, "onMessage"),
            new PatternTopic("__keyspace@0__:operator:*")
        );
        return container;
    }
}