package one.block.eosiojavarpcprovider;

import org.junit.Test;

import java.io.IOException;

import one.block.eosiojavarpcprovider.implementations.EosioJavaRpcAmqpClientImpl;

public class EosioJavaRpcAmqpClientImplTest {

    private EosioJavaRpcAmqpClientImpl eosioJavaRpcAmqpClient;

    @Test
    public void test() {
        eosioJavaRpcAmqpClient = new EosioJavaRpcAmqpClientImpl();

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
