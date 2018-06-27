package cn.firefriend.code.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class GetCmdStrUtil {
	/**
	 * 获取控制台输出结果
	 * 
	 * @return
	 * @throws IOException
	 */
	@Test
	public static String getCmdStr(String cmd, String path) throws Exception {
		File file = new File(path);
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(cmd, null, file);
		InputStream is = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		String result = "";
		while ((line = reader.readLine()) != null) {
			result += line + "\n";
		}
		process.waitFor();
		is.close();
		reader.close();
		process.destroy();
		return result;
	}

}
