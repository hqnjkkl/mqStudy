package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    public static void main(String[] args) throws JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                if(message instanceof  TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try{
                        System.out.println(textMessage.getText());
                    }catch (JMSException e){
                        e.printStackTrace();
                    }

                }
            }
        });
        consumer.close();
        session.close();
        connection.close();
    }
}
