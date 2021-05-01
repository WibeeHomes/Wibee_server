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
	
	//API 파싱해서 DB에 저장하는 함수
    public void apiDB(String type, String loc, String time) throws IOException, ParserConfigurationException, SAXException {
    	
    	home addHome=new home();
    	double[] xy=new double[2];
    	ConnectDB connectDB = ConnectDB.getInstance();
    	
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
        
        System.out.println("파싱할 리스트 수 : "+ nList.getLength()+loc+" "+type);
 
        for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;
                
                String hYear=getTagValue("건축년도", eElement);
                if(hYear!=null)addHome.hYear=hYear;// 집 건축년도
            	String floor=getTagValue("층", eElement).trim();
            	if(floor!=null)addHome.hFloor= floor; //층수
            	addHome.hArea=getTagValue("전용면적", eElement); // 면적
            	addHome.addDong=getTagValue("법정동", eElement).trim();// 주소-법정동
            	addHome.addJibun=getTagValue("지번", eElement);// 지번
            	            	
            	if(type=="apt") {
            		addHome.hName= getTagValue("아파트", eElement).trim(); // 집 이름
            		addHome.warFee =Integer.parseInt(getTagValue("보증금액", eElement).trim().replaceAll(",",""));// 보증금
            		addHome.renFee=Integer.parseInt(getTagValue("월세금액", eElement).trim()); // 월세
                	addHome.hCate=0; //카테고리(아파트, 연립주택, 오피스텔)
            	}
            	else if(type=="offi") {
            		addHome.hName= getTagValue("단지", eElement).trim(); // 집 이름
            		addHome.warFee =Integer.parseInt(getTagValue("보증금", eElement).trim().replaceAll(",",""));// 보증금
            		addHome.renFee=Integer.parseInt(getTagValue("월세", eElement).trim()); // 월세
                	addHome.hCate=1; //카테고리(아파트, 연립주택, 오피스텔)
            	}
            	else if(type=="rh") {
            		addHome.hName= getTagValue("연립다세대", eElement).trim(); // 집 이름
            		addHome.warFee =Integer.parseInt(getTagValue("보증금액", eElement).trim().replaceAll(",",""));// 보증금
                	addHome.renFee=Integer.parseInt(getTagValue("월세금액", eElement).trim()); // 월세
                	addHome.hCate=2; //카테고리(아파트, 연립주택, 오피스텔)
            	}
            	xy=calXY(addHome.addDong+addHome.addJibun);
                addHome.pointX=xy[1];//(y,x) 형식??
                addHome.pointY=xy[0];
            }
            //addHome.printHome();
            //DB에 추가
           if(addHome.pointX!=0) {
 
        	   //전세 지역코드+a, 월세 지역코드+b
        	   if(addHome.renFee==0)connectDB.homeInsert(addHome,"a"+loc);
        	   else connectDB.homeInsert(addHome,"b"+loc);
           }
        }

    }
    
    // 주소를 이용 현재 경도와 위도를 가져오는 api 이용함수   
    public double[] calXY(String name) throws IOException, ParserConfigurationException, SAXException {
        String parsingUrl="";
        String key= "340FCCC5-C1C9-31D4-B7D8-56BC7558298A";
        double xy[]=new double[2];
        
        StringBuilder urlBuilder = new StringBuilder("http://api.vworld.kr/req/search?"); /*URL*/
        urlBuilder.append(URLEncoder.encode("service","UTF-8") + "="+URLEncoder.encode("search", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("request","UTF-8") + "="+ URLEncoder.encode("search", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode("2.0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(name, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("address", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("category","UTF-8") + "=" + URLEncoder.encode("parcel", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("errorformat","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("key","UTF-8") + "=" + URLEncoder.encode(key, "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        parsingUrl=url.toString();
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
        Document doc=dBuilder.parse(parsingUrl);
        doc.getDocumentElement().normalize();

        NodeList nList=doc.getElementsByTagName("item"); //장소 전체 노드

        for(int i=0; i<nList.getLength(); i++) {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element eElement=(Element) nNode;
               
                xy[0]=Double.parseDouble(getTagValue("x",eElement));
                xy[1]=Double.parseDouble(getTagValue("y",eElement));
            }
        }
        return xy;
    }
    
    // 태그 값을 읽어올 함수
    private static String getTagValue(String tag, Element eElement) {
        Node nValue=null;
        
        NodeList x= eElement.getElementsByTagName(tag);
        Node test=x.item(0);
        NodeList t=null;
        if(test!=null) {
        	t= test.getChildNodes();
        	if((Node)t.item(0)!=null) {nValue=(Node)t.item(0);}
        }
        if(nValue==null) return null;
        return nValue.getNodeValue();
    }

}
