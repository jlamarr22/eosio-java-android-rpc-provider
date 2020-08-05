package one.block.eosiojavarpcprovider.implementations;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class EosioJavaRpcAmqpClientImpl implements AutoCloseable {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public EosioJavaRpcAmqpClientImpl() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();
        AtomicReference<String> result = new AtomicReference<>();

        channel.queueDeclare(requestQueueName, false, false, false, null);

        channel.confirmSelect();

        ConfirmCallback messageConfirmedCallback = (sequenceNumber, multiple) -> {
            // Message confirmed successful delivery
            result.set(Long.toString(sequenceNumber));
        };

        ConfirmCallback messageRejectedCallback = (sequenceNumber, multiple) -> {
            // Message rejected/nacked
            System.out.println("message was nacked/rejected");
        };

        channel.addConfirmListener(messageConfirmedCallback, messageRejectedCallback);

        channel.basicPublish("", requestQueueName, null, message.getBytes("UTF-8"));

//        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
//
//        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
//            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
//                response.offer(new String(delivery.getBody(), "UTF-8"));
//            }
//        }, consumerTag -> {
//        });
//
//        String result = response.take();
//        channel.basicCancel(ctag);

        // Wait for a response from the server
        while(true) {
            if (result != null) {
                break;
            }
        }

        return result.get();
    }

    public void close() throws IOException {
        connection.close();
    }
}
