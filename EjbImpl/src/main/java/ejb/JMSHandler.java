package ejb;

import ejb.interfaces.JMSHandlerManager;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.*;
import java.io.Serializable;
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
    public void sendMsg(Serializable obj, String  ident) {
        try {
            ObjectMessage objMsg=context.createObjectMessage(obj);
            objMsg.setStringProperty("Operation", "Request");
            objMsg.setStringProperty("Id", ident);
            context.createProducer().send(queue, objMsg);
            LOG.info("Send message");
        } catch (JMSException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Serializable receiveMsg(String ident) {
        try {
            String selector="Operation = 'Response' AND Source ='" + ident + "'";
            JMSConsumer receiver=context.createConsumer(queue, selector);
            ObjectMessage resp=(ObjectMessage)receiver.receive(300000);
            Serializable respObj=resp.getObject();
            LOG.info("Received message");
            return respObj;
        } catch (JMSException ex) {
            Logger.getLogger(JMSHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
