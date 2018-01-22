package com.test.hej;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
	private int k = 1; // 定义递归次数变量
	
	public void zip(String zipFileName,File inputFile){
		ZipOutputStream out=null;
		try {
			out=new ZipOutputStream(new FileOutputStream(zipFileName));//,Charset.forName("gbk")
			
			//编码处理中文 
			BufferedOutputStream bo=new BufferedOutputStream(out);
		    zip(out,inputFile, inputFile.getName(),bo);
		    bo.close();
		    out.close();
		    System.out.println("压缩成功");
		} catch (Exception e) {
			 System.out.println("压缩失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) {
		// TODO Auto-generated method stub
		try {
			if(f.isDirectory()){
				File[] f1=f.listFiles();
				if(f1.length==0){
					out.putNextEntry(new ZipEntry(base+"/"));					
				}
				for (int i = 0; i < f1.length; i++) {
					zip(out, f1[i],base+"/"+f1[i].getName(),bo);
				}
				//k次递归
				k++;
			}else{
	            BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
	            out.putNextEntry(new ZipEntry(new ZipEntry(base)));	            
	            String temp;
	            while((temp=in.readLine())!=null){
	                out.write(temp.getBytes("GBK"));
	            }
	            in.close();
	                       
//				out.putNextEntry(new ZipEntry(base));
//				FileInputStream in=new FileInputStream(f);
//				BufferedInputStream bi=new BufferedInputStream(in);
//				int b;				
//				while ((b = bi.read()) !=-1)  {					
//					bo.write(b);
//				}
//				bi.close();
//				in.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	public static void main(String[] args) {
		ZipFile zf=new ZipFile();
		zf.zip("D:\\FIndexDB\\webProject\\Layer\\2017\\07\\21\\511\\BZ.zip",new File("D:\\FIndexDB\\webProject\\Layer\\2017\\07\\21\\511\\BZ"));
	    System.out.println("zip pro");
	}
}
