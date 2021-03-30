package com.example.demo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DemoApiController {
	static Calendar cal = Calendar.getInstance();
	Date time=new Date();
	static int year = cal.get(Calendar.YEAR);
	static int month = cal.get(Calendar.MONTH) + 1;
	static int day = cal.get(Calendar.DAY_OF_MONTH);
	static String cur_date="";
	SimpleDateFormat format1=new SimpleDateFormat("HH:mm");
	String current=format1.format(time);

	@GetMapping("/hello")
	public String hello() {
		return "안녕";
	}
	@RequestMapping(value="/Program_Character", headers= {"Accept=application/json"})
	public HashMap<String, Object> Program_Character(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String program="";
		String file="C:\\Users\\Danbi\\Desktop\\"+program+"_Character"+".txt";
		String url=""; 
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String last_modify=c.getLastModify(file);
		// 크롤링을 오늘 한번도 안 했을 때
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			
			HashMap<String,Object> action = (HashMap<String, Object>)params.get("action");
			System.out.println("action : "+action);
			
	        HashMap<String, Object> params1=(HashMap<String, Object>) action.get("params");
	        System.out.println(params1);
	        Object sys_tv_name=((HashMap<String,Object>) params1).get("sys_tv_name").toString();
	        System.out.println("sys_tv_name : "+sys_tv_name);
	        
	        program=(String) sys_tv_name;
	       
	        program=program.replace("\n", "");
	        file="C:\\Users\\Danbi\\Desktop\\"+program+"_Character"+".txt";
	        
	        System.out.println("program is "+program);
	        System.out.println("file is "+ file); 

			System.out.println("마지막 크롤링 날짜와 오늘 날짜가 다릅니다.");
			data=c.crawlCharacters(program); // 
			System.out.println("data is"+ data);
			c.crawlFileDown(data, file); // 크롤링 파일 다운로드 

			// 크롤링을 했을 때는 파일에서 읽어옴
			System.out.println("날짜 똑같음");
			result=c.FileRead(new File(file));
			
			System.out.println("result +"+result);
			template.put("outputs", outputs);
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/Program_Info", headers= {"Accept=application/json"})
	public HashMap<String, Object> Program_Info(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String program="";
		String file="C:\\Users\\Danbi\\Desktop\\"+program+"_info"+".txt";
		// 크롤링을 오늘 한번도 안 했을 때
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			HashMap<String,Object> action = (HashMap<String, Object>)params.get("action");
			
	        HashMap<String, Object> params1=(HashMap<String, Object>) action.get("params");
	        Object sys_tv_name=((HashMap<String,Object>) params1).get("sys_tv_name").toString();
	        program=(String) sys_tv_name;
	        program=program.replace("\n", "");
	        file="C:\\Users\\Danbi\\Desktop\\"+program+"_info"+".txt";
	        
			data=c.crawlInforProgram(program); // 
			
			c.crawlFileDown(data, file); // 크롤링 파일 다운로드 
			template.put("outputs", outputs);
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_Program", headers= {"Accept=application/json"})
	public HashMap<String, Object> crawl_Program(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String program="";
		String file="C:\\Users\\Danbi\\Desktop\\"+program+".txt";
		String url=""; 
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String last_modify=c.getLastModify(file);
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			
			HashMap<String,Object> action = (HashMap<String, Object>)params.get("action");
			System.out.println("action : "+action);
			
	        HashMap<String, Object> params1=(HashMap<String, Object>) action.get("params");
	        System.out.println(params1);
	        Object sys_tv_name=((HashMap<String,Object>) params1).get("sys_tv_name").toString();
	        System.out.println("sys_tv_name : "+sys_tv_name);
	        
	        //System.out.println(value);
	        program=(String) sys_tv_name;
	       
	        program=program.replace("\n", "");
	        file="C:\\Users\\Danbi\\Desktop\\"+program+".txt";
	        
			
			data=c.crawlProgram(program); // 

			template.put("outputs", outputs);
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("json load failed");
		}
		return resultJson;
	}

	@RequestMapping(value="/crawl_SBS", headers= {"Accept=application/json"})
	public HashMap<String, Object> SBS(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\SBS.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=5100&key_depth2=14&key_depth3=";
		String last_modify=c.getLastModify(file);
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			 
			HashMap<String,Object> action = (HashMap<String, Object>)params.get("action");
			// 크롤링을 오늘 한번도 안 했을 때
			HashMap<String, Object> params1=(HashMap<String, Object>) action.get("params");
	        System.out.println(params1);
	        Object sys_tv_name=((HashMap<String,Object>) params1).get("sys_tv_name");
	        
			 data=c.crawlPairing(url); // 
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
		}
		catch(Exception e) {
			
			System.out.println("json load failed");
		}
		return resultJson;
	}
	
	@RequestMapping(value="/crawl_KBS2", headers= {"Accept=application/json"})
	public HashMap<String, Object> KBS2(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\KBS2.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=5100&key_depth2=12&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			
			data=c.crawlPairing(url); //
			
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_tvn", headers= {"Accept=application/json"})
	public HashMap<String, Object> tvn(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\tvn.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=7600&key_depth2=&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
		
			data=c.crawlPairing(url);
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
	        System.out.println(utter);
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_sbsf", headers= {"Accept=application/json"})
	public HashMap<String, Object> sbsf(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\sbsf.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=7600&key_depth2=882&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			
			// 크롤링을 오늘 한번도 안 했을 때
			
			data=c.crawlPairing(url); //
			
			// 크롤링을 했을 때는 파일에서 읽어옴
			System.out.println("날짜 똑같음");
			
			//resultJson.put("")
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
	        //result=trans.translate(utter);
	        //text.put("text", data);
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_jtbc2", headers= {"Accept=application/json"})
	public HashMap<String, Object> jtbc2(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\jtbc2.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=7600&key_depth2=874&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			
			// 크롤링을 오늘 한번도 안 했을 때
			
			data=c.crawlPairing(url); //
			
			//resultJson.put("")
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
	        //result=trans.translate(utter);
	        //text.put("text", data);
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_mbcev", headers= {"Accept=application/json"})
	public HashMap<String, Object> mbcev(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\mbcev.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=7600&key_depth2=881&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			
			// 크롤링을 오늘 한번도 안 했을 때
			
			data=c.crawlPairing(url); //
			
			
			
			//resultJson.put("")
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
	@RequestMapping(value="/crawl_MBC", headers= {"Accept=application/json"})
	public HashMap<String, Object> MBC(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> resultJson=new HashMap<>();
		crawl c=new crawl();
		String data = "";
		String result="";
		String file="C:\\Users\\Danbi\\Desktop\\MBC.txt";
		cur_date+=(year);
		cur_date+=(month);
		cur_date+=(day);
		String url="https://m.skbroadband.com/content/realtime/Channel_List.do?key_depth1=5100&key_depth2=13&key_depth3=";
		String last_modify=c.getLastModify(file);
			
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonInString=mapper.writeValueAsString(params);
	        
			List<HashMap<String, Object>> outputs=new ArrayList<>();
			HashMap<String, Object> template=new HashMap<>();
			HashMap<String, Object> simpleText=new HashMap<>();
			HashMap<String, Object> text=new HashMap<>();
			template.put("outputs", outputs);
			
			data=c.crawlPairing(url); // 
			
			//resultJson.put("")
			resultJson.put("version","2.0");
			resultJson.put("template", template);	
			
			text.put("text", data);
	        simpleText.put("simpleText", text);
			outputs.add(simpleText);
			
			HashMap<String,Object> userRequest = (HashMap<String, Object>)params.get("userRequest");
			System.out.println("userRequest"+userRequest);
	        String utter = userRequest.get("utterance").toString();
		}
		catch(Exception e) {
			System.out.println("json load failed");
		}
		return resultJson;
	}
}

