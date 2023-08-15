package site.achun.audio.app.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public FanoutExchange fileUpdateExchange(){
        return new FanoutExchange("FILE_UPDATE_FANOUT_EXCHANGE");
    }

    @Bean
    public Queue fileUpdateQueue(){
        return new Queue("file.update.queue");
    }

    @Bean
    public Binding fileUpdateBinding(Queue fileUpdateQueue,FanoutExchange fileUpdateExchange){
        return BindingBuilder.bind(fileUpdateQueue).to(fileUpdateExchange);
    }

    @Bean
    public FanoutExchange unitUpdateExchange(){
        return new FanoutExchange("UNIT_UPDATE_FANOUT_EXCHANGE");
    }

    @Bean
    public Queue unitUpdateQueue(){
        return new Queue("unit.update.queue");
    }

    @Bean
    public Binding unitUpdateBinding(Queue unitUpdateQueue,FanoutExchange unitUpdateExchange){
        return BindingBuilder.bind(unitUpdateQueue).to(unitUpdateExchange);
    }
}
