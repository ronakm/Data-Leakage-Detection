package com.helper;

//1.Open a new Java class in the project (default package)
//2.Copy paste the below code
//3.Compile - Don't execute this[Run]

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.Flags.Flag;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.ParseException;
import javax.mail.search.FlagTerm;
import javax.mail.search.MessageNumberTerm;
import javax.servlet.jsp.JspException;

import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.pop3.POP3SSLStore;

public class MailUtility {

	private Session session = null;
	private Store store = null;
	private String username, password;
	private Folder folder;

	public MailUtility() {

	}

	public void setUserPass(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static void main(String[] args) {
		MailUtility gmail = new MailUtility();
		try {
			// gmail.connect();
			// gmail.openFolder("INBOX");
			// gmail.printAllMessages();

//			System.out.println(new MailUtility().getMailMessages("INBOX",
//					"mail.rajesh.agrawal@gmail.com", "raj@").size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Part getBodyMultiPart(String host, String user, String password,
			int messageNo, int attNo, String folerName) {
		Part part = null;
		try {
			MailUtility gmail = new MailUtility();
			gmail.setUserPass(user, password);
			gmail.connect();
			gmail.openFolder(folerName);
			Message msg = gmail.folder.getMessage(messageNo);
			Multipart mp = (Multipart) msg.getContent();
			part = mp.getBodyPart(attNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return part;
	}

	public static ArrayList getMailMessages(String foldername, String user,
			String password) {
		ArrayList arr = null;
		MailUtility gmail = new MailUtility();
		gmail.setUserPass(user, password);
		try {

			gmail.connect();
			gmail.openFolder(foldername);
			arr = gmail.getMessageList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public void deleteSpecificMail(String foldername, String user,
			String password, int mno) {

		System.out.println("foldername " + foldername + " user" + user
				+ " mno " + mno);
		ArrayList arr = null;
		MailUtility gmail = new MailUtility();
		gmail.setUserPass(user, password);
		try {
			gmail.connect();
			gmail.openFolder(foldername);
			// arr = gmail.getMessageList();
			// Message[] msgs = folder.getMessages();

			Message m = gmail.folder.getMessage(mno);
			m.setFlag(Flag.DELETED, true);
			gmail.folder.close(false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public void connect() throws Exception {

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties pop3Props = new Properties();

		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false"); // standard
																			// parameter
		pop3Props.setProperty("mail.pop3.port", "995");
		pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

		URLName url = new URLName("pop3", "pop.gmail.com", 995, "", username,
				password);

		session = Session.getInstance(pop3Props, null);
		store = new POP3SSLStore(session, url);
		store.connect();
		store.close();

		Properties iampProps = new Properties();

		iampProps.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
		iampProps.setProperty("mail.imap.socketFactory.fallback", "false");
		iampProps.setProperty("mail.imap.port", "993");
		iampProps.setProperty("mail.imap.socketFactory.port", "993");

		url = new URLName("imap", "imap.gmail.com", 993, "", username, password);

		session = Session.getInstance(iampProps, null);
		store = new IMAPSSLStore(session, url);
		store.connect();

	}

	public boolean sendEmail(HashMap parameters) {

		boolean success = false;
		String recipients = StringHelper.nullObjectToStringEmpty(parameters
				.get("to"));
		String sender = StringHelper.nullObjectToStringEmpty(parameters
				.get("from"));
		String cc = StringHelper.nullObjectToStringEmpty(parameters.get("cc"));
		String subject = StringHelper.nullObjectToStringEmpty(parameters
				.get("subject"));
		String body = StringHelper.nullObjectToStringEmpty(parameters
				.get("body"));
		final String userName = StringHelper.nullObjectToStringEmpty(parameters
				.get("user"));
		final String password = StringHelper.nullObjectToStringEmpty(parameters
				.get("password"));
		//
		// String recipients="mail.rajesh.agrawal@gmail.com";
		// String sender="rajagrawal4uster@localhost";
		// String cc="rajagrawal4uster@gmail.com";
		// String subject="Test Test";
		// String body="Test Body";
		// final String userName="rajagrawal4uster@gmail.com";
		// final String password="asd*";
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session sessio1n = Session.getDefaultInstance(props, auth);

		sessio1n.setDebug(true);

		Message msg = new MimeMessage(sessio1n);
		InternetAddress[] toAddrs = null, ccAddrs = null;

		try {

			if (recipients.length() > 0) {
				toAddrs = InternetAddress.parse(recipients, false);
				msg.setRecipients(Message.RecipientType.TO, toAddrs);
			} else
				throw new JspException("No recipient address specified");

			// if (sender.length()>0)
			// msg.setFrom(new InternetAddress(sender));
			// else
			// throw new JspException("No sender address specified");

			if (cc.length() > 0) {
				ccAddrs = InternetAddress.parse(cc, false);
				msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			}

			if (subject != null)
				msg.setSubject(subject);
			msg.setSentDate(new Date());
			if (body != null)
				msg.setText(body);
			else
				msg.setText("");

			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = true;
		return success;
	}

	public void openFolder(String folderName) throws Exception {

		// Open the Folder
		folder = store.getDefaultFolder();

		folder = folder.getFolder(folderName);

		if (folder == null) {
			throw new Exception("Invalid folder");
		}

		// try to open read/write and if that fails try read-only
		try {

			folder.open(Folder.READ_WRITE);

		} catch (MessagingException ex) {

			folder.open(Folder.READ_ONLY);

		}

		// Message[] unreadMessages = folder.search(
		// new FlagTerm(new Flags(Flags.Flag.RECENT), true));
		//
		// String phone=ConnectionManager.getGmailUserId(username);
		// System.out.println("phone "+phone);
		//
		//
		// if(unreadMessages!=null&&phone.length()>0){
		// FetchMailUsage.unreadmessages=unreadMessages.length;
		// try{
		// Sender sender=new
		// Sender(phone,"You have "+FetchMailUsage.unreadmessages+" new mails.");
		// sender.send();
		// }catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }

	}

	public void closeFolder() throws Exception {
		folder.close(false);
	}

	public int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	public int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	public void disconnect() throws Exception {
		store.close();
	}

	public void printMessage(int messageNo) throws Exception {
		System.out.println("Getting message number: " + messageNo);

		Message m = null;

		try {
			m = folder.getMessage(messageNo);
			dumpPart(m);
		} catch (IndexOutOfBoundsException iex) {
			System.out.println("Message number out of range");
		}
	}

	public void printAllMessageEnvelopes() throws Exception {

		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");
			dumpEnvelope(msgs[i]);

		}

	}

	public void printAllMessages() throws Exception {

		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");
			dumpPart(msgs[i]);
		}

	}

	public void getAllMessages() throws Exception {

		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");
			dumpPart(msgs[i]);
		}

	}
	public static boolean showAllMessages=false;
	public ArrayList getMessageList() throws Exception {
		ArrayList arr = new ArrayList();
		// Attributes & Flags for all messages ..
		// Message[] msgs = folder.getMessages();
		Message[] msgs =null;
		
		if(showAllMessages){
			System.out.println("--------------------------Showing All Messages---------------------");
			msgs = folder.getMessages();
		}else{
			System.out.println("--------------------------Showing Unread Messages---------------------");
			FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
			msgs = folder.search(ft);
		}
		// Use a suitable FetchProfile
		// FetchProfile fp = new FetchProfile();
		// fp.add(FetchProfile.Item.ENVELOPE);
		// folder.fetch(msgs, fp);

		java.util.Arrays.sort(msgs, new Comparator() {
			public int compare(Object o1, Object o2) {
				try {
					Message m1 = (Message) o1;
					Message m2 = (Message) o2;
					if (m1 != null && m2 != null && m1.getSentDate() != null
							&& m2.getSentDate() != null) {
						return (m1.getSentDate().compareTo(m2.getSentDate()));
					} else {
						return 0;
					}
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");

			// if(!msgs[i].isSet(Flag.DELETED)){
			arr.add(msgs[i]);
			// }
		}

		return arr;
	}

	public static void dumpPart(Part p) throws Exception {
		if (p instanceof Message)
			dumpEnvelope((Message) p);

		String ct = p.getContentType();
		try {
			pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
		} catch (ParseException pex) {
			pr("BAD CONTENT-TYPE: " + ct);
		}

		/*
		 * Using isMimeType to determine the content type avoids fetching the
		 * actual content data until we need it.
		 */
		if (p.isMimeType("text/plain")) {
			pr("This is plain text");
			pr("---------------------------");
			System.out.println((String) p.getContent());
		} else {

			// just a separator
			pr("---------------------------");

		}
	}

	public static void dumpEnvelope(Message m) throws Exception {
		pr(" ");
		Address[] a;
		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				pr("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				pr("TO: " + a[j].toString());
			}
		}

		// SUBJECT
		pr("SUBJECT: " + m.getSubject());

		// DATE
		Date d = m.getSentDate();
		pr("SendDate: " + (d != null ? d.toString() : "UNKNOWN"));

	}

	static String indentStr = "                                               ";
	static int level = 0;

	/**
	 * Print a, possibly indented, string.
	 */
	public static void pr(String s) {

		System.out.print(indentStr.substring(0, level * 2));
		System.out.println(s);
	}

	public HashMap getMail(String foldername, String user, String password,
			int messageNo, int attNo) {

		System.out
				.println("String user,String password  ... " + user + " ... ");
		Part part = null;
		HashMap hmp = new HashMap();
		try {
			MailUtility gmail = new MailUtility();
			gmail.setUserPass(user, password);
			try {
				gmail.connect();
				gmail.openFolder(foldername);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("folder " + gmail.folder);
			int count = gmail.folder.getMessageCount();
			System.out.println(count + " total messages");
			MessageNumberTerm st = new MessageNumberTerm(messageNo);
			// Get some message references
			Message[] found = gmail.folder.search(st);
			Multipart mp = null;
			try {
				mp = (Multipart) found[0].getContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			part = mp.getBodyPart(attNo);
			System.out
					.println(found.length
							+ " messages matched Subject pattern \""
							+ messageNo + "\"");

			// "true" actually deletes flagged messages from folder
			gmail.folder.close(false);
			gmail.store.close();

		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
		}

		return hmp;
	}

	public boolean sendEmailMultipart(HashMap parameters) {

		boolean success = false;
		String recipients = StringHelper.nullObjectToStringEmpty(parameters
				.get("to"));
		// String
		// sender=StringHelper.nullObjectToStringEmpty(parameters.get("from"));
		String cc = StringHelper.nullObjectToStringEmpty(parameters.get("cc"));
		String subject = StringHelper.nullObjectToStringEmpty(parameters
				.get("subject"));
		String body = StringHelper.nullObjectToStringEmpty(parameters
				.get("body"));
		Object multipart = parameters.get("ATTACH");
		final String userName = StringHelper.nullObjectToStringEmpty(parameters
				.get("user"));
		final String password = StringHelper.nullObjectToStringEmpty(parameters
				.get("password"));
		//
		// String recipients="mail.rajesh.agrawal@gmail.com";
		// String sender="rajagrawal4uster@localhost";
		// String cc="rajagrawal4uster@gmail.com";
		// String subject="Test Test";
		// String body="Test Body";
		// final String userName="rajagrawal4uster@gmail.com";
		// final String password="asd*";
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session sessio1n = Session.getDefaultInstance(props, auth);

		sessio1n.setDebug(true);

		Message msg = new MimeMessage(sessio1n);
		InternetAddress[] toAddrs = null, ccAddrs = null;

		try {

			if (recipients.length() > 0) {
				String toList = "";
				String toListA[] = recipients.split(",");
				for (int i = 0; i < toListA.length; i++) {
					if (!toListA[i].trim().equalsIgnoreCase("all"))
						// toList+=","+StringHelper.nullObjectToStringEmpty(details.get("EMAIL_LIST"));
						// else
						toList += "," + toListA[i];
				}

				recipients = toList.substring(1);
				toAddrs = InternetAddress.parse(toList, false);

				msg.setRecipients(Message.RecipientType.TO, toAddrs);
			} else
				throw new JspException("No recipient address specified");

			if (userName.length() > 0)
				msg.setFrom(new InternetAddress(userName));
			else
				throw new JspException("No sender address specified");

			if (cc.length() > 0) {

				String ccList = "";
				String ccListA[] = cc.split(",");
				for (int i = 0; i < ccListA.length; i++) {
					// if(ccListA[i].trim().equalsIgnoreCase("all"))
					// ccList+=","+StringHelper.nullObjectToStringEmpty(details.get("EMAIL_LIST"));
					// else
					ccList += "," + ccListA[i];
				}
				cc = ccList;
				ccAddrs = InternetAddress.parse(ccList, false);
				msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			}

			if (subject != null)
				msg.setSubject(subject);
			msg.setSentDate(new Date());
			Multipart mp = new MimeMultipart();

			MimeBodyPart mailBody = new MimeBodyPart();

			if (body != null)
				mailBody.setText(body);
			else
				mailBody.setText("");
			mp.addBodyPart(mailBody);
			if (multipart != null) {
				if (multipart instanceof ArrayList) {
					ArrayList arr = (ArrayList) multipart;
					for (int i = 0; i < arr.size(); i++) {
						mp.addBodyPart((MimeBodyPart) arr.get(i));
					}
				} else if (multipart instanceof MimeBodyPart) {
					mp.addBodyPart((MimeBodyPart) multipart);
				}
			}
			msg.setContent(mp);
			System.out.println("To List " + recipients);
			System.out.println("CC List " + cc);

			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = true;

		return success;
	}

}
