package one.block.eosiojavarpcprovider;

import org.junit.Test;

import java.io.IOException;

import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcAmqpClientImpl;
import one.block.eosiojavarpcprovider.toBeMoved.AMQPConfig;

public class EosioJavaRpcAmqpClientImplTest {

    private EosioJavaRpcAmqpClientImpl eosioJavaRpcAmqpClient;

    @Test
    public void test() {
        String requestQueueName = "";
        String replyToQueueName = "";
        String hostName = "";
        AMQPConfig config = new AMQPConfig(requestQueueName, replyToQueueName, hostName);
        eosioJavaRpcAmqpClient = new EosioJavaRpcAmqpClientImpl(config);

        try {
            String result = eosioJavaRpcAmqpClient.call("10");
            String test = result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
