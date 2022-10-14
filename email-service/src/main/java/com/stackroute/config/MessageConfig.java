package com.stackroute.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
	 private String exchangeName="user_exchange";
	    private String registerQueue="user_queue";


	        @Bean
	        public Jackson2JsonMessageConverter jsonMessageConverter()
	        {
	            return new Jackson2JsonMessageConverter();
	        }

	    @Bean
	    public DirectExchange directExchange()
	    {
	        return new DirectExchange(exchangeName);
	    }

	    @Bean
	    public Queue registerQueue()
	    {
	        return new Queue(registerQueue,false);
	    }

	    @Bean
	    Binding bindingUser(Queue registerQueue, DirectExchange exchange)
	    {
	        return BindingBuilder.bind(registerQueue()).to(exchange).with("user_routing");
	    }

	}



//	
//	private String exchangeName="user_exchange";
//	
//    public static final String USER_QUEUE = "user_queue";
//    
//    @Bean
//    public Queue userQueue(){
//        return new Queue(USER_QUEUE,true);
//    }
//
//
//    @Bean
//    public DirectExchange directExchange()
//    {
//        return new DirectExchange(exchangeName);
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter consumerJackson2MessageConverter()
//    {
//        return new  Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
//    {
//        RabbitTemplate rabbitTemp=new RabbitTemplate();
//        rabbitTemp.setMessageConverter((MessageConverter) consumerJackson2MessageConverter());
//        return rabbitTemp;
//    }
//
//}



