package ejb;

import ejb.interfaces.JMSHandlerManager;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class JMSHandler implements JMSHandlerManager {

    @Inject
    @JMSConnectionFactory("java:/jms/MyXaConnectionFactory")
    private JMSContext context;

    @Resource(mappedName="java:jboss/exported/jms/queue/Project")
    private Queue queue;

    private static final Logger LOG = Logger.getLogger(JMSHandler.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendMsg(String txt) {
        try {
            TextMessage txtMsg=context.createTextMessage("Treść komunikatu");
            txtMsg.setStringProperty("cecha1", "A");
            txtMsg.setStringProperty("Operation", "Request");
            context.createProducer().send(queue, txtMsg);
            LOG.info("Send message");
        } catch (JMSException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String receiveMsg() {
        try {
            JMSConsumer receiver=context.createConsumer(queue, "Operation = 'Response'");
            TextMessage resp=(TextMessage)receiver.receive(300000);
            String respTxt=resp.getText();
            LOG.info("Received message");
            return respTxt;
        } catch (JMSException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
