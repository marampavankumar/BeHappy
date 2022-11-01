package test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazon.sqs.javamessaging.SQSSession;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SQSReceiveMsg {
	
	public static void main(String args[]) throws JMSException, InterruptedException
	
	{
		
		
		AmazonSQSClientBuilder builder = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAX75SQNR2YFQHNYIG", "vZzh4TTPvdOFsrZhqlU1KgXGeqxVReOe1USZ8wq+")));
		
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(), builder);

        SQSConnection connection = connectionFactory.createConnection();
       
        Session session = connection.createSession(false, SQSSession.UNORDERED_ACKNOWLEDGE);

        MessageConsumer consumer = session.createConsumer(session.createQueue("TestTxnDelQueue"));
        connection.start();


		
        consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				com.amazon.sqs.javamessaging.message.SQSMessage sqsMessage = (com.amazon.sqs.javamessaging.message.SQSMessage) message;
                TextMessage payload = (TextMessage) message;

				try {
					System.out.println("payload text:::" + payload.getText());
					System.out.println("receipt handle:::" + sqsMessage.getReceiptHandle());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
        /*AmazonSQS sqs = AmazonSQSClientBuilder.standard()
        		  .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAX75SQNR2YFQHNYIG", "vZzh4TTPvdOFsrZhqlU1KgXGeqxVReOe1USZ8wq+")))
        		  .withRegion(Regions.US_EAST_1)
        		  .build();
        

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/549593312373/TestMsgDelQueue")
        		  .withWaitTimeSeconds(10)
        		  .withMaxNumberOfMessages(10);

        		List<com.amazonaws.services.sqs.model.Message> sqsMessages = sqs.receiveMessage(receiveMessageRequest).getMessages();   
        		
        System.out.println(sqsMessages.get(0).getBody());        		
        System.out.println(sqsMessages.get(0).getReceiptHandle());*/
        
        
        while(true)
        {
        	Thread.sleep(1000*60);
        }
        
		
	}

}
