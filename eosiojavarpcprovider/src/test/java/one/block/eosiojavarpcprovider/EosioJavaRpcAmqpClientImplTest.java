package one.block.eosiojavarpcprovider;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcAmqpClientImpl;
import one.block.eosiojavarpcprovider.toBeMoved.AMQPConfig;

public class EosioJavaRpcAmqpClientImplTest {

    private EosioJavaRpcAmqpClientImpl eosioJavaRpcAmqpClient;

    @Test
    public void test() {
        String requestQueueName = "rpc_queue";
        String replyToQueueName = "rpc_queue";
        String hostName = "localhost";
        AMQPConfig config = new AMQPConfig(requestQueueName, replyToQueueName, hostName);
        eosioJavaRpcAmqpClient = new EosioJavaRpcAmqpClientImpl(config);

        List<String> results = new ArrayList<>();

        try {
            for (int i = 0; i < 32; i++) {
                String result = eosioJavaRpcAmqpClient.call(Integer.toString(i));
                results.add(result);
            }
            String test = "";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
