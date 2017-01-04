package com.mine.twittercaptureutility;

import org.w3c.dom.NodeList;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.Timer;

public class TweetCapture{
	public static TwitterCommonFile twCommon = new TwitterCommonFile();
	
	
	/*public static void main(String args[]) throws IOException{
		load();	
	}*/
	
	public static  void load() throws IOException{
		try{	
			TwitterCommonFile twCommon = new TwitterCommonFile();
		    twCommon.writeToFile("Load Method for TweetCaptureUtility");
		    String strInputXML="pathofyourxmlfile";
		    connect(strInputXML);
		    twCommon.writeToFile("OutputXMl After Connection with twitter \n");
		}catch (Exception e){
			e.printStackTrace();
	        twCommon.writeToFile("Exception Occurred "+e.getMessage().toString());
	        } 
		  }
	
	public static void  connect(String strInputXML) throws IOException{
	   try	{
		Document doc;
		 String strProxyServer=null;
		 String strProxyPort=null;
		 String strProxyUsername=null;
		 String strProxyPassword=null;
		 String strConsumerKey=null;
		 String strConsumerSecret=null;
		 String strAccessToken=null;
		 String strAccessTokenSecret=null;
		 String strPaging=null;
		doc=twCommon.XmLParser(strInputXML);
		NodeList nList = doc.getElementsByTagName("TwitterCaptureUtilityInfo");
		 for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);

		        System.out.println("\nCurrent Element :" + nNode.getNodeName());

		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            strProxyServer = eElement.getElementsByTagName("ProxyServer").item(0).getTextContent();
		            System.out.println(strProxyServer);
		            strProxyPort = eElement.getElementsByTagName("ProxyPort").item(0).getTextContent();
		            System.out.println(strProxyPort);
		            strProxyUsername=eElement.getElementsByTagName("ProxyUsername").item(0).getTextContent();
		            System.out.println(strProxyUsername);
		            strProxyPassword=eElement.getElementsByTagName("ProxyPassword").item(0).getTextContent();
		            System.out.println(strProxyPassword);
		            strConsumerKey=eElement.getElementsByTagName("ConsumerKey").item(0).getTextContent();
		            System.out.println(strConsumerKey);
		            strConsumerSecret=eElement.getElementsByTagName("ConsumerSecret").item(0).getTextContent();
		            System.out.println(strConsumerSecret);
		            strAccessToken=eElement.getElementsByTagName("AccessToken").item(0).getTextContent();
		            System.out.println(strAccessToken);
		            strAccessTokenSecret= eElement.getElementsByTagName("AccessTokenSecret").item(0).getTextContent();
		            System.out.println(strAccessTokenSecret);
		            strPaging= eElement.getElementsByTagName("Paging").item(0).getTextContent();
		            System.out.println(strPaging);
		        	}
		 		}
		  
		   ConfigurationBuilder cb= new ConfigurationBuilder();
		 //	cb.setUseSSL(true);
	        cb.setHttpProxyHost(strProxyServer);
	        cb.setHttpProxyPort(Integer.parseInt(strProxyPort));
	        cb.setHttpProxyUser(strProxyUsername);
	        cb.setHttpProxyPassword(strProxyPassword);
	        
	        cb.setDebugEnabled(true)
	                .setOAuthConsumerKey(strConsumerKey)
	                .setOAuthConsumerSecret(strConsumerSecret)
	                .setOAuthAccessToken(strAccessToken)
	                .setOAuthAccessTokenSecret(strAccessTokenSecret);
	      
	        //Instantiate a re-usable and thread-safe factory
	       TwitterFactory twitterFactory = new TwitterFactory(cb.build());
	       Twitter twitter = twitterFactory.getInstance();
	       Paging objPaging = new Paging(40);
	       Random rand=new Random();
	       List<Status> status= twitter.getMentionsTimeline();
	       List listLong = new ArrayList();
	       listLong=searchInDataBase(); //save entire tweet Id in a list
	       for(Status objStatus:status){ 
	    		   long longTweetId = objStatus.getId();
			       String strTweetText = objStatus.getText();
			       long longUserId = objStatus.getUser().getId();
			       String strUserScreenName = objStatus.getUser().getScreenName();
			       strTweetText = strTweetText.substring(objStatus.getInReplyToScreenName().length()+1).trim();
			       if(objStatus.getInReplyToStatusId()==-1){
				       if(!listLong.contains(String.valueOf(longTweetId))){
				    	  int iTicketNo=saveToDataBase(longTweetId,strTweetText,longUserId,strUserScreenName,longTweetId);
				    	  if(iTicketNo!=0){
						      StatusUpdate statusUpdate = new StatusUpdate("@"+strUserScreenName+ "Hello,User!");				    		 
				    		  statusUpdate.inReplyToStatusId(longTweetId);
						      twitter.updateStatus(statusUpdate);
						      twCommon.writeToFile("Succesfully replied to "+ strUserScreenName +".");
				    	  }
			       		}
			       }
			      //strTweetText = strTweetText.substring(strMyUserScreenName.length() + 1, strTweetText.length()).trim();
			       System.out.println(longTweetId +"***"+strUserScreenName +"****"+strTweetText);
	    	   }
					
		}catch(Exception e){
			 e.printStackTrace();
			 twCommon.writeToFile("Connection with Twitter Server failed");
		     twCommon.writeToFile(e.getMessage().toString());
		    
		}
	}
	 //save tweet data to table
    private static int saveToDataBase(long longTweetID, String strTweetText, long longUserID, String strUserScreenName, long longQueryTweetID) throws IOException{
    	 
    	String strSql=null;
    	int iTicketNo=0;
    	try
        {
		//save current tweetId,tweet post,etc. in table.
           }
        catch (Exception e){
          System.err.println("Database exception!");   
          e.printStackTrace();
          twCommon.writeToFile("Error in Inserting Data in Table "+e.getMessage().toString());
        } 
    	return iTicketNo;
    }
   
    private static List<String> searchInDataBase() throws IOException{
    	 List<String> rowValues = new ArrayList<String>();
    	try
        {
    	 
         //get the list of tweetId save in our table
      	  
          }catch (Exception e){
              System.err.println("Database exception!"); 
             // System.err.println(e.getMessage()); 
              e.printStackTrace();
              twCommon.writeToFile("Error in Sarching Data from Table "+e.getMessage().toString());
            } 
    	return rowValues;
    }
}
