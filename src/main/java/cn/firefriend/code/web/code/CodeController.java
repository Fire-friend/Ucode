package cn.firefriend.code.web.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.firefriend.code.dto.CodeExecution;
import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.CodeStateEnum;
import cn.firefriend.code.service.CodeService;
import cn.firefriend.code.util.AddMapToListUtil;
import cn.firefriend.code.util.GetDirUtil;
import cn.firefriend.code.util.GitUtil;
import cn.firefriend.code.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/code")
public class CodeController {
	@Autowired
	private CodeService codeService;
	
	/**
	 * 创建工程功能
	 * @param request
	 * @return 成功status为0，失败status为1，返回失败信息msg
	 * TODO 添加项目成员功能
	 */
	@RequestMapping("/creat")
	@ResponseBody
	public Map<String ,String> creatProject(HttpServletRequest request){
		Map<String ,String> modelMap = new HashMap<String, String>();
		String codeName = HttpServletRequestUtil.getString(request, "codeName");
		String description = HttpServletRequestUtil.getString(request, "description");
		boolean isPublic = HttpServletRequestUtil.getBoolean(request, "isPublic");
		Code code = new Code();
		code.setCodeName(codeName);
		code.setDescription(description);
		code.setPublic(isPublic);
		code.setPermissions(10);
		User user = (User) request.getSession().getAttribute("user");
		code.setUser(user);
		CodeExecution insertExecution = codeService.insertProject(code);
		//创建成功
		if(insertExecution.getStateInfo() == CodeStateEnum.SUCCESS.getStateInfo()){
			modelMap.put("status", "0");
		}else{
			modelMap.put("msg", insertExecution.getStateInfo());
			modelMap.put("status", "1");
		}
		return modelMap;
	}
	
	/**
	 * 查询用户有关项目信息<br/>
	 * action : create查询用户创建的，join查询用户参与的,all查询所有工程信息
	 * @return json数组，每个元素为每一个工程有关信息
	 */
	@RequestMapping("/codeQuery.do")
	@ResponseBody
	public List<Map<String, String>> codeQuery(HttpServletRequest request){
		//用户action
		String action = HttpServletRequestUtil.getString(request, "action");
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String ,String> codeMap = null;
		User user = (User) request.getSession().getAttribute("user");
		CodeExecution allProject = codeService.queryAllProject(user);
		//如果查询失败，返回错误信息，存在第一个元素
		if(allProject.getStateInfo() != CodeStateEnum.SUCCESS.getStateInfo()){
			codeMap = new HashMap<String, String>();
			codeMap.put("status", "0");
			codeMap.put("msg", allProject.getStateInfo());
			list.add(codeMap);
			return list;
		}
		List<Code> codeList = allProject.getCodeList();
		if("creat".equals(action)){
			//遍历list中的每一个code,且将permissions为10的添加进list中
			for (Code code : codeList) {
				if(code.getPermissions() == 10){
					codeMap = AddMapToListUtil.creatCodeMap(code);
					list.add(codeMap);
				}
			}
		}else if("join".equals(action)){
			//遍历list中的每一个code,且将permissions为5的添加进list中
			for (Code code : codeList) {
				if(code.getPermissions() == 5){
					codeMap = AddMapToListUtil.creatCodeMap(code);
					list.add(codeMap);
				}
			}
		}else if("all".equals(action)){
			//遍历list中的每一个code,将添加进list中
			for (Code code : codeList) {
					codeMap = AddMapToListUtil.creatCodeMap(code);
					list.add(codeMap);
			}
		}else{//用户未知行为
			codeMap = new HashMap<String, String>();
			codeMap.put("status", "0");
			codeMap.put("msg", "用户未知行为");
			list.add(codeMap);
		}
		return list;
	}
	
	/**
	 * 查询项目详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDetail.do")
	@ResponseBody
	public Map<String ,String> getCodeDetail(HttpServletRequest request){
		Map<String , String> modelMap = new HashMap<String, String>();
		String codeName = HttpServletRequestUtil.getString(request, "codeName");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			modelMap.put("status", "0");
			return modelMap;
		}
		Code code = new Code();
		code.setUser(user);
		code.setCodeName(codeName);
		CodeExecution queryCode = codeService.queryCode(code);
		if(queryCode.getStateInfo() == CodeStateEnum.SUCCESS.getStateInfo()){
			Code codeDetail = queryCode.getCode();
			modelMap.put("status", "1");
			modelMap.put("codeName", codeDetail.getCodeName());
			modelMap.put("creatUser", codeDetail.getUser().getUsername());
			modelMap.put("creatTime", codeDetail.getCreatTime().toString());
			modelMap.put("codeDescription", codeDetail.getDescription());
			modelMap.put("path", codeDetail.getPath());
		}else{
			modelMap.put("status", "0");
		}
		return modelMap;
	}
	
	/**
	 * 根据user和codeName获取项目目录
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCodeDir.do")
	@ResponseBody
	public List< Map<String , String> > getCodeDir(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			return null;
		}
		String codeName = HttpServletRequestUtil.getString(request, "codeName");
		Code code = new Code();
		code.setUser(user);
		code.setCodeName(codeName);
		CodeExecution queryCode = codeService.queryCode(code);
		if(queryCode.getStateInfo() == CodeStateEnum.SUCCESS.getStateInfo()){
			Code aimCode = queryCode.getCode();
			List<Map<String, String>> dirList = null;
			try {
				GitUtil.updateCloneRep(aimCode.getPath()+"\\client");
			} catch (IOException e) {
				return null;
			}
			dirList = GetDirUtil.getDirList(aimCode.getPath()+"\\client");
			System.out.println("listSize:"+ dirList.size());
			return dirList;
		}else{
			return null;
		}
	}
	
	
	
}
