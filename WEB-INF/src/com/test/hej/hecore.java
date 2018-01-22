package com.test.hej;

import java.util.Iterator;

import org.directwebremoting.json.types.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class hecore {
    
	public static void main(String[] args) {
		String result="SDE.HH:LINE,SDE.KGTEO:LINE,SDE.XG1998:POINT,SDE.XG1999:LINE,SDE.XGTEST:POINT,SDE.XGTEXT:LINE,SDE.ZG0:LINE,SDE.ZG1999:POINT";
		//MsgCompare(result);
		String ck="[{\"ID\":41},{\"ID\":42}]";
		ck(ck);
		System.out.println("over");
	}
	public static void ck(String ck){
		//JSONObject jo=JSONObject.fromObject(ck);
		//System.out.println(jo.get(1));
		JSONArray ja=JSONArray.fromObject(ck);
		System.out.println(ja.size());
		for (int i = 0; i < ja.size(); i++) {
			JSONObject jo=ja.getJSONObject(i);
			Iterator<String> it=jo.keys();
			while (it.hasNext()) {
    			String field=it.next();	        	     
    	        String value=jo.getString(field);
                System.out.println(field+"=="+value);
    		} 		
		}
	}
	//SDE.HH:LINE,SDE.KGTEO:LINE,SDE.XG1998:POINT,SDE.XG1999:LINE,SDE.XGTEST:POINT,SDE.XGTEXT:LINE,SDE.ZG0:LINE,SDE.ZG1999:POINT
	public static void MsgCompare(String result) {
		// TODO Auto-generated method stub
        String res[]=result.split(",");
        for (int i = 0; i < res.length; i++) {
            String t[]=res[i].split(":");
            String v[]=t[0].split("\\.");
            //System.out.println("good");
            System.out.println("result=="+t[1]+"=="+v[1]);
		}
	}
}
