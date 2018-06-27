package cn.firefriend.code.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import cn.firefriend.exception.CodeException;

public class GitUtil {

	/**
	 * 创建工程目录，初始化仓库
	 * @param email 用户邮箱
	 * @param codeName 工程名称
	 * @return 返回仓库的绝对路径
	 * @throws Exception 
	 */
	@Test
	public static String creatDir(String email , String codeName) throws Exception{
//		String email = "269182663@qq.com";
//		String codeName = "demo1";
		if(email == null||codeName == null){
			throw new CodeException("信息为空");
		}
		Runtime run =Runtime.getRuntime();
		File proPath = new File("user/"+email,"code/" + codeName);
		File absoluteFile = proPath.getAbsoluteFile();
		System.out.println("absoluteFile:" + absoluteFile);
		if(!absoluteFile.exists()){
			absoluteFile.mkdirs();
		}
		run.exec("git init --bare",null,absoluteFile);//创建初始仓库
		return absoluteFile.toString();
	}
	
	/**
	 *在远程仓库目录下创建clone仓库，用来文件获取 
	 * @param dirPath
	 * @return
	 * @throws IOException 
	 */
	@Test
	public static boolean creatCloneRep(String dirPath) throws IOException{
		boolean status = false;
		Runtime run = Runtime.getRuntime();
		File path = new File(dirPath);
		run.exec("git clone "+ dirPath +" client",null,path);
		status = true;
		return status;
	}
	
	/**
	 * 更新远程仓库下的克隆仓库，保证仓库最新
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean updateCloneRep(String path) throws IOException{
		boolean status = false;
		Runtime run = Runtime.getRuntime();
		File pathFile = new File(path);
		run.exec("git pull",null,pathFile);
		status = true;
		return status;
	}
}
