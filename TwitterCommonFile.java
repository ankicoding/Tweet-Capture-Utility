package com.mine.twittercaptureutility;

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
			String path = "pathtoyourlogfile";
			FileWriter Writer = new FileWriter(path,true);
			Writer.write("\r\n" + strCurrDate +" "+strText );
			Writer.close();
  }
}
