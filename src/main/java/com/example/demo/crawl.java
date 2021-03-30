package com.example.demo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class crawl{
	static FileInputStream fin = null; 
	static InputStreamReader in = null;
	static Calendar cal = Calendar.getInstance();
	static int year = cal.get(Calendar.YEAR);
	static int month = cal.get(Calendar.MONTH) + 1;
	static int day = cal.get(Calendar.DAY_OF_MONTH);
	static String cur_date="";
	static Date time=new Date();
	static SimpleDateFormat format1=new SimpleDateFormat("HH:mm");
	static SimpleDateFormat format2=new SimpleDateFormat("HH시mm");
	static String current=format1.format(time);
	static String current2=format2.format(time);
	public static String crawlPairing(String url) {
		Document doc;
		Elements elem = null;
		Elements cont=null;
		String str = "";	
		
		try {
			str="🗓"+month+"월"+day+"일 편성표 📆\n\n";
			doc=Jsoup.connect(url).get();
			elem=doc.select("div.cmm_boxs");
			for(Element e: elem.select("ol li")) {
				Elements flag_box = e.select("span.flag_box");
				flag_box.remove();
				Elements time=e.select("p.time"); 
				// 미래 프로그램
				if(time.text().compareTo(current)>0) 
				{
					if(e.className().equals("list on")) continue;
					str+=(e.text());
					str+="\n";
				}
				if(e.className().equals("list on")) { // 방송중
					Elements bar_wrap=e.select("div.bar_wrap");
					bar_wrap.remove();
					str+=("😂🔛🤣 "+e.text());
					str+="\n";
					str+="😏🔜🤭";
				}
				System.out.println(e.text());				
			}
			System.out.println(str);	
			return str;	
		}			
			 // 파일 다운로드 업데이트
		catch(Exception e) {
			return str;
		}
		}
	public static String crawlInforProgram(String program) throws UnsupportedEncodingException {
		Document doc;
		Elements cm_info_box = null;
		String program_info="";
	
		try {
			System.out.println("편성표 정보 알려주는 함수로 들어감");
			String url="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+program;
			System.out.println("program is "+program);
			System.out.println("url is +"+url);
			doc = Jsoup.connect(url).get();
			cm_info_box =doc.select("div.cm_info_box");
			for(Element e:cm_info_box.select("div.detail_info div")) {// 전체
				if(e.hasClass("text_expand")&&e.hasClass("_ellipsis")&&e.hasClass("_img_ellipsis")) {
				Elements button=e.select("button");
				button.remove();
				program_info=e.text();
				program_info+=" \n\n";
				program_info+="함께 만나 보실래요 ??? 🌝🌞☺ 👉🏼👈🏼👀😊🤗";
				}
				else {
					System.out.println("하이!!");
				}
				
			}
		}
		catch(Exception e) {
				System.out.println(e);
			}	
		return program_info;
	}  
	public static String crawlCharacters(String program) throws UnsupportedEncodingException {
		Document doc;
		Elements cm_tab_info_box = null;
		String characters="";
		int i=0;
		try {
			System.out.println("등장인물 정보 알려주는 함수로 들어감");
			String url="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+program+"%EC%B6%9C%EC%97%B0%EC%A7%84";
			System.out.println("program is "+program);
			System.out.println("url is +"+url);
			doc = Jsoup.connect(url).get();
			cm_tab_info_box =doc.select("div.cm_tab_info_box");
			characters+=program+" 프로그램에선 \n";
			for(Element e:cm_tab_info_box.select("ul li div.title_box")) {// 전체
				System.out.println(e.text());
				// 예능 프로그램은 strong 태그에 연예인 이름이 있고, span 태그에 진행, 출연 단어가 있음
				// 드라마는 strong 태그에 역할 이름이 있고, span 배우 이름이 있기 때문에 필요한 코드 
				Elements strong=e.select("strong");
				strong.text();
				Elements span=e.select("span");
				// 드라마일 떄
				if(span.text().contains("진행")||span.text().contains("출연")) characters+="'"+strong.text()+"' ";
				// 예능일 때
				else characters+="'"+span.text()+"' ";
				if(i>4) break;
				System.out.println();
				i+=1;
			}
			characters+="👨‍👨\n\n"; 
			characters+="등이 출연합니다~ 👨‍👨‍👦‍👦 ‍ \n 어때요?! 더더욱 "+program+" 보고 싶으시죠 ?? 🤭😁🧐";
		}
		catch(Exception e) {
				System.out.println(e);
			}	
		return characters;
	}
	public static String crawlProgram(String program) throws UnsupportedEncodingException {
		Document doc;
		Elements cm_content_wrap = null;
		
		String str = "";
		String[] date=new String[100];
		String[] channel=new String[100];
		
		String [] data=new String[30]; // 데이터 넣기
	
		try {
			System.out.println("프로그램 입력하면 편성표 나오는 함수로 들어감");
			String url="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+program+"%ED%8E%B8%EC%84%B1%ED%91%9C";
			System.out.println("program is "+program);
			System.out.println("url is +"+url);
			doc = Jsoup.connect(url).get();
			
			cm_content_wrap =doc.select("div.pack_group");
				
			int j=0, k=0, y=0;
			for(Element e:cm_content_wrap.select("div.table_scroll_wrap")) {// 전체
				if(e.hasClass("_scroll")) {
					for(Element e_channel:e.select("div.table_top_area>table.cm_table>thead>tr>th")) { // 채널
							if(channel[e_channel.elementSiblingIndex()]==null) channel[e_channel.elementSiblingIndex()]=e_channel.text();
							else channel[e_channel.elementSiblingIndex()]+=e_channel.text();
							System.out.println(channel[e_channel.elementSiblingIndex()]+e_channel.elementSiblingIndex());
						}
						for(Element e_date:e.select("div.table_body_area>table.cm_table>tbody>tr")) {
							Elements number=e_date.select("span.number");
							number.remove();
							Element first=e_date.firstElementSibling();
							System.out.println(first);
							for(Element f:first.select("td.align_left")) {
								if(date[f.elementSiblingIndex()]==null) date[f.elementSiblingIndex()]=f.text();
								else date[f.elementSiblingIndex()]+=f.text();

							}
							j+=1;
							if(j==1) break;
						}	
					}	
				}
			
			str="오늘의 "+program+" 편성표를 알려드릴게요 !! 👩🏿‍💻 \n\n";
			for(int i=0; date[i]!=null; i++) {
				System.out.println(i);
				if(!date[i].equals("")) str+="🔊"+channel[i]+"🔊"+"\n"+"⏰"+date[i]+"⏰"+"\n";
				System.out.println(channel[i]+"-"+date[i]);
		}
			return str;
			}
		
		catch(Exception e) {
			System.out.println(e);
			return "오류!!!!";
			
		}
	}
	public static String FileRead(File file) {
		String result = "";
		try {
			fin=new FileInputStream(file);
			in=new InputStreamReader(fin, "UTF-8");
			int c;
			while((c=in.read())!=-1) result+=(char)c;
			
			in.close();
			fin.close();
		}catch(IOException e) {
			return "오류";
		}
		return result;
	}
	public static void crawlFileDown(String data, String file) {
		FileWriter fout=null;
		try {
			fout=new FileWriter(file);
			data=data.toString();
			fout.write(data);
			fout.close();
		}
		catch(IOException e) {
			System.out.println("크롤링 저장 오류");
		}
	}
	public static String getLastModify(String crawl_file) {
		File file = new File(crawl_file);
		long lastModified=file.lastModified();
		String pattern="yyyyMMdd";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
		Date lastModifiedDate=new Date(lastModified);
		String last_modify=simpleDateFormat.format(lastModifiedDate);
		
		return last_modify;
	}
	public static void main(String[] args) {
		String url="";
		String data;
		String channel="";
		String file="";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String last_modify = getLastModify(file);
		System.out.println("last : " + last_modify);
		System.out.println("cur : " + cur_date);
			 if(cur_date.equals(last_modify)==false) {
				 System.out.println("마지막 크롤링 날짜와 오늘 날짜가 다릅니다.");
				 data=crawlPairing(url);
				 crawlFileDown(data, file);
			 }	 
			 else {
				 System.out.println("크롤링 함수를 실행하지 않아도 됩니다~!");
			 }
		
		}
	}
