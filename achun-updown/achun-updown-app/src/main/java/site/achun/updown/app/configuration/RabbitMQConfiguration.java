package site.achun.updown.app.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public FanoutExchange fileRemoveExchange(){
        return new FanoutExchange("FILE_REMOVE_FANOUT_EXCHANGE");
    }

    @Bean
    public Queue fileRemoveQueue(){
        return new Queue("file.remove.queue");
    }

    @Bean
    public Binding fileUpdateBinding(Queue fileRemoveQueue,FanoutExchange fileRemoveExchange){
        return BindingBuilder.bind(fileRemoveQueue).to(fileRemoveExchange);
    }

}
