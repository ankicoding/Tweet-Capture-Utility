package com.newgen.twittercaptureutility;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;


public class TwitterCommonFile {

  public Document XmLParser(String strFileName){
	  Document doc = null;
    try {

    File fXmlFile = new File(strFileName);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    doc = dBuilder.parse(fXmlFile);

    doc.getDocumentElement().normalize();
   /* NodeList nList = doc.getElementsByTagName("TwitterCaptureUtilityInfo");
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
	 		}*/
    } catch (Exception e) {
     e.printStackTrace();
    	try {
			writeToFile(e.getMessage().toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	return doc;
  }
  
  public void writeToFile(String strText) throws IOException{
			Date dt=new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String strCurrDate=formatter.format(dt);	
			String path = "D://TwitterLog.txt";
			FileWriter Writer = new FileWriter(path,true);
			Writer.write("\r\n" + strCurrDate +" "+strText );
			Writer.close();
  }
}
