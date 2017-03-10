
package cn.edu.zzti.common.util;

import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CommonUtil {
	private static Logger log = Logger.getLogger(CommonUtil.class);

	/**
	 * 对象转换为字符串，避免"null"的出现
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj) {
		return null == obj ? null : obj + "";
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return null == obj || "".equals(obj);
	}

	/**
	 * 判断保存结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkSave(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper > 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.SAVE_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.SAVE_FAIL_MSG);
		}
		return resMap;
	}

	/**
	 * 判断删除单张表的结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkDel(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper > 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.DEL_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.DEL_FAIL_MSG);
		}
		return resMap;
	}

	/**
	 * 判断重置密码的结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkResetPassword(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper > 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.RESET_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.RESET_FAIL_MSG);
		}
		return resMap;
	}
	
	/**
	 * 判断修改状态的结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkChangeStatus(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper > 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.CHANGE_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.CHANGE_FAIL_MSG);
		}
		return resMap;
	}
	
	/**
	 * 判断删除多张表的结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkDels(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper >= 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.DEL_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.DEL_FAIL_MSG);
		}
		return resMap;
	}

	/**
	 * 判断处理结果并设定返回值
	 * 
	 * @param oper
	 * @return
	 */
	public static Map<String, Object> checkResult(int oper) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (oper > 0) {
			resMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.RESULT_SUCCESS_MSG);
		} else {
			resMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			resMap.put(Constants.RESULT_MSG, Constants.RESULT_FAIL_MSG);
		}
		return resMap;
	}

	/**
	 * 生成分页类
	 * 
	 * @param page  当前页页码
	 * @param pagesize  页面大小
	 * @return
	 */
	public static PageUtil getPage(String page, String pagesize) {
		PageUtil pageUtil;
		if (CommonUtil.isNull(pagesize)) {
			pageUtil = new PageUtil(page, Constants.PER_PAGE_SIZE);
		}else {
			pageUtil = new PageUtil(page, Integer.valueOf(pagesize));
		}
		return pageUtil;
	}

	/**
	 * 设置响应头
	 * @param response
	 * @param fileName
	 */
	public  static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage(),e);
			}
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}


	/**
	 * 获取服务器mac地址
	 * @return
	 * @throws SocketException
	 */
	public static List<String> getLocalMac() throws SocketException {

		List<String> macList = new ArrayList<String>();	
		//获取所有网络接口
		Enumeration<NetworkInterface> macs = NetworkInterface.getNetworkInterfaces();	
		//遍历所有网络接口
		while(macs.hasMoreElements()) {

			NetworkInterface networkInterface = macs.nextElement();
			byte[] mac = networkInterface.getHardwareAddress();
			StringBuffer macString = new StringBuffer();
			//判断是否是网卡
			if(mac != null && mac.length == 6) {
				for (int i = 0; i < mac.length; i++) {
					if(i!=0) {
						macString.append("-");
					}
					//字节转换为整数	
					int temp = mac[i]&0xff;
					String str = Integer.toHexString(temp);
					if(str.length()==1) {
						macString.append("0"+str);
					}else {
						macString.append(str);
					}

				}
				macList.add(macString.toString());

			}
		}
		return macList;
	}


}
