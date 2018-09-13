//others------------------
ADMIN = "admin";//系统管理员账号
ADMIN_ROLE_ID = 1;//系统最高管理员

All = "all";

TREE_DEFAULT_LIMIT = 20; //树默认每页显示记录数

PAGE_LIMIT = 100;//分页条数

EXPORT_MAX = 5000;//导出excel最多记录数

//查询类型
SEARCH_SELF='-1';	//自定义
SEARCH_MIN = '0';
SEARCH_HOUR = '1';
SEARCH_DATE = "2";   //日
SEARCH_MONTH = "3";  //月
SEARCH_YEAR="4";
SEARCH_BETWEEN = "10"; //任意时段
SEARCH_SINGGLE = "11";
	
LEFT_TREE_HEIGHT_GAP = 56; //左边树中tab+toolbar的高度
LEFT_TREE_PAGINGBAR_HEIGHT = 20;//分页工具条高度

ROWSPLIT = "@"; // 分隔符
COLUMNSPLIT = "#"; 
NO_DATA_TEXT = "--";

PASSWORD_SECRETKEY = "charging";//密码加密密钥


//-----------菜单样式----------
HEADER_HEIGHT = 50;
FOOTER_HEIGHT = 40;

BTN_BAR_HEIGHT = 36;

//-----------地图打点样式----------
LABEL_OFFSET_X1=10;
LABEL_OFFSET_X2=7;
LABEL_OFFSET_X3=3;
LABEL_OFFSET_Y=4;
LABEL_STYLE = {
	 color : "white",
	 backgroundColor:"transparent",
	 borderColor:"transparent",
	 fontSize : "12px",
	 height : "20px",
	 lineHeight : "20px",
	 fontFamily:"微软雅黑"
 }

UPLOADIMG_MAX_SIZE = 4;//上传图片最大大小4M

var REGBOX = {
		regEmail : /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,//邮箱
		regName : /^[a-z0-9_-]{3,16}$/,//用户名
//		regMobile : /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,//手机
		regMobile:/^1[34578]\d{9}$/,//手机
		//regMobile : /^(1)+\d{10}$/,//手机
		regTel : /^0[\d]{2,3}-[\d]{7,8}$/,
		//身份证正则表达式(15位) 
		regCard15 : /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/,
		//身份证正则表达式(18位) 
		regCard18 : /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
		regNum:/^[0-9]*$/,
		//中文
		regCn:/^[\u4e00-\u9fa5]+$/,
		regMoney:/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){0,2})?$/,
		regBm:/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){0,4})?$/,
		regP:/^([1-9]\d{0,3}|0)(\.\d{0,4}){0,1}$/
}

//------错误类型------
SESSION_TIMEOUT = 1;//会话结束
ACCESS_DENIED = 2;//无操作权限
DATAAREA_DENIED = 3;//无数据范围权限
SESSION_CHANGE = 4;//账号在其它地方登录
JSON_ERROR = 201;//异常信息

LOGOUT_URL = "frame/logout.action";
LOGIN_URL = "frame/login.action";
MAIN_URL = "main/main.action";
HOMEPAGE_URL = "main/homepage.action";
//HOME_INIT_URL = "main/main.action";

//------缓存KEY------
//STORAGE_KEY_EID = "hl_eid";//当前企业编码
//STORAGE_KEY_EDISC = "hl_edisc";//当前企业名称
STORAGE_KEY_ACTIVETABURL = "activeTabUrl";//当前tab页的url
STORAGE_KEY_ACTIVETABID = "activeTabid";//当前tabid


STORAGE_KEY_QUERY = "queryParam_";

//-------前端页面权限按钮id前缀------------
BUTTONDEF_ = "buttondef_";

//页面跳转延时时间（毫秒）
HREF_TIMEOUT_DELAY = 500;//

//判断设备运行状态
RUNNING = 1//运行
STOP=2//停机
STANDBY_INIT=3//初始待机
KEY_POWEROFF=4//按键关机
STANDBY=5//待机
EMERGENCY_SYOP=6//紧急停机
STARTING=7//启动中
RUNNING_FAULT=8//故障运行
RUNNING_ALARM=9//告警运行
RUNNING_DERATE=10//降额运行
RUNNING_DISPATCH=11//调度运行
COMM_FAULT=12//通信故障

/**
 * 设备状态
 */
DEVICE_STATUS_NORMAL = 1;//正常
DEVICE_STATUS_WARING = 2;//告警
DEVICE_STATUS_FAULT = 3;//故障
DEVICE_STATUS_OFFLINE = 4;//离线

DEVICE_STATUS_WARING_FAULT = 5;//告警+故障
DEVICE_STATUS_WARING_OFFLINE = 6;//告警+离线
DEVICE_STATUS_FAULT_OFFLINE = 7;//故障+离线
DEVICE_STATUS_WARING_FAULT_OFFLINE = 8;//告警+故障+离线


SINGLE_PHASE = 1;//单相
THREE_PHASE = 2;//三相

SUCCESS = "success";


