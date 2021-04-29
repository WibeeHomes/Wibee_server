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

public class ApiExplorer {
    public void apiPrint(String type) throws IOException, ParserConfigurationException, SAXException {
    	
    	StringBuilder urlBuilder = null;
    	if(type=="apartment") {
       		 urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent"); /*URL*/
    	}else if(type=="office") {
    		 urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiRent"); /*URL*/
    	}else if(type=="alliance") {
    		urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent"); /*URL*/
    	}
       

		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=jIVfPPerTxZa7ALt9Ws0Pqk1Qj%2BLLhIsod0B3riK6Z19GUjvZzISKbUv9havnLdKDc8nRbbCjHmzoXLO11KtSg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode("11110", "UTF-8")); /*각 지역별 코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("201512", "UTF-8")); /*월 단위 신고자료*/
        URL url = new URL(urlBuilder.toString());
        
        String parsingUrl="";
        parsingUrl=url.toString();

        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(parsingUrl);

        doc.getDocumentElement().normalize();

        NodeList nList=doc.getElementsByTagName("item");
        System.out.println("파싱할 리스트 수 : "+ nList.getLength());
        
        if(type=="apartment") {
        	for(int i=0; i<nList.getLength(); i++) {
                Node nNode=nList.item(i);
                if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element eElement=(Element) nNode;
                    System.out.println("######################");
            		//System.out.println(eElement.getTextContent());
            		System.out.println("건축년도  : " + getTagValue("건축년도", eElement));
            		System.out.println("단지   : #" + getTagValue("법정동", eElement)+"#");
            		System.out.println("법정동  : " + getTagValue("보증금액", eElement));
            		System.out.println("보증금 : " + getTagValue("아파트", eElement));
            		System.out.println("월세 금 : #" + getTagValue("월세금액", eElement)+"#");
            		System.out.println("전용 면적  : " + getTagValue("전용면적", eElement));
            		System.out.println("지번  : " + getTagValue("지번", eElement));
            		System.out.println("지역코드  : " + getTagValue("지역코드", eElement));
            		System.out.println("층  : " + getTagValue("층", eElement));
                }
            }
   	}else if(type=="office") {
   		for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;
                System.out.println("######################");
        		//System.out.println(eElement.getTextContent());
        		System.out.println("건축년도  : " + getTagValue("건축년도", eElement));
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
   	}else if(type=="alliance") {
   		for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;
                System.out.println("######################");
        		//System.out.println(eElement.getTextContent());
        		System.out.println("건축년도  : " + getTagValue("건축년도", eElement));
        		System.out.println("법정동   : *" + getTagValue("법정동", eElement)+"*");
        		System.out.println("보증금액  : " + getTagValue("보증금액", eElement));
        		System.out.println("연립다세대 : " + getTagValue("연립다세대", eElement));
        		System.out.println("월세 금액 : *" + getTagValue("월세금액", eElement)+"*");
        		System.out.println("전용 면적  : " + getTagValue("전용면적", eElement));
        		System.out.println("지번  : " + getTagValue("지번", eElement));
        		System.out.println("지역코드  : " + getTagValue("지역코드", eElement));
        	//	if(getTagValue("층", eElement)!=null) {System.out.println("층  : " + getTagValue("층", eElement));}
            }
        }
   	}


        
     // 선별진료소 api와 통신 및 xml파싱
        /*HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());*/
    }
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList=eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue=(Node)nlList.item(0);
        if(nValue==null) return null;
        return nValue.getNodeValue();
    } // 태그 값을 읽어올 함수
}
