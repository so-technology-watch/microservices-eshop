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
    
    /**
     * 
     * @param exchangeName the exchange's name
     * @param exchangeType the exchange's type
     * @param routingKey the routing key
     * @param message the message we want to publish
     * @throws IOException 
     */
    public void publish(String exchangeName, String exchangeType, String routingKey, String message) throws IOException {
        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
    }
    
    /**
     * 
     * @return a boolean that indicates if the client is open
     */
    public boolean isOpen(){
        return client.isOpen();
    }
}
