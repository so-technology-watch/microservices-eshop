package fr.sogeti.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author fduneau
 */
public class AmqpClient {
    private final Connection client;
    private final Channel channel;
    
    public AmqpClient(String host, String username, String password, String virtualHost, int port) throws IOException, TimeoutException {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPort(port);
        client = connectionFactory.newConnection();
        channel = client.createChannel();
    }
    
    public void publish(String exchangeName, String exchangeType, String routingKey, String message) throws IOException {
        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
    }
    
    public boolean isOpen(){
        return client.isOpen();
    }
}
