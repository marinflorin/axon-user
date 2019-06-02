package com.peaas.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {

    @Value("${axon.amqp.exchange}")
    private String exchange;

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(exchange).build();
    }

//    @Bean
//    public Queue peaasWalletQueue() {
//        return QueueBuilder.durable("peaas_wallet").build();
//    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

//    @Bean
//    public Binding peaasWalletBinding() {
//        return BindingBuilder.bind(peaasWalletQueue()).to(exchange()).with("*").noargs();
//    }

    @Autowired
    public void configure(AmqpAdmin amqpAdmin) {
        amqpAdmin.declareExchange(exchange());

        amqpAdmin.declareQueue(queue());
        amqpAdmin.declareBinding(binding());

//        amqpAdmin.declareQueue(peaasWalletQueue());
//        amqpAdmin.declareBinding(peaasWalletBinding());
    }

//	@Bean
//	public SpringAMQPMessageSource peaas_wallet(Serializer serializer) {
//		System.out.println("--- On Message Call ---");
//		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
//
//			@RabbitListener(queues = "peaas_wallet")
//			@Transactional
//			@Override
//			public void onMessage(Message message, Channel channel) throws Exception {
//				System.out.println(message.getMessageProperties());
//				System.out.println("channel == " + channel);
//				super.onMessage(message, channel);
//			}
//		};
//	}

//	@Bean
//	public SagaConfiguration<UserSaga> userSagaConfiguration(Serializer serializer) {
//		return SagaConfiguration.subscribingSagaManager(UserSaga.class);
//	}
}
