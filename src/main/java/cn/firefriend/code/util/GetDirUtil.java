package cn.firefriend.code.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDirUtil {
	static int id = 1;
	public static List< Map<String , String> > getDir(String aimPath,int pid ,List< Map<String , String> > listDir ){
		Map<String, String> map = null;
		File aimFile = new File(aimPath);
		File[] subFiles =  aimFile.listFiles();
		
		for (File file : subFiles) {
			if(file.isFile()){
				map = creatMap(file,pid,id);
				id++;
				listDir.add(map);
			}else{
				map = creatMap(file,pid,id);
				int temp = pid;
				pid = id;
				id++;
				listDir.add(map);
				listDir = getDir(file.toString() , pid ,listDir);
				pid = temp;
			}
		}
		return listDir;
	}
	
	public static Map<String ,String> creatMap(File file, int pid ,int id){
		Map<String ,String> fileInfo = new HashMap<String, String>();
		fileInfo.put("title", file.getName());
		fileInfo.put("id", Integer.toString(id));
		fileInfo.put("pid", Integer.toString(pid));
		return fileInfo;
	}
	
	/**
	 * 返回该目录下的所有文件
	 * @param aimPath
	 * @return
	 */
	public static List< Map<String , String>> getDirList (String aimPath){
		System.out.println("getDirList:" + aimPath);
		List< Map<String , String> > listDir = new ArrayList<Map<String , String>>();
		getDir(aimPath, 0 ,listDir);
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "全部文件");
		map.put("id", "0");
		map.put("pid", "-1");
		listDir.add(map);
		return listDir;
	}
}
