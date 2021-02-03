package net.nanbot.plugin.sdk;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.json.*;


public class NanBotSdkMain {
	private final NanBotSdkApi nt = new NanBotSdkApi();

	/*
	 *	SDK说明（上手必读！）：
	 * 	1. 修改package为你自己的插件ID，发布的时候请打包。
	 * 	2. 框架因为是易语言写的程序，只能是 32 位程序，故 JDK 和 JRE 只能是 32 位的，否则无法使用。
	 * 	3. 此 SDK 作者 Java 技术并不好，如果您对此SDK有任何建议请通过官方渠道反馈。
	 *  4. 由于易语言 Java 库的原因，Java 插件无法实现插件的热卸载、热重载、热加载。
	 *  5. 各种事件类型、参数、以及API名、参数请参见官方文库和易语言SDK
	 *  6. 事件返回值：1 为处理完成，交给下个插件处理，2 为处理完成，拦截消息不交给下面插件处理，其余值为出错代码
	 *  7. Java 开发的插件因为易语言的Java库的原因会长期不支持热卸载、热重载、热加载
	 *  8. Jar发行说明：package（包名）必须为插件ID，文件名必须为 [插件ID.nt.jar] ；如插件ID为 net.nanbot.plugin.sdk 则文件名为 net.nanbot.plugin.sdk.nt.jar
	 *  9. 如果插件需要带有三方Jar包可直接提取到插件Jar包内或者将所需的jar包放到框架的bin目录中
	 *  10. 请全程使用UTF-8编码！（Please Always Use UTF-8！！！！！）
	 */

	/**
	 * 一个没啥用的构造函数，不知道为什么如果没这玩意加载的时候会取不到构造函数的句柄导致没法创建对象
	 * 请不要在这个方法里面执行任何初始化操作！！！
	 */
	public NanBotSdkMain(){

	}

	/**
	 * 此方法被调用时候，请不要放置任何其他代码，也请不要在此初始化操作，如果此处出错将会直接导致致命异常
	 * @return 基础信息Json
	 */
	public String GenerateInfo() {
		Map<String, Object> json = new LinkedHashMap<>();
		
		
		/*
		 * 基础信息补充说明：
		 * 内部版本号：确保每次发布更新时候这里的值比已有版本大，且只能为整数型
		 * 显示版本号：主要用于框架中显示
		 * 背景图片名：显示的背景图片文件名，请在初始化中将背景图片写到 “\webui\imgs\”文件夹中；如“net.nanbot.test.jpg”
		 * 插件的介绍：可以是HTML，HTML，框架内部使用前端框架为MDUI，参考：https://www.mdui.org/docs/；若您不知道HTML是什么，请直接使用纯文本
		 * 插件优先级：高优先级插件主要用于动态开关机器人、授权插件等；如果插件无需优先处理，请使用默认的正常
		 * 优先级方式：每次打开框架会自动生成一个序列表（0-Z），同优先级的插件调用将以插件MD5的值升序后按生成的序列表顺序进行调用。即：假如你的插件MD5开头是0，但是序列表中0位于最后，那么你的插件也有可能会最后调用，总之，插件调用顺序为随机
		 * 优先级注意：严禁各类流氓插件，一经发现直接封杀，请根据插件功能合理选择优先级，请携手创建一个良好的框架插件环境
		 */

		//插件基础信息填写
		Map<String, Object> info = new LinkedHashMap<>();
		
		info.put("name", "NanBot测试插件"); 			//插件名称
		info.put("author", "温泉"); 					//插件作者
		info.put("verindex", 1); 	   					//插件内部版本序号
		info.put("vername"	,"1.0 Beta"); 				//插件显示版本号
		info.put("id","net.nanbot.plugin.sdk"); 		//插件ID，此处请填写为包名
		info.put("url",""); 							//联系地址，用户点击联系作者时候跳转地址
		info.put("describe","这是一个测试插件"); 		//插件说明介绍，支持HTML
		info.put("priority",2); 						//插件优先级，1为优先，2为正常，3为滞后
		info.put("bg","");	 							//插件背景图片文件名
		
		json.put("info",info);

		
		//插件SDK信息，请勿修改：
		Map<String, Object> sdk = new LinkedHashMap<>();
		sdk.put("ver",1);
		
		json.put("sdk",sdk);
		
		//插件需要接收哪些事件信息，未开启的事件将不会传递，未开启事件的相关子程序可删除以保持源码整洁
		Map<String, Object> event = new LinkedHashMap<>();
		
		event.put("group",		true); //群事件
		event.put("frame", 		true); //框架和插件事件
		event.put("verify", 		true); //验证事件
		event.put("friend", 		true); //好友事件
		event.put("provisional", 	true); //临时事件
		event.put("other", 		true); //其他事件
		event.put("public", 		true); //公众号事件
		
		json.put("event",event);

		
		//插件需要的权限信息，如果未申明权限会被拒绝调用
		Map<String, Object> auth = new LinkedHashMap<>();

		auth.put("group",true); //群操作
		auth.put("message",true); //消息操作
		auth.put("baseinfo",true); //基础信息获取
		auth.put("maininfo",true); //敏感信息获取
		auth.put("frame",true); //框架插件操作
		auth.put("verify",true); //验证消息操作
		auth.put("robot",true); //机器人自身操作
		auth.put("money",false); //钱包操作
		
		json.put("auth",auth);
		
		JSONObject json1 = new JSONObject(json);
		return json1.toString();
	}


	/**
	 * 此方法为接收授权码，授权码是调用API的唯一许可凭证，请不要在此执行任何其他多余代码，包括初始化
	 */
	public int AuthCode(String token) {
		nt.SetAuthCode(token);
		return 1;
	}


	//下方事件如果不需要可以注释或者直接删除，返回值：1为处理完成交给下个插件处理；2为处理完成不交给下一个插件处理，其余值为出错代码，框架会捕捉输出的异常并弹窗

	/**
	 * 框架事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @param ErrorPtr  错误消息指针，Java好像没有操作指针的函数...所有这里无效
	 * @return			运行结果
	 */
	public int Event_Frame(int type , String content, int ErrorPtr) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1;
	}
	
	/**
	 * 好友事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return 			运行结果
	 */
	public int Event_Friend(int type , String content) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1; 
	}


	/**
	 * 群事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return			运行结果
	 * @throws JSONException 错误
	 */
	public int Event_Group(int type , String content) throws JSONException {
		nt.OutPut("type:"+type + "; content:"+content);
		if(type == 101) {
			JSONObject obj = new JSONObject(content);
			if(Objects.equals(obj.getString("msg"), "测试") && obj.getLong("qq") == 80071319) {
				nt.sendGroupMsg(obj.getLong("group"),1,"测试测试测试测试");
			}
		}
		return 1; 
	}
	/**
	 * 公众号事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return			int
	 */
	public int Event_Public(int type , String content) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1;
	}


	/**
	 * 验证事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return			int
	 */
	public int Event_Verify(int type , String content) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1;
	}


	/**
	 * 临时会话事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return		 int
	 */
	public int Event_Provisional(int type , String content) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1; 
	}


	/**
	 * 其他事件
	 * @param type 		事件类型
	 * @param content	事件内容
	 * @return int
	 */
	public int Event_Other(int type , String content) {
		nt.OutPut("type:"+type + "; content:"+content);
		return 1;
	}

	/**
	 * 插件卸载
	 */
	public void PluiginDump() {
		nt.OutPut("插件卸载");
		//此方法只会在框架关闭时候调用
	}
}
