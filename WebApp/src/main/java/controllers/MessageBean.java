package controllers;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.SchemaOutputResolver;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:jboss/exported/jms/queue/Project"
        ),
        @ActivationConfigProperty(
                propertyName = "messageSelector",
                propertyValue = "Operation = 'Request'"
        )

})
@Named("msg")
public class MessageBean implements MessageListener {


   /* @Resource(mappedName = "java:/jms/MyXaConnectionFactory")
    private static ConnectionFactory cf;
*/
   /* @Resource(mappedName="java:jboss/exported/jms/queue/Project")
    private Queue queue;
*/


    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;

            try {
                InitialContext ctx=new InitialContext();
                QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("/jms/MyXaConnectionFactory");
                Connection conn=f.createConnection();
                Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
                MessageProducer replyProd=session.createProducer();

                //JMSProducer prod=mdc.createProducer();
                // prod.set
                TextMessage msg =session.createTextMessage();
                msg.setStringProperty("Operation","Response");
                msg.setText("Nastąpiło wylogowanie!");
                msg.setJMSDestination(message.getJMSDestination());
                replyProd.send(message.getJMSDestination(),msg);

                System.out.println("xxx");
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}