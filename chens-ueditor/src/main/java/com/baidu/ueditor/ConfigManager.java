package com.baidu.ueditor;

import com.baidu.ueditor.define.ActionMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置管理器
 *
 * @author hancong03@baidu.com
 */
public final class ConfigManager {

	private final String rootPath;

	private final String staticPath;

	private final String projectPath;

	private static final String configFileName = "config.json";

	private JSONObject jsonConfig = null;

	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";

	/**
     * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
     */
	private ConfigManager(String rootPath, String staticPath, String projectPath) throws IOException {
		this.rootPath = rootPath;
		this.staticPath =staticPath;
		this.projectPath = projectPath;

		this.initEnv();
	}

	/**
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager(InputStream inputStream) throws IOException {
		this.rootPath = "";
		this.staticPath ="";
		this.projectPath = "";
		String configContent = this.readFile(inputStream);
		try {
			JSONObject jsonConfig = new JSONObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch (Exception e) {
			this.jsonConfig = null;
		}
	}

	/**
	 * 配置管理器构造工厂
	 *
	 *
	 * @param rootPath  服务器根路径
	 * @param staticPath  静态资料路径
	 * @param projectPath 项目前缀路径
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance(String rootPath, String staticPath, String projectPath) {
		try {
			return new ConfigManager(rootPath,staticPath,projectPath);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 配置管理器构造工厂
	 *
	 *
	 * @param inputStream  配置文件流
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance(InputStream inputStream) {
		try {
			return new ConfigManager(inputStream);
		} catch (Exception e) {
			return null;
		}

	}

	// 验证配置文件加载是否正确
	public boolean valid() {
		return this.jsonConfig != null;
	}

	public JSONObject getAllConfig() {

		return this.jsonConfig;

	}

	public Map<String, Object> getConfig(int type) throws JSONException {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;

		switch (type) {

			case ActionMap.UPLOAD_FILE:
				conf.put("isBase64", "false");
				conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
				conf.put("allowFiles", this.getArray("fileAllowFiles"));
				conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
				savePath = this.jsonConfig.getString("filePathFormat");
				break;

			case ActionMap.UPLOAD_IMAGE:
				conf.put("isBase64", "false");
				conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
				conf.put("allowFiles", this.getArray("imageAllowFiles"));
				conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
				savePath = this.jsonConfig.getString("imagePathFormat");
				break;

			case ActionMap.UPLOAD_VIDEO:
				conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
				conf.put("allowFiles", this.getArray("videoAllowFiles"));
				conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
				savePath = this.jsonConfig.getString("videoPathFormat");
				break;

			case ActionMap.UPLOAD_SCRAWL:
				conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
				conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
				conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
				conf.put("isBase64", "true");
				savePath = this.jsonConfig.getString("scrawlPathFormat");
				break;

			case ActionMap.CATCH_IMAGE:
				conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
				conf.put("filter", this.getArray("catcherLocalDomain"));
				conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
				conf.put("allowFiles", this.getArray("catcherAllowFiles"));
				conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
				savePath = this.jsonConfig.getString("catcherPathFormat");
				break;

			case ActionMap.LIST_IMAGE:
				conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
				conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
				conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
				break;

			case ActionMap.LIST_FILE:
				conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
				conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
				conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
				break;
			default:
				break;

		}

		conf.put("savePath", savePath);
		conf.put("rootPath", this.rootPath);

		conf.put("staticPath", this.staticPath);
		conf.put("projectPath", this.projectPath);

		return conf;

	}

	private void initEnv() throws IOException {

		String configContent = this.readFile(rootPath + File.separator + configFileName);
		try {
			JSONObject jsonConfig = new JSONObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch (Exception e) {
			this.jsonConfig = null;
		}
	}


	private String[] getArray(String key) throws JSONException {

		JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
		String[] result = new String[jsonArray.length()];

		for (int i = 0, len = jsonArray.length(); i < len; i++) {
			result[i] = jsonArray.getString(i);
		}

		return result;

	}

	private String readFile(String path) throws IOException {
		return readFile(new FileInputStream(path));
	}

	private String readFile(InputStream inputStream) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {

			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}

		return this.filter(builder.toString());

	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

}
