package one.block.eosiojavarpcprovider.implementations;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import one.block.eosiojavarpcprovider.toBeMoved.AMQPConfig;

public class EosioJavaRpcAmqpClientImpl {

    private AMQPConfig amqpConfig;

    public EosioJavaRpcAmqpClientImpl(AMQPConfig amqpConfig) {
        this.amqpConfig = amqpConfig;
    }

    public String call(String message) throws IOException {
        AtomicReference<String> result = new AtomicReference<>();

        ConfirmCallback messageConfirmedCallback = (sequenceNumber, multiple) -> {
            // Message confirmed successful delivery
            result.set(Long.toString(sequenceNumber));
        };

        ConfirmCallback messageRejectedCallback = (sequenceNumber, multiple) -> {
            // Message rejected/nacked
            System.out.println("message was nacked/rejected");
            result.set("rejected");
        };

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.amqpConfig.getHostName());

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .replyTo(channel.queueDeclare().getQueue()) // this.amqpConfig.getReplyToQueueName() // Only know at runtime?
                    .build();

            channel.confirmSelect();
            channel.addConfirmListener(messageConfirmedCallback, messageRejectedCallback);
            channel.basicPublish(this.amqpConfig.getExchangeName(), this.amqpConfig.getRequestQueueName(), props, message.getBytes("UTF-8"));

            // Naive wait for result to be available
            while(true) {
                if (result != null && result.get() != null) {
                    break;
                }
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return result.get();
    }
}
