package cn.firefriend.code.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Demo1 {
	@Test
	public  void test1() {
		List<Map<String, String>> dirList = GetDirUtil.getDirList("C:\\Users\\Administrator\\Desktop\\fileTree");
		for (Map<String, String> map : dirList) {
			for (String key : map.keySet()) {
				System.out.println(key + ":" +map.get(key));
			}
			System.out.println("------------------------");
		}
	}
	
	@Test
	public void getFile(){
		String readToString = GetFileContentUtil.readToString("C:\\Users\\Administrator\\Desktop\\layer-v3.1.1\\layer-v3.1.1\\test.html");
//		readToString.replace("\n", "</br>");
		System.out.println(readToString);
	}
	
	@Test
	public void findFile(){
		File findFiles = GetFileContentUtil.findFiles("C:\\Users\\Administrator\\Desktop\\friend", "duigou5555.jpg");
		if(findFiles != null){
			System.out.println("findFiles:" + findFiles);
		}
	}
	
	@Test
	public void cloneRep(){
		try {
			boolean creatCloneRep = GitUtil.creatCloneRep("D:\\test-folder\\rep");
			assertEquals(true,creatCloneRep);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
