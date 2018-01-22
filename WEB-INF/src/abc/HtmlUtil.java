
/**
 * @author hecore
 * Avens form tools
 */
package abc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpUtils;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.org.apache.regexp.internal.recompile;

public class HtmlUtil{
	
	/**
	 * function 横标木显示的规范函数
	 * @param Stirng showStr 标目内容
	 * @param boolean canChange  是否可以修改
	 * @param needfill 是否需要提示信息
	 * @param promStr 可以修改时提示重点
	 * @param idStr 
	 * @return
	 */
	public static String getHtagForms(String showStr,boolean canChange,boolean needfill,String promStr,String idStr){
		String tmpShowStr = showStr;
		if(null == tmpShowStr)
			tmpShowStr = "";
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<font class='tagtxt'>");
		strbuf.append(tmpShowStr);
		strbuf.append("</font>");
		if(!canChange)
			return strbuf.toString();
		if(needfill||(null!= promStr&&"".equals(promStr))){
			if(null!=idStr){
				strbuf.append("<div id='ts_");
				strbuf.append(idStr);
				strbuf.append("' style= 'dispaly:none''");
			}
			strbuf.append("<font color=#ff0000>");
			if( null != promStr && !"".equals(promStr))
				strbuf.append(promStr);
			else
				strbuf.append("必填");
			strbuf.append("</font>");
			if(null!=idStr)
				strbuf.append("</div>");
		}
		return strbuf.toString();		
	}
	/**
	 * 获取一个hidden遍历
	 * @param eleid  id name的名称
	 * @param markid id name名称的后缀
	 * @param oldval 缺省值
	 * @return
	 */
	public static String getOneInputHidden(String eleid,String markid,String oldval){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<INPUT TYPE=\"HIDDEN\" name=\"");
		strbuf.append(eleid);
		strbuf.append(markid);
		strbuf.append("\" id= \"");
		strbuf.append(oldval);
		strbuf.append("\">");
		return strbuf.toString();
	}
	
	/**
	 * 
	 * @param inputid  name id的值
	 * @param valStr button按钮显示的值
	 * @param style 显示样式,class属性
	 * @param event 响应事件
	 * @param mode mode=1 灰色 disabled mode=0 其他
	 * @param byStyle 备用样式串
	 * @return
	 */
	public static String getButtonString(String inputid,String valStr,String style,String event,int mode,String byStyle){
		StringBuffer strbuf=new StringBuffer();
		if(inputid==null||"".equals(inputid))
			strbuf.append("<input type='button'");
		else{
			strbuf.append("<input type='button' name=");
			strbuf.append(inputid);
			strbuf.append(" id=");
			strbuf.append(inputid);
		}
		if (style!=null&&"".equals(style)) {
			strbuf.append(" class='");
			strbuf.append(style);
			strbuf.append("'");
		}
		if(event!=null&&"".equals(event)){
			strbuf.append(" onclick=");
			strbuf.append(event);
		}
		strbuf.append(" value=");
		strbuf.append(valStr);
		strbuf.append("'");
		if(mode==1)
			strbuf.append(" disabled />");
		else{
			if (mode==2) {
				strbuf.append(byStyle);
				strbuf.append(" />");
			}		
		}
		return strbuf.toString();
	}
	
	public static String getTextareaForms(int mode,String NameStr,String valStr,String classStr){
		String tempValStr=valStr;
		if(null == tempValStr)
			tempValStr="";
		StringBuffer strbuf=new StringBuffer();
		if (mode == 2) { //代办可修改模式
			strbuf.append("<textarea class='");
			strbuf.append(classStr);
			strbuf.append("' name=");
			strbuf.append(NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append(">");
			strbuf.append(tempValStr);
			strbuf.append("</textarea>");
			return strbuf.toString();
		}
		if(mode==3){ // 已办的黑色显示模式
			strbuf.append("<textatrea class='");
			strbuf.append(classStr);
			strbuf.append("' name=");
			strbuf.append(NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append(" readOnly>");
			strbuf.append(tempValStr);
			strbuf.append("</textarea>");
			return strbuf.toString();
		}
		if(mode==1){//cannot modify gray style.
			strbuf.append("<textatrea class='");
			strbuf.append(classStr);
			strbuf.append("' style='visibility:hidden;height:1px;' name='");
			strbuf.append(NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append(" readOnly>");
			strbuf.append(tempValStr);
			strbuf.append("</textarea>");
			return strbuf.toString();
		}
		return "";		
	}
	
	public static String getTextTimeForms(int mode,String NameStr,String valStr,String classStr){
		String tempValStr=valStr;
		if(null==tempValStr)//防御性编程
			tempValStr = "";
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<input type=text class='");
		strbuf.append(classStr);
		strbuf.append("' maxlength='2' name=");
		strbuf.append(NameStr);
		strbuf.append(" id=");
		strbuf.append(NameStr);
		strbuf.append(" value='");
		strbuf.append(tempValStr);
		if(mode == 3)//已办的黑色显示
			strbuf.append(" readOnly");
		else if(mode==1)
			strbuf.append(" disabled");
		else if(mode==2)
			strbuf.append("'>");
		return strbuf.toString();
	}
	
	public static String getFontForms(String nameStr,String style,String tsMessage){
		StringBuilder strbuf=new StringBuilder();
		strbuf.append("<font id=");
		strbuf.append(nameStr);
		strbuf.append(" style='");
		strbuf.append(style);
		strbuf.append("'>");
		strbuf.append(tsMessage);
		strbuf.append("</font>");
		return strbuf.toString();
	}
	
	public static String getDateShowString(Date rqObj,String dateFormat,boolean cannotchange){
		String ret="";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
			if(rqObj!=null)
				ret=sdf.format(rqObj);
			else{
				if(cannotchange)
					ret="";
				else 
					ret=sdf.format(new Date());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String getDateForms(String NameStr,String valStr,int mode,String classStr,String rClassStr){
		if(mode==3)
			return HtmlUtil.getReadOnlyForms(NameStr,valStr,rClassStr);
		if(mode==2){
			StringBuilder strbuf=new StringBuilder();
			strbuf.append("<input type=text ");
			if(classStr!=null&&!"".equals(classStr)){
				strbuf.append(" class='");
				strbuf.append(classStr);
				strbuf.append("'");
			}
			strbuf.append(" name=");
			strbuf.append(NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append(" value='");
			strbuf.append(valStr);
			strbuf.append("' readOnly>");
			strbuf.append("<script language=\"JavaScript\">document.write(getanchorhref('");
			strbuf.append(NameStr);
			strbuf.append("'));</script>");
			return strbuf.toString();
		}
		return "";
	}
	
	public static String getRadioInputString(String[] selidval,String[] selshowval,String oldVal,String inputId,String onclickName,int onelinenumber,String classStr){
		int xxsl=selidval.length;
		StringBuffer strbuf=new StringBuffer();
		for (int i = 0; i < selshowval.length; i++) {
			if(i%onelinenumber == 0){//奇数、偶数
				if(i!=0)
					strbuf.append("<br>");
			}else{
				strbuf.append("&nbsp,&nbsp,&nbsp;");
			}
			if(null == onclickName){
				strbuf.append("<input type='radio' name=");
			}else{
				strbuf.append("<inpt type='radio' onclick='");
				strbuf.append(onclickName);
				strbuf.append("' name=");
			}
			strbuf.append(inputId);
			strbuf.append(" value=");
			strbuf.append(selidval[i]);
			if(oldVal!=null && oldVal.equals(selidval))
				strbuf.append(" checked");
			strbuf.append(" ><font ");
			if(classStr!=null &&!"".equals(classStr)){
				strbuf.append(" class='");
				strbuf.append(classStr);
				strbuf.append("'");
			}
			strbuf.append(">");
			strbuf.append(selshowval[i]);
			strbuf.append("</font>&nbsp");			
		}
		return strbuf.toString();
	}
	
	public static String getSelectInputString(String[] selval,String[] selid,String nameStr,String valStr){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<select name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		strbuf.append(">");
		int xzelen=selval.length;
		if(xzelen>=1)
			strbuf.append("<option value='1'>--请选择--</option>");
		for (int i = 0; i < xzelen; i++) {
			String xzValStr= selval[i];
			String xzIdStr = selid[i];
			strbuf.append("<option value=");
			strbuf.append(xzIdStr);
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selectd>");
			}else{
				strbuf.append(" >");			
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("</selected>");
		return strbuf.toString();
	}
	
	public static String getSelectInputString(String[] selval,String[] selid,String nameStr,String valStr,String widthStyle){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<select name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		strbuf.append(widthStyle);
		strbuf.append(">");
		int xzelen=selval.length;
		if(xzelen>=1)
			strbuf.append("<option value='1'>--请选择--</option>");
		for (int i = 0; i < xzelen; i++) {
			String xzValStr= selval[i];
			String xzIdStr = selid[i];
			strbuf.append("<option value=");
			strbuf.append(xzIdStr);
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selectd>");
			}else{
				strbuf.append(" >");			
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("</selected>");
		return strbuf.toString();
	}
	
	public static String getSelectInputString(String[] selval,String[] selid,String nameStr,String valStr,String widthStyle,String event){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<select name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		strbuf.append(widthStyle);
		strbuf.append(" onChange=");
		strbuf.append(event);
		strbuf.append(">");
		int xzelen=selval.length;
		if(xzelen>=1)
			strbuf.append("<option value='1'>全部</option>");
		for (int i = 0; i < xzelen; i++) {
			String xzValStr= selval[i];
			String xzIdStr = selid[i];
			strbuf.append("<option value=");
			strbuf.append(xzIdStr);
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selectd>");
			}else{
				strbuf.append(" >");			
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("</selected>");
		return strbuf.toString();
	}
	
	public static String gertSelectString(String[] selval,String[] selid,String nameStr,String valStr){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<selectd name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		strbuf.append(">");
		int xzlen=selval.length;
		if(xzlen>=1)
			strbuf.append("<option value='-1'>--请选择--</option>");
		for (int i = 0; i < xzlen; i++) {
			String xzValStr=selval[i];
			String xzIdStr=selid[i];
			strbuf.append("<option value=");
			strbuf.append(" >");
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selected");
			}else{
				strbuf.append(" >");
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("<select>");
		return strbuf.toString();
	}
	
	public static String gertSelectString(String[] selval,String[] selid,String nameStr,String valStr,String widthStyle){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<selectd name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		strbuf.append(widthStyle);
		strbuf.append(">");
		int xzlen=selval.length;
		if(xzlen>=1)
			strbuf.append("<option value='-1'>--请选择--</option>");
		for (int i = 0; i < xzlen; i++) {
			String xzValStr=selval[i];
			String xzIdStr=selid[i];
			strbuf.append("<option value=");
			strbuf.append(" >");
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selected");
			}else{
				strbuf.append(" >");
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("<select>");
		return strbuf.toString();
	}
	
	public static String gertSelectString(String[] selval,String[] selid,String nameStr,String valStr,String style,String event){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<selectd name=");
		strbuf.append(nameStr);
		strbuf.append(" id=");
		strbuf.append(nameStr);
		if (style!=null&&"".equals(style)) {
			strbuf.append(style);
		}
		if(event !=null&&!"".equals(event)){
			strbuf.append(" onChange=");
			strbuf.append(event);
		}
		strbuf.append(" >");
		int xzlen=selval.length;
		if(xzlen>=1)
			strbuf.append("<option value='-1'>--请选择--</option>");
		for (int i = 0; i < xzlen; i++) {
			String xzValStr=selval[i];
			String xzIdStr=selid[i];
			strbuf.append("<option value=");
			strbuf.append(" >");
			if(xzIdStr.equals(valStr)){
				strbuf.append(" selected");
			}else{
				strbuf.append(" >");
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("<select>");
		return strbuf.toString();
	}
	
	/**
	 * 没有头，入 --1请选择 --1 全部
	 */
	public static String getSelectStringNoHead(String[] selval,String[] selid,String name,String oldVal,String style,String event){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<selectd name=");
		strbuf.append(name);
		strbuf.append(" id=");
		strbuf.append(name);
		if(style !=null && !"".equals(style))
			strbuf.append(style);
		if(event!=null&&!"".equals(event)){
			strbuf.append(" onChange=");
			strbuf.append(event);
		}
		strbuf.append(" >");
		int xzlen =selval.length;
		for (int i = 0; i < xzlen; i++) {
			String xzValStr=selval[i];
			String xzIdStr=selid[i];
			strbuf.append("<option value=");
			strbuf.append(" >");
			if(xzIdStr.equals(oldVal)){
				strbuf.append(" selected");
			}else{
				strbuf.append(" >");
			}
			strbuf.append(xzValStr);
			strbuf.append("</option>");
		}
		strbuf.append("<select>");
		return strbuf.toString();
	}
	
	public static String formsMan(String oldValStr,String fieldName,String classStr){
		return formsMan(oldValStr,fieldName,false,classStr);
	}
	
	public static String formsMan(String oldValStr,String fieldName){
		return formsMan(oldValStr,fieldName,false);
	}
	
	public static String formsMan(String oldValStr,String fieldName,boolean ifMoreSelect){
		if(oldValStr==null)
			oldValStr="";
		String avensFieldName="avens_"+fieldName;
		String temp="";
		StringBuffer strbuf=new StringBuffer();
		if(ifMoreSelect){
			strbuf.append(HtmlUtil.getTextareaForms(3, avensFieldName, temp, "avens_textarea1"));
		}else{
			strbuf.append("<input type=text class='avens_performer' name=");
			strbuf.append(avensFieldName);
			strbuf.append(" id=");
			strbuf.append(avensFieldName);
			strbuf.append(" value='");
			strbuf.append(temp);
			strbuf.append("'readonly='readonly' style='background-color:#ECE9DB'");
		}
		strbuf.append("<input type=hidden class='avens_performer' name=");
		strbuf.append(fieldName);
		strbuf.append(" id=");
		strbuf.append(fieldName);
		strbuf.append(" value=");
		strbuf.append(oldValStr);
		strbuf.append("' readOnly>");
		return strbuf.toString();
	}
	
	public static String formsMan(String oldValStr,String fieldName,boolean ifMoreSelect,String classStr){
		if(oldValStr==null)
			oldValStr="";
		String avensFieldName="avens_"+fieldName;
		String temp="";
		StringBuffer strbuf=new StringBuffer();
		if(ifMoreSelect){
			strbuf.append(HtmlUtil.getTextareaForms(3, avensFieldName, temp, "avens_textarea1"));
		}else{
			strbuf.append("<input type=text");
			if(classStr!=null&&!"".equals(classStr)){
				strbuf.append(" class='");
				strbuf.append(classStr);
				strbuf.append("'");
			}
			strbuf.append(" name=");		
			strbuf.append(avensFieldName);
			strbuf.append(" id=");
			strbuf.append(avensFieldName);
			strbuf.append(" value='");
			strbuf.append(temp);
			strbuf.append("' readonly/>");
		}
		strbuf.append("<input type=hidden name=");
		strbuf.append(fieldName);
		strbuf.append(" id=");
		strbuf.append(fieldName);
		strbuf.append(" value=");
		strbuf.append(oldValStr);
		strbuf.append("' readOnly>");
		return strbuf.toString();
	}
	
	public static String DISABLEDFORMSDEFAULTMARK="dfshow_";
	
	public static String getDisabledForms(String NameStr,String valStr,String classStr){
		return getDisabledForms(NameStr,valStr,valStr,null,DISABLEDFORMSDEFAULTMARK,classStr);
	}
	
	public static String getDisabledForms(String NameStr,String valStr,String markStr,String classStr){
		return getDisabledForms(NameStr,valStr,valStr,null,markStr,classStr);
	}
	
	public static String getDisabledForms(String NameStr,String idValStr,String showValStr,String markStr,String classStr){
		return getDisabledForms(NameStr,showValStr,null,markStr,classStr);
	}
	
	public static String  getDisabledForms(String NameStr,String idValStr,String showValStr,String unitStr,String markStr,String classStr){
		String tmpIdValStr=idValStr;
		if(null==tmpIdValStr)
			tmpIdValStr="";
		String tmpShowStr=showValStr;
		if(null==tmpShowStr)
			tmpShowStr="";
		//得到灰掉的<input>元素的id值
		StringBuffer idbuf=new StringBuffer();
		idbuf.append(markStr);
		idbuf.append(NameStr);
		String dt_NameStr= idbuf.toString();
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<input type=text class='");
		strbuf.append(classStr);
		strbuf.append("' name=");
		strbuf.append(dt_NameStr);
		strbuf.append("' value='");
		strbuf.append(dt_NameStr);
		strbuf.append("' disabled>");
		strbuf.append("<input type=HIDDEN name=");
		strbuf.append(NameStr);
		strbuf.append(" value='");
		strbuf.append(tmpIdValStr);
		strbuf.append("'>");
		if(unitStr!=null)
			strbuf.append(unitStr);
		return strbuf.toString();
	}
	
	public static String getReadOnlyForms(String NameStr,String valStr,String classStr){
		return getReadOnlyForms(NameStr, valStr, valStr,DISABLEDFORMSDEFAULTMARK,null,classStr);
	}
	
	public static String getReadOnlyForms(String NameStr,String valStr,String unitStr,String classStr){
		return getReadOnlyForms(NameStr, valStr, valStr,DISABLEDFORMSDEFAULTMARK,unitStr,classStr);
	}
	
	public static String getReadOnlyForms(String NameStr,String idvalStr,String showValStr,String markStr,String unitStr,String classStr){
		String tmpIdValStr=idvalStr;
		if(null==tmpIdValStr)
			tmpIdValStr="";
		String tmpShowStr=showValStr;
		if(null==tmpShowStr)
			tmpIdValStr="";
		StringBuffer strbuf=new StringBuffer();
		if(tmpIdValStr.equals(tmpShowStr)){
			strbuf.append("<input type=text class='");
			strbuf.append(classStr);
			strbuf.append("' name=");
			strbuf.append(NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append("'");
			strbuf.append(" readOnly");
			strbuf.append(">");
		}else{
			StringBuffer idbuf=new StringBuffer();
			idbuf.append(markStr);
			idbuf.append(NameStr);
			String dt_NameStr=idbuf.toString();
			strbuf.append("<input type=text class='");
			strbuf.append(NameStr);
			strbuf.append("' name=");
			strbuf.append(dt_NameStr);
			strbuf.append(" id=");
			strbuf.append(NameStr);
			strbuf.append(" value='");
			strbuf.append(tmpIdValStr);
			strbuf.append("'");			
		}
		if(unitStr!=null)
			strbuf.append(unitStr);
		return strbuf.toString();
	}
	
	public static String getTextIdShowString(String nameStr,String idValStr,String showValStr,int mode,String classStr){
		if(mode==3)
			return HtmlUtil.getReadOnlyForms(nameStr, idValStr,showValStr,"show_",null, classStr);
		return HtmlUtil.getDisabledForms(nameStr, idValStr,showValStr,"show_",classStr);
	}
	
	public static String getTxetInputString(String lenStr,String nameStr,String valStr,String unitStr,int mode,String classStr){
		if(mode==3)
			return HtmlUtil.getReadOnlyForms(nameStr, valStr, classStr);
		if(mode==2){
			StringBuffer strbuf=new StringBuffer();
			if(null==lenStr||"".equals(lenStr)){
				strbuf.append("<input type=text class='"+classStr+"' name=");
			}else{
				strbuf.append("<input type=text maxlength='>");
				strbuf.append(lenStr);
				strbuf.append("' class='"+classStr+"' name=");
			}
			strbuf.append(nameStr);
			strbuf.append(" id=");
			strbuf.append(nameStr);
			strbuf.append(" value=");
			strbuf.append("'");
			strbuf.append(">");
			if (unitStr!=null) 
				strbuf.append(unitStr);
			return strbuf.toString();
		}
		return HtmlUtil.getDisabledForms(nameStr, valStr, classStr);
	}
	
	public static String getTxetInputStringDw(String lenStr,String nameStr,String valStr,String unitStr,int mode,String classStr){
		if(mode==3)
			return HtmlUtil.getReadOnlyForms(nameStr, valStr, classStr);
		if(mode==2){
			StringBuffer strbuf=new StringBuffer();
			if(null==lenStr||"".equals(lenStr)){
				strbuf.append("<input type=text class='"+classStr+"' name=");
			}else{
				strbuf.append("<input type=text maxlength='>");
				strbuf.append(lenStr);
				strbuf.append("' class='"+classStr+"' name=");
			}
			strbuf.append(nameStr);
			strbuf.append(" id=");
			strbuf.append(nameStr);
			strbuf.append(" value=");
			strbuf.append("'");
			strbuf.append(">");
			if (unitStr!=null) 
				strbuf.append(unitStr);
			return strbuf.toString();
		}
		return HtmlUtil.getDisabledForms(nameStr, valStr,unitStr,HtmlUtil.DISABLEDFORMSDEFAULTMARK, classStr);
	}
	
	public static String getTxetInputString(String lenStr,String nameStr,String valStr,String unitStr,int mode){
		if("null"==valStr||"".equals(valStr)||null==valStr)
			valStr="";
		if(mode==3)
			return HtmlUtil.getReadOnlyForms(nameStr, valStr, unitStr);
		if(mode==2){
			StringBuffer strbuf=new StringBuffer();
			if(null==lenStr||"".equals(lenStr)){
				strbuf.append("<input type=text class='"+unitStr+"' name=");
			}else{
				strbuf.append("<input type=text maxlength='>");
				strbuf.append(lenStr);
				strbuf.append("' class='"+unitStr+"' name=");
			}
			strbuf.append(nameStr);
			strbuf.append(" id=");
			strbuf.append(nameStr);
			strbuf.append(" value=");
			strbuf.append("'");
			strbuf.append(">");
			if (unitStr!=null) 
				strbuf.append(unitStr);
			return strbuf.toString();
		}
		return HtmlUtil.getDisabledForms(nameStr, valStr,unitStr,"inputtext2");
	}
	
	public static String getToolBar(int jlsl,int pageNo,int pageSl,String buttonStyle,String buttonEvent,String pageChangeEvent){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<table width=100%><tr><td align='center'>");
		int mode=2;
		if (pageSl==1||pageSl==0) {
			mode=1;
			strbuf.append(getButtonString("btn", "首 页",buttonStyle,buttonEvent+"('1')", mode, ""));
			strbuf.append("&nbsp;&nbsp;\n");
		}
		if(pageNo>1){
			mode=2;
			strbuf.append(getButtonString("btn", "上一页",buttonStyle,buttonEvent+"('-1')", mode, ""));			
		}else{
			mode=1;
			strbuf.append(getButtonString("btn", "下一页",buttonStyle,null, mode, ""));			
		}
		strbuf.append("&nbsp;&nbsp;\n");
		if(pageNo==pageSl||pageSl==0){
			mode=1;
			strbuf.append(getButtonString("btn", "尾 页",buttonStyle,null, mode, ""));			
		}else{
			mode=2;
			strbuf.append(getButtonString("btn", "尾 页",buttonStyle,buttonEvent+("'n'"), mode, ""));			
		}
		strbuf.append("&nbsp;&nbsp;\n");
		strbuf.append("共<font color='green'>"+jlsl+"</font>条记录,");
		strbuf.append("当前第：<select name='select' ");
		if(pageChangeEvent!=null&&!"".equals(pageChangeEvent)){
			strbuf.append("onChange='");
			strbuf.append(pageChangeEvent);
			strbuf.append("'");
		}
		if(jlsl>0)
			strbuf.append(">");
		else
			strbuf.append(">");
		if(pageSl<1){
			strbuf.append(" disabled>");		
		}else{
			for (int n = 0; n <=pageSl;n++) {
				if(n ==pageNo)
					strbuf.append("<option value="+n+" selected>"+n+"</option>");
				else
					strbuf.append("<otpion value='"+n+"'>"+n+"</option>");
			}
		}
		strbuf.append("<select>页</td></tr></table>\n");
		return strbuf.toString();
	}
	
	public static String getToolBar(int jlsl,int pageNo,int pageSl,String buttonStyle,String buttonEvent,String pageChangeEvent,String selectName){
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("<table width=100%><tr><td align='center'>");
		int mode=2;
		if (pageSl==1||pageSl==0) {
			mode=1;
			strbuf.append(getButtonString("btn", "首 页",buttonStyle,buttonEvent+"('1')", mode, ""));
			strbuf.append("&nbsp;&nbsp;\n");
		}
		if(pageNo>1){
			mode=2;
			strbuf.append(getButtonString("btn", "上一页",buttonStyle,buttonEvent+"('-1')", mode, ""));			
		}else{
			mode=1;
			strbuf.append(getButtonString("btn", "下一页",buttonStyle,null, mode, ""));			
		}
		strbuf.append("&nbsp;&nbsp;\n");
		if(pageNo==pageSl||pageSl==0){
			mode=1;
			strbuf.append(getButtonString("btn", "尾 页",buttonStyle,null, mode, ""));			
		}else{
			mode=2;
			strbuf.append(getButtonString("btn", "尾 页",buttonStyle,buttonEvent+("'n'"), mode, ""));			
		}
		strbuf.append("&nbsp;&nbsp;\n");
		strbuf.append("共<font color='green'>"+jlsl+"</font>条记录,");
		strbuf.append("当前第：<select name='select' ");
		strbuf.append(" name='");
		strbuf.append(selectName);
		strbuf.append("'");
		if(pageChangeEvent!=null&&!"".equals(pageChangeEvent)){
			strbuf.append("onChange='");
			strbuf.append(pageChangeEvent);
			
		}
		if(jlsl>0)
			strbuf.append(">");
		else
			strbuf.append(">");
		if(pageSl<1){
			strbuf.append(" disabled>");		
		}else{
			for (int n = 0; n <=pageSl;n++) {
				if(n ==pageNo)
					strbuf.append("<option value="+n+" selected>"+n+"</option>");
				else
					strbuf.append("<otpion value='"+n+"'>"+n+"</option>");
			}
		}
		strbuf.append("<select>页</td></tr></table>\n");
		return strbuf.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String tempstr;
		//tempstr=getHtagForms("llalal", true,  true, "PRE", "123");
		//tempstr=getOneInputHidden("123","k23","old");
		//tempstr=getButtonString("21", "21", "21","21", 1,"21");
		//tempstr=getTextareaForms(2,"12","34","54");
		tempstr=getToolBar(1,2,3,"123","ev","er");
		System.out.println(tempstr);
	}
}