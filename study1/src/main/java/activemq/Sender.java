package activemq;


import com.rabbitmq.client.AMQP;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: activemq_01
 * @ClassName Sender
 * @description: 消息发送
 * @author: muxiaonong
 * @create: 2020-10-02 13:01
 * @Version 1.0
 **/
public class Sender {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("hello!test-queue-come on");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }
}