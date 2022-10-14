package com.stackroute.config;

import com.stackroute.Rabbitmq.EmailDTO;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.model.EmailMessage;
import com.stackroute.serviceImpl.EmailSenderServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Consumer {

	private Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private EmailSenderServiceImpl userService;

	@RabbitListener(queues = "user_queue")
	public void getUserDtoFromRabbitMq(EmailDTO emailDto) throws UserAlreadyExistsException {
		logger.info("Inside getUserDtoFromRabbitMq of Consumer :{}", emailDto);
		EmailMessage user = new EmailMessage();
		user.setReceiver(emailDto.getReceiver());
		user.setSubject(emailDto.getSubject());
		user.setMessage(emailDto.getMessage());
		userService.sendEmail(user.getReceiver(), user.getSubject(), user.getMessage());
		logger.info("Email sent successfully");
	}
}

//public class Consumer {
//    @Autowired
//    private EmailSenderServiceImpl emailSenderService;
//
//    @RabbitListener(queues="user_queue")
//    public void getUserDtoFromRabbitMq(EmailDTO emailDto) throws UserAlreadyExistsException
//    {
//        System.out.println(emailDto.toString());
//        User user=new User();
//        user.setEmail(emailDto.getEmail());
//        user.setPassword(emailDto.getPassword());
//        emailSenderService.saveUser(user);
//    }
//}
