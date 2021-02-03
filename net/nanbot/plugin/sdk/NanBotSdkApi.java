package net.nanbot.plugin.sdk;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.*;


class NanBotSdkApi {
	private String token = "";
	public final int INT_TEST = 1;
	
    public interface CLibrary extends Library {
        // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
        CLibrary INSTANCE = (CLibrary) Native.load(("ntapi.dll" ), CLibrary.class);

        // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
        String nt_callFuction(String format);
    }
	
	public void SetAuthCode(String tk) {
		this.token = tk;
	}

	/**
	 * 消息转义，如果消息内容里面有非特殊码而出现了`[` `]` `{` `}`字符的话需要进行转义
	 * @param msg 待转义文本
	 * @return
	 */
	public String msgFormat(String msg) {
		msg = msg.replace("&","&#38;");
		msg = msg.replace("[","&#91;");
		msg = msg.replace( "]","&#93;");
		msg = msg.replace("{","&#123;");
		msg = msg.replace("}","&#125;");
		return msg;
	}

	/**
	 * 将转义了的消息还原
	 * @param msg
	 * @return
	 */
	public String msgDeformat(String msg) {
		msg = msg.replace("&#91;", "[");
		msg = msg.replace("&#93;", "]");
		msg = msg.replace("&#123;", "{");
		msg = msg.replace("&#125;", "}");
		msg = msg.replace("&#38;", "&");
		return msg;
	}

	/**
	 * 特殊码_艾特，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param obj 目标对象QQ
	 * @return
	 */
	public String code_AT(String obj) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","at");
		j.put("val",obj);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}


	/**
	 * 特殊码_图片，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param picData 图片内容，可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param type 图片类型，jpg / png / bmp / gif 等图片类型后缀名;不需要加“.”
	 * @param show 是否是秀图
	 * @param showType 秀图类型，目前已知的有 40001 和 40002
	 * @param flash 是否以闪照方式发送，不能与修图同时为true
	 * @return
	 */
	public String code_pic(String picData, int width, int height, String type, Boolean show, int showType, boolean flash) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","pic");
		j.put("val",picData);
		j.put("Width",width);
		j.put("Height",height);
		j.put("Pictype",type);
		j.put("Show",show);
		j.put("ShowType",showType);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}

	/**
	 * 特殊码_图片，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param picData 图片内容，可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param type 图片类型，jpg / png / bmp / gif 等图片类型后缀名;不需要加“.”
	 * @return
	 */
	public String code_pic(String picData, int width, int height, String type) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","pic");
		j.put("val",picData);
		j.put("Width",width);
		j.put("Height",height);
		j.put("Pictype",type);
		j.put("Show",false);
		j.put("ShowType",0);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}
	
	
	/**
	 * 特殊码_图片，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param picData 图片内容，可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @return
	 */
	public String code_pic(String picData, int width, int height) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","pic");
		j.put("val",picData);
		j.put("Width",width);
		j.put("Height",height);
		j.put("Pictype","");
		j.put("Show",false);
		j.put("ShowType",0);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}
	/**
	 * 特殊码_图片，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param picData 图片内容，可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 * @return
	 */
	public String code_pic(String picData) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","pic");
		j.put("val",picData);
		j.put("Width",0);
		j.put("Height",0);
		j.put("Pictype","");
		j.put("Show",false);
		j.put("ShowType",0);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}
	
	/**
	 * 特殊码_表情，可直接混合在消息中使用，消息发送出去后为指定的特殊内容
	 * @param faceid 表情ID
	* @return
	 */
	public String code_face(String faceid) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","face");
		j.put("val",faceid);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}
	
	/**
	 * 特殊码_符号，可直接混合在消息中使用，消息发送出去后为指定的特殊内容，此项 emoji 传递 Utf8 十六进制，可以发送一些易语言无法显示的特殊字符
	 * @param hex UTF-8
	 * @return
	 */
	public String code_emoji(String hex) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("mod","face");
		j.put("val",hex);
		String par = new JSONObject(j).toString();
		return "[nt:" + par + "]";
	}
	
	/**
	 * [基础信息] 获取机器人好友列表
	 * @return Json格式机器人好友列表
	 * @throws JSONException 
	 */
	public JSONObject GetFriendList() throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetFriendList");
		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [基础信息] 获取机器人群列表
	 * @return Json格式群列表
	 * @throws JSONException 
	 */
	public JSONObject GetGroupList() throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetGroupList");
		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [消息操作] 发送 普通/xml/json 消息给某个好友
	 * @param toQQ 好友QQ
	 * @param msgType 消息格式，1为普通文本 2为XML 3为Json
	 * @param content 消息内容
	 */
	public void sendFriendMsg(long toQQ, int msgType, String content) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SendMsg");
		j.put("type",1);
		j.put("msgType",msgType);
		j.put("target",toQQ);
		j.put("content",content);
		this.callFunction(j);
	}

	/**
	 * [消息操作] 发送 普通/xml/json 消息给某个群
	 * @param toGroup 目标群号
	 * @param msgtype 消息格式 1为普通文本 2为XML 3为Json
	 * @param Content 消息内容/XML内容/Json内容
	 */
	public void sendGroupMsg(long toGroup,int msgtype,String Content){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SendMsg");
		j.put("type",2);
		j.put("msgType",msgtype);
		j.put("target",toGroup);
		j.put("content",Content);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 获取某个群成员的详细信息
	 * @param group
	 * @param qq
	 * @return 群成员信息结果 Json
	 * @throws JSONException
	 */
	public JSONObject GetGroupMemberInfo(long group, long qq) throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetGroupMemberInfo");
		j.put("group",group);
		j.put("qq",qq);

		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [基础信息] 获取群详细信息
	 * @param group
	 * @return
	 * @throws JSONException
	 */
	public JSONObject GetGroupInfo(long group) throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetGroupInfo");
		j.put("group",group);
		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [群操作] 禁言某人
	 * @param group
	 * @param qq
	 * @param time 单位为秒，填0为解禁
	 */
	public void ShutUp(long group, long qq, int time) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ShutUp");
		j.put("group",group);
		j.put("qq",qq);
		j.put("time",time);

		this.callFunction(j);
	}
	
	
	/**
	 * [群操作] 撤回某条群消息
	 * @param group
	 * @param msgId 消息ID
	 * @param msgNo 消息序号
	 */
	public void DrawGroupMsg(long group, int msgId, int msgNo) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","DrawGroupMsg");
		j.put("group",group);
		j.put("msgId",msgId);
		j.put("msgNo",msgNo);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 置全群禁言或解禁
	 * @param group
	 * @param isShutUp 是否禁言
	 */
	public void ShutUpGroup(long group, Boolean isShutUp) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ShutUpGroup");
		j.put("group", group);
		j.put("isShutUp",isShutUp);

		this.callFunction(j);
	}
	
	/**
	 * [群操作] 设置每分钟发言频率限制
	 * @param group
	 * @param num 发言条数 目前腾讯的选项只有5或10，其余值后果自负
	 */
	public void SetMsgSpeed(long group, int num) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetMsgSpeed");
		j.put("group", group);
		j.put("num",num);
		this.callFunction(j);
	}
	
	
	/**
	 * [群操作] 置匿名聊天开关
	 * @param group
	 * @param isOpen 是否开启
	 */
	public void SetAnonymousSwitch(long group, Boolean isOpen) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetAnonymousSwitch");
		j.put("group", group);
		j.put("switch",isOpen);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 置坦白说开关
	 * @param group
	 * @param isOpen 是否开启
	 */
	public void SetHonestSwitch(long group, Boolean isOpen) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetHonestSwitch");
		j.put("group", group);
		j.put("switch",isOpen);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 置群成员群名片
	 * @param group
	 * @param qq
	 * @param card
	 */
	public void SetGroupCard(long group, long qq, String card) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetGroupCard");
		j.put("group", group);
		j.put("qq",qq);
		j.put("card", card);
		this.callFunction(j);
	}

	/**
	 * [群操作] 置群聊的名称
	 * @param group 目标群号
	 * @param name 群聊名称
	 */
	public void SetGroupName(long group, String name) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetGroupName");
		j.put("group", group);
		j.put("name", name);
		this.callFunction(j);
	}
	
	/**
	 * [基础信息] 获取机器人账号
	 * @return 已登录的机器人QQ
	 */
	public String GetRobotQQ() {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetRobotQQ");
		return this.callFunction(j);
	}
	
	/**
	 * [基础信息] 获取机器人昵称
	 * @return 已登录的机器人昵称
	 */
	public String GetRobotName() {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetRobotName");
		return this.callFunction(j);
	}
	
	/**
	 * [框架操作] 向框架写入一条日志
	 * @param msg 内容
	 */
	public void OutPut(String msg) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","OutPut");
		j.put("val", msg);
		this.callFunction(j);
	}
	
	/**
	 * [敏感信息] 获取机器人skey
	 * @return 已登录的机器人skey
	 */
	public String GetSkey() {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetSkey");
		return this.callFunction(j);
	}
	
	/**
	 * [敏感信息] 获取机器人P_skey
	 * @return 已登录的机器人P_skey
	 * @throws JSONException 
	 */
	public JSONObject GetP_skey() throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetP_skey");
		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [框架操作] 在框架 WebUI 底部显示一条提示
	 * @param msg
	 */
	public void ShowTips(String msg) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ShowTips");
		j.put("val", msg);
		this.callFunction(j);
	}
	
	/**
	 * [框架操作] 在框架 WebUI 显示一个信息提示框，需要用户点击确认关闭
	 * @param msg
	 */
	public void ShowAlert(String msg) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ShowAlert");
		j.put("val", msg);
		this.callFunction(j);
	}
	
	/**
	 * [验证消息操作] 处理群事件，目前支持某人申请加群、某人邀请对象加群处理
	 * @param type 1同意，2拒绝
	 * @param group 群号
	 * @param qq 进群的QQ号
	 * @param seq 由事件参数传递而来
	 */
	public void GroupEventProcessing(int type, long group, long qq, long seq) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GroupEventProcessing");
		j.put("code", type);
		j.put("group", group);
		j.put("qq", qq);
		j.put("seq", seq);
		this.callFunction(j);
	}

	/**
	 * [验证消息操作] 处理好友事件，好友添加请求
	 * @param type 处理方式 1同意 2拒绝
	 * @param qq
	 * @param msgId
	 * @param seq
	 */
	public void FriendEventProcessing(int type, long qq, long msgId, long seq) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","FriendEventProcessing");
		j.put("code", type);
		j.put("qq", qq);
		j.put("msgNo", msgId);
		j.put("seq", seq);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 邀请(多个)好友加入某个群聊
	 * @param group
	 * @param qq
	 */
	public void InviteFriendInGroup(long group, long[] qq) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","InviteFriendInGroup");
		j.put("group", group);
		j.put("qq", qq);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 置群成员专属头衔
	 * @param group
	 * @param qq
	 * @param name
	 */
	public void SetMemberTitle(long group, long qq, String name) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetMemberTitle");
		j.put("group", group);
		j.put("qq", qq);
		j.put("name", name);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 退出或解散某群
	 * @param group
	 */
	public void ExitGroup(long group) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ExitGroup");
		j.put("group", group);
		this.callFunction(j);
	}
	
	
	/**
	 * [群操作] 置群成员移除
	 * @param group
	 * @param qq
	 * @param noEnter 是否拒绝再次申请
	 */
	public void KickMember(long group, long qq, Boolean noEnter) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","KickMember");
		j.put("group", group);
		j.put("qq", qq);
		j.put("noEnter", noEnter);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 获取群成员列表
	 * @param group
	 * @return 群成员列表Json
	 * @throws JSONException
	 */
	public JSONObject GetGroupMemberList(long group) throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetGroupMemberList");
		j.put("group",group);
		return new JSONObject(this.callFunction(j));
	}
	
	/**
	 * [消息操作] 发送一条语音消息给指定对象；注意：语音必须是 amr 格式，非 amr 格式无法发送，请自行转码
	 * @param type 发送类型，1为好友，2为群
	 * @param target 目标
	 * @param content 可以是 网络网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 * @param time 语音持续时间，单位为秒
	 */
	public void SendVoice(int type, long target, String content, int time) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SendVoice");
		j.put("type", type);
		j.put("target", target);
		j.put("content", content);
		j.put("time", time);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 给某人点赞
	 * @param qq 点赞目标
	 * @param num 点赞数量
	 */
	public void Favorite(long qq, int num) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","Favorite");
		j.put("qq", qq);
		j.put("num", num);
		this.callFunction(j);
	}
	
	/**
	 * [群操作] 设置群消息提醒方式
	 * @param group
	 * @param type 1接收并提醒，2收进群助手，3屏蔽群消息，4接收但不提醒
	 */
	public void SetGroupNotifyType(long group, int type) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetGroupNotifyType");
		j.put("group", group);
		j.put("type", type);
		this.callFunction(j);
	}
	
	/**
	 * [SendTempMsg][消息操作] 发送群临时消息
	 * @param group
	 * @param qq
	 * @param content
	 * @param type
	 */
	public void SendTempMsg(long group, long qq, String content, int type) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SendTempMsg");
		j.put("group", group);
		j.put("qq", qq);
		j.put("content", content);
		j.put("type", type);
		this.callFunction(j);
	}
	
	/**
	 * [框架操作] 获取框架名称和内部版本号
	 * @return 
	 */
	public String GetFrameVer() {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetFrameVer");
		return this.callFunction(j);
	}
	
	/**
	 * [框架操作] 获取机器人的在线信息，包括在线时长、收发速率，收发数量
	 * @return 
	 */
	public String GetRobotStatus() {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetRobotStatus");
		return this.callFunction(j);
	}
	
	
	/**
	 * [机器人操作] 设置机器人签名
	 * @param content
	 */
	public void SetSign(String content) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetSign");
		j.put("content", content);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 设置机器人昵称
	 * @param content
	 */
	public void SetNick(String content) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetNick");
		j.put("content", content);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 设置机器人头像
	 * @param content 可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 */
	public void SetAvatar(String content) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetAvatar");
		j.put("content", content);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 设置机器人封面
	 * @param content 可以是 图片网址 / 本地完整路径 / 已缓存在data\image中的文件 / 图片Base64(不推荐)
	 */
	public void SetCover(String content) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetCover");
		j.put("content", content);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 设置机器人的出生日期
	 * @param year
	 * @param month
	 * @param day
	 */
	public void SetBirthday(short year, byte month, byte day) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetBirthday");
		j.put("year", year);
		j.put("month", month);
		j.put("day", day);
		this.callFunction(j);
	}
	
	/**
	 * [机器人操作] 设置机器人的性别
	 * @param sex 1男 2女
	 */
	public void SetSex(int sex) {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetSex");
		j.put("sex", sex);
		this.callFunction(j);
	}

	/**
	 * [机器人操作] 设置好友备注
	 * @param qq 账号
	 * @param name 备注名称
	 */
	public void SetFriendName(long qq, String name){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetSex");
		j.put("qq", qq);
		j.put("name", name);
		this.callFunction(j);
	}

	/**
	 * [机器人操作] 拉圈圈，调用一次赞一次
	 * @param qq 目标QQ
	 * @param CircleId 圈圈ID，可以通过取资料卡获取
	 */
	public void LikeIt(long qq, long CircleId){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","LikeIt");
		j.put("qq", qq);
		j.put("id", CircleId);
		this.callFunction(j);
	}

	/**
	 * [机器人操作] 点赞资料卡中的个性标签，调用一次赞一次
	 * @param qq 目标对象
	 * @param LabelId 标签ID
	 */
	public void LikeLable(long qq, long LabelId){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","LikeLable");
		j.put("qq", qq);
		j.put("id", LabelId);
		this.callFunction(j);
	}

	/**
	 * [机器人操作] 创建一个新群聊
	 * @param name 群聊名称
	 * @return 新群号码
	 */
	public long CreateGroup(String name){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","CreateGroup");
		j.put("gn", name);

		return Long.parseLong(this.callFunction(j)) ;
	}

	/**
	 * [基础信息] 获取资料卡信息
	 * @param qq 目标QQ
	 * @return 资料卡Json对象
	 * @throws JSONException
	 */
	public JSONObject GetSummaryCard(long qq) throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetSummaryCard");
		j.put("qq", qq);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * 调用框架的CURL库进行网页Post
	 * @param url 请求地址
	 * @param type 请求类型，0 GET，1 文本型POST，2 字节集型POST，默认为0
	 * @param postString 类型为1时候有用
	 * @param postBytes 类型为2时候有用
	 * @param cookies
	 * @param userAgent
	 * @param header
	 * @return
	 * @throws JSONException
	 */
	public JSONObject HttpCurl(String url, int type,String postString, byte[] postBytes,String cookies,String userAgent,String[] header) throws JSONException{
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","GetSummaryCard");
		j.put("url", url);
		j.put("type", type);
		j.put("posttext", postString);
		j.put("posthex", bytesToHexString(postBytes));
		j.put("cookies", cookies);
		j.put("useragent", userAgent);
		j.put("header", header);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * [群操作] 设置或者取消管理员权限
	 * @param group
	 * @param qq
	 * @param status true设置，false取消
	 */
	public void SetAdminStatus(long group, long qq, boolean status){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","SetAdminStatus");
		j.put("group", group);
		j.put("qq", qq);
		j.put("status", status);
		this.callFunction(j);
	}

	/**
	 * [群操作] 将某个匿名用户禁言
	 * @param group 群号
	 * @param uid 匿名UID
	 * @param nick 匿名名称
	 * @param time 禁言时长，单位为秒
	 */
	public void ShutUpAnonymous(long group, String uid, String nick,long time){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","ShutUpAnonymous");
		j.put("group", group);
		j.put("id", uid);
		j.put("nick", nick);
		j.put("time", time);
		this.callFunction(j);
	}

	/**
	 * [消息操作] 设置或者取消某条消息为精华
	 * @param group
	 * @param msgId
	 * @param msgNo
	 * @param status
	 */
	public void setMessageEssence(long group,int msgId,int msgNo,boolean status){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","setEssence");
		j.put("group", group);
		j.put("msgId", msgId);
		j.put("msgNo", msgNo);
		j.put("status", status);
		this.callFunction(j);
	}

	/**
	 * [群操作] 开启或者关闭某个群的幸运字符，需要管理员权限
	 * @param group
	 * @param status
	 */
	public void setGroupLuckyCharStatus(long group, boolean status){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","setLuckyChar");
		j.put("group", group);
		j.put("status", status);
		this.callFunction(j);
	}

	/**
	 * [群操作] 在群聊内双击某人头像，俗称拍一拍
	 * @param group
	 * @param qq
	 */
	public void touchTwice(long group, long qq){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","touchTwice");
		j.put("group", group);
		j.put("qq", qq);
		this.callFunction(j);
	}

	/**
	 * [钱包操作] 发送一个普通红包给群聊或者好友或者群临时，返回Json对象，示例请看源码定位到这个API处
	 * @param toObj 如果目标是好友就是好友Q号，是群就是群号
	 * @param num 红包数量
	 * @param money "1.23" 表示1块钱两毛三分
	 * @param title
	 * @param type 1好友 2群  3群临时会话
	 * @param group 仅当红包类型为群临时会话(3)有效且必须传递，其余时候任意或0
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendSimpleRedPacket(long toObj, short num, String money,String title, int type, long group) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","sendSimplePacket");
		j.put("obj", toObj);
		j.put("num", num);
		j.put("money", money);
		j.put("brief", title);
		j.put("type", type);
		j.put("group", group);
		return new JSONObject(this.callFunction(j));
		//{"retcode":"0","retmsg":"success","bargainor_id":"1000026901","callback_url":"https%3A%2F%2Fmqq.tenpay.com%2Fv2%2Fhybrid%2Fwww%2Fmobile_qq%2Fpayment%2Fpay_result.shtml%3F_wv%3D1027%26channel%3D2","pay_flag":"1","pay_time":"2021-01-28 18:18:31","real_fee":"1","sp_billno":"1010000269015021012xxxxxxxxxxxxx","sp_data":"attach%3DCgQKABABElEQ8YnZCBoMU0FP5Li2S2lyaXRvIJeVlyYqBOaziS4wATgBQABIAVAAWg4xODIuMTMxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx%26bank_billno%3D%26bank_type%3DCFT%26bargainor_id%3D1000026901%26charset%3D2%26fee_type%3D1%26pay_result%3D0%26purchase_alias%3D400988517%26sign%3DCF04B547759E925CF1BD4771xxxxxxxx%26sp_billno%3D101000026901502xxxxxxxxxxxxxxxxxxx%26time_end%3D20210128181831%26total_fee%3D1%26transaction_id%3D100002690121012800047xxxxxxxxxxxxxxxxxxx%26ver%3D2.0","transaction_id":"100002690121012800047311615xxxxxxxxxxxxxxxxxxx","send_flag":"0"}
	}

	/**
	 * [钱包操作] 发送一个口令红包给好友或者群聊，返回Json对象
	 * @param toObj  如果目标是好友就是好友Q号，是群就是群号
	 * @param num 红包数量
	 * @param money "1.23" 表示1块钱两毛三分
	 * @param title
	 * @param type 1好友 2群
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendCommandRedPacket(long toObj, short num, String money,String title, int type) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","sendCommandPacket");
		j.put("obj", toObj);
		j.put("num", num);
		j.put("money", money);
		j.put("brief", title);
		j.put("type", type);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * [钱包操作] 发送一个专享红包到群聊，返回Json对象
	 * @param group 目标群
	 * @param qq 专享QQ
	 * @param money 金额
	 * @param title
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendExclusiveRedPacket(long group, long qq, String money, String title) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","sendExclusivePacket");
		j.put("group", group);
		j.put("obj", qq);
		j.put("num", 1);
		j.put("money", money);
		j.put("brief", title);
		return new JSONObject(this.callFunction(j));
	}


	/**
	 * [钱包操作] 给某个好友进行转账，返回Json对象
	 * @param qq
	 * @param money "1.23" 表示1块钱两毛三分
	 * @param title
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendTransferAccounts(long qq, String money, String title) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","TransferAccounts");
		j.put("obj", qq);
		j.put("money", money);
		j.put("brief", title);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * [钱包操作] 发送一个拼手气红包到群聊，返回Json对象
	 * @param group
	 * @param num
	 * @param money "1.23" 表示1块钱两毛三分
	 * @param title
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendFightLuckRedPacket(long group, int num, String money, String title) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","sendFightLuckPacket");
		j.put("group", group);
		j.put("num", num);
		j.put("money", money);
		j.put("brief", title);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * [钱包操作] 发送一个语音红包给群聊或者好友，返回结果Json文本
	 * @param obj
	 * @param num
	 * @param money "1.23" 表示1块钱两毛三分
	 * @param content
	 * @param type 1好友 2群
	 * @return
	 * @throws JSONException
	 */
	public JSONObject sendVoiceRedPacket(long obj, int num, String money, String content, int type) throws JSONException {
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","sendVoicePacket");
		j.put("obj", obj);
		j.put("num", num);
		j.put("money", money);
		j.put("brief", content);
		j.put("type", type);
		return new JSONObject(this.callFunction(j));
	}

	/**
	 * [群操作] 设置群成员修改名片时候的规则提示
	 * @param group
	 * @param rule
	 */
	public void setGroupCardRule(long group, String rule){
		Map<String, Object> j = new LinkedHashMap<String, Object>();
		j.put("func","setGroupCardRule");
		j.put("group", group);
		j.put("rule", rule);
		this.callFunction(j);
	}




	/**
	 * 调用 NanBot 公开函数，具体调用参数以及命令以及返回解析请参见文库和易语言 SDK
	 * @param callinfo 调用参数
	 * @return 如果有返回则返回 Json 字符串，否则返回空
	 */
	public String callFunction(Map<String, Object> callinfo){
		callinfo.put("tk", this.token);
		return CLibrary.INSTANCE.nt_callFuction(new JSONObject(callinfo).toString());
	}




	private static String string2HexString(String strPart) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < strPart.length(); i++) {
			int ch = (int) strPart.charAt(i);
			String strHex = Integer.toHexString(ch);
			hexString.append(strHex);
		}
		return hexString.toString();
	}


	/**
	 * 字节数组转16进制字符串
	 */
	public static String bytesToHexString(byte[] bArr) {
		if (bArr == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(bArr.length);
		String sTmp;
		for (byte b : bArr) {
			sTmp = Integer.toHexString(0xFF & b);
			if (sTmp.length() < 2)
				sb.append(0);
			sb.append(sTmp);
		}

		return sb.toString();
	}

	/**
	 * hex字符串转byte数组
	 *
	 * @param inHex 待转换的Hex字符串
	 * @return 转换后的byte数组结果
	 */
	public static byte[] hexToByteArray(String inHex) {
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1) {
			//奇数
			hexlen++;
			result = new byte[(hexlen / 2)];
			inHex = "0" + inHex;
		} else {
			//偶数
			result = new byte[(hexlen / 2)];
		}
		int j = 0;
		for (int i = 0; i < hexlen; i += 2) {
			result[j] = hexToByte(inHex.substring(i, i + 2));
			j++;
		}
		return result;
	}
	public static byte hexToByte(String inHex) {
		return (byte) Integer.parseInt(inHex, 16);
	}
}
