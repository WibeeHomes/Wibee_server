package user;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ApiExplorer {
	//기존 테이블 drop 하는 함수 
	//테이블 create 하는 함수 
	//
	//API 파싱해서 DB에 저장하는 함수
    public void apiDB(String type, String loc, String time) throws IOException, ParserConfigurationException, SAXException {
    	
    	StringBuilder urlBuilder = null;
    	if(type=="apt") {
       		 urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent"); /*URL*/
    	}else if(type=="offi") {
    		 urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiRent"); /*URL*/
    	}else if(type=="rh") {
    		urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent"); /*URL*/
    	}

		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=jIVfPPerTxZa7ALt9Ws0Pqk1Qj%2BLLhIsod0B3riK6Z19GUjvZzISKbUv9havnLdKDc8nRbbCjHmzoXLO11KtSg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(loc, "UTF-8")); /*각 지역별 코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*월 단위 신고자료*/
        URL url = new URL(urlBuilder.toString());
        
        String parsingUrl="";
        parsingUrl=url.toString();
        
        // 1. 빌더 팩토리 생성.
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();

        // 2. 빌더 팩토리로부터 빌더 생성
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        
        // 3. 빌더를 통해 XML 문서를 파싱해서 Document 객체로 가져온다.
        Document doc=dBuilder.parse(parsingUrl);
        
        // 문서 구조 안정화 ?
        doc.getDocumentElement().normalize();

        // XML 데이터 중 <person> 태그의 내용을 가져온다.
        NodeList nList=doc.getElementsByTagName("item");
        
        System.out.println("파싱할 리스트 수 : "+ nList.getLength());
        
        if(type=="apt") {
        	for(int i=0; i<nList.getLength(); i++) {
                Node nNode=nList.item(i);
                if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element eElement=(Element) nNode;
                    System.out.println("건축년도  : " + getTagValue("건축년도", eElement));
            		System.out.println("아파단지   : #" + getTagValue("법정동", eElement)+"#");
            		System.out.println("법정동  : " + getTagValue("보증금액", eElement));
            		System.out.println("보증금 : " + getTagValue("아파트", eElement));
            		System.out.println("월세 금 : #" + getTagValue("월세금액", eElement)+"#");
            		System.out.println("전용 면적  : " + getTagValue("전용면적", eElement));
            		System.out.println("지번  : " + getTagValue("지번", eElement));
            		System.out.println("지역코드  : " + getTagValue("지역코드", eElement));
            		System.out.println("층  : " + getTagValue("층", eElement));
                }
            }
   	}else if(type=="offi") {
   		for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;

                System.out.println("오건축년도  : " + getTagValue("건축년도", eElement));
        		System.out.println("법정동   : #" + getTagValue("법정동", eElement)+"#");
        		System.out.println("보증금  : " + getTagValue("보증금", eElement));
        		System.out.println("단지 : " + getTagValue("단지", eElement));
        		System.out.println("월세 : #" + getTagValue("월세", eElement)+"#");
        		System.out.println("전용 면적  : " + getTagValue("전용면적", eElement));
        		System.out.println("지번  : " + getTagValue("지번", eElement));
        		System.out.println("지역코드  : " + getTagValue("지역코드", eElement));
        		System.out.println("층  : " + getTagValue("층", eElement));
            }
        }
   	}else if(type=="rh") {
   		for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;
                
                System.out.println("----------");
        		System.out.println("연건축년도  : " + getTagValue("건축년도", eElement));
        		System.out.println("법정동   : *" + getTagValue("법정동", eElement)+"*");
        		System.out.println("보증금액  : " + getTagValue("보증금액", eElement));
        		System.out.println("연립다세대 : " + getTagValue("연립다세대", eElement));
        		System.out.println("월세 금액 : *" + getTagValue("월세금액", eElement)+"*");
        		System.out.println("전용 면적  : " + getTagValue("전용면적", eElement));
        		System.out.println("지번  : " + getTagValue("지번", eElement));
        		System.out.println("지역코드  : " + getTagValue("지역코드", eElement));
        		String x=getTagValue("층", eElement);
        		if(x!=null) {System.out.println("층  : " + x);}
            }
        }
   	}

    }
    
    // 태그 값을 읽어올 함수
    private static String getTagValue(String tag, Element eElement) {
        Node nValue=null;
        
        NodeList x= eElement.getElementsByTagName(tag);
        Node test=x.item(0);
        NodeList t=null;
        if(test!=null) {
        t= test.getChildNodes();
        
        if((Node)t.item(0)!=null) {nValue=(Node)t.item(0);}}
        if(nValue==null) return null;
        return nValue.getNodeValue();
    } 
}
