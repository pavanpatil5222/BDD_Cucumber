package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


//import com.j256.simplemagic.ContentInfo;
//import com.j256.simplemagic.ContentInfoUtil;
//import com.j256.simplemagic.ContentType;

//import com.testautomationguru.utility.PDFUtil;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.SubjectTerm;



/**
 * Utility for interacting with an Email application
 */
public class EmailUtils {	

	
	private static Folder folder;

	public static void connectToEmailServer(String username, String password) throws Exception {
		Properties props = System.getProperties();
		try {
			props.load(new FileInputStream(new File("./src/test/resources/email.properties")));
		} catch (Exception e) {
			throw new Exception("Loading email properties file is having issue" + e);
//	        System.exit(-1);
		}
		Session mailSession = Session.getInstance(props);
		mailSession.setDebug(true);
		Store store = mailSession.getStore("imaps");
		store.connect("smtp.googlemail.com", username, password);
		folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);

	}

	public static String getEmailSubject(Message message) throws Exception {
		return message.getSubject();
	}
		
	public static int getNumberOfMessages() throws MessagingException {
	    return folder.getMessageCount();
	  }
	
	public static int getNumberOfUnreadMessage() throws MessagingException {
		return folder.getUnreadMessageCount();
	}
	  /**
	   * Searches for messages with a specific subject
	   * @param subject Subject to search messages for
	   * @param unreadOnly Indicate whether to only return matched messages that are unread
	   * @param maxToSearch maximum number of messages to search, starting from the latest. For example, enter 100 to search through the last 100 messages.
	   */
	public static Message[] getMessagesBySubject(String subject, boolean unreadOnly, int maxToSearch) throws Exception{
	    Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);
	 
	    Message messages[] = folder.search(new SubjectTerm(subject), folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));
	    if(unreadOnly){
	      List<Message> unreadMessages = new ArrayList<Message>();
	      for (Message message : messages) {
	        if(isMessageUnread(message)) {
	          unreadMessages.add(message);
	        }
	      }
	      messages = unreadMessages.toArray(new Message[]{});
	    } else {
	    	List<Message> seenMessages = new ArrayList<Message>();
		      for (Message message : messages) {
		        if(isMessageRead(message)) {
		        	seenMessages.add(message);
		        }
		      }
		      messages = seenMessages.toArray(new Message[]{});
	    }
	 
	    return messages;
	  }
	
	private static Map<String, Integer> getStartAndEndIndices(int max) throws MessagingException {
	    int endIndex = getNumberOfMessages();
	    int startIndex = endIndex - max;
	 
	    //In event that maxToGet is greater than number of messages that exist
	    if(startIndex < 1){
	      startIndex = 1;
	    }
	 
	    Map<String, Integer> indices = new HashMap<String, Integer>();
	    indices.put("startIndex", startIndex);
	    indices.put("endIndex", endIndex);
	 
	    return indices;
	  }
	  
	  public static boolean isMessageInFolder(String subject, boolean unreadOnly) throws Exception {
	    int messagesFound = getMessagesBySubject(subject, unreadOnly, getNumberOfMessages()).length;
	    return messagesFound > 0;
	  }
	 
	  public static boolean isMessageUnread(Message message) throws Exception {
	    return !message.isSet(Flags.Flag.SEEN);
	  }
	  
	  public static boolean isMessageRead(Message message) throws Exception {
		    return message.isSet(Flags.Flag.SEEN);
		  }
	  
	public static boolean hasAttachments(Message email) throws Exception {
	    String contentType = email.getContentType();
	    System.out.println(contentType);

	    if (contentType.toLowerCase().contains("multipart/mixed")) {
	        // this message must contain attachment
	        Multipart multiPart = (Multipart) email.getContent();

	        for (int i = 0; i < multiPart.getCount(); i++) {
	            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
	            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                System.out.println("Attached filename is:" + part.getFileName());
	            }
	        }

	        return true;
	    }

	    return false;
	}
}