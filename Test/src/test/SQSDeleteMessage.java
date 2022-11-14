package test;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageResult;

public class SQSDeleteMessage {

	public static void main(String[] args) {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
      		  .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("", "")))
      		  .withRegion(Regions.US_EAST_1)
      		  .build();

		DeleteMessageResult result = sqs.deleteMessage("https://sqs.us-east-1.amazonaws.com/549593312373/TestTxnDelQueue", "AQEBIqPLDNDZpykfwq1sBWzIvAtagG8LhFNpcp25kYSNe2Mt2nHKpvEExykkFz2M6vc5fhHzXGFbHdHXkPL6uS6KmfURWY8w/6T/C3+dO+yswSFypV8+miMouVbPKOG2yNRvO7S2Mq0zKv8d+i8lm9uakiatL3XHbggSbWxRy2w1F3ZCUYBfk2Nvpz3kGbeJPa9TIOx0gFF3bwVUFkJuK5e9orG4Ffnj6Es56yEEUJYkzqtWBDARzZAI1RdRsf6hS6OgMIdPKbUiVERRiXEvNbnlOdwDKuxprBtf8zF2k0KsCASFoov2A5gxInc+jcL+9hHHMi6y6q6h6RnqdcEW+/0a3zUm0/9rplhnEBAb2jHMPniFpR1W6TNHjwvFfZEyXuOCLEK5w+tri94w6+ex1nKqow==");

		System.out.println(result.toString());

		System.out.println(result.getSdkResponseMetadata());

		System.out.println(result.getSdkHttpMetadata());


	}

}
