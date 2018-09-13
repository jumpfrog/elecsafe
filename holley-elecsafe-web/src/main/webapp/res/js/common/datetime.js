/***时间常量定义***************************************************************/
TIME_LONG = 'YYYY-MM-DD HH:mm:ss';
TIME_NO_SEC = 'YYYY-MM-DD HH:mm';
TIME_SHORT = 'YYYY-MM-DD';
YEAR_MONTH = 'YYYY-MM';
YEAR_LONG = 'YYYY';

/***时间公共方法***************************************************************/
/**
 * 获取当前时间戳
 * @returns
 */
function getTimeStamp(){
	return new Date().getTime();
}

/**
 * 后端传回来的日期格式成字符串
 * @param date
 * @returns
 */
function getFormatDate(datestr){
	str="";
	if(!date)return "";
	if(date.indexOf("T") != -1){
		str = date.replace("T"," ")
	}else{
		str = date;
	}
	return str;
}

/**
 * 解析日期为字符串
 * @param date
 * @param format
 * TIME_LONG = 'YYYY-MM-DD HH:mm:ss';
   TIME_NO_SEC = 'YYYY-MM-DD HH:mm';
   TIME_SHORT = 'YYYY-MM-DD';
   YEAR_MONTH = 'YYYY-MM';
   YEAR_LONG = 'YYYY';
 * @returns
 */
function parseDateToStr(date,format){
	var dt = new Date(date);       
	var year = dt.getFullYear();   
	var month = dt.getMonth() + 1;           //显示月份比实际月份小1,所以要加1   
	var day = dt.getDate();   
	var hour = dt.getHours();
	var minute = dt.getMinutes();
    var second = dt.getSeconds();
	month = month<10 ? "0"+month : month;       //数字<10，实际显示为，如5，要改成05   
	day = day<10 ? "0"+day : day;
	hour = hour<10 ? "0"+hour : hour; 
	minute = minute<10 ? "0"+minute : minute; 
	second = second<10 ? "0"+second : second;
	var result = "";
	if(format == TIME_LONG){
		result = year + "-" + month + "-" + day +" "+ hour +":" + minute + ":" + second;
	}else if(format == TIME_NO_SEC){
		result = year + "-" + month + "-" + day +" "+ hour +":" + minute;
	}else if(format == TIME_SHORT){
		result = year + "-" + month + "-" + day;
	}else if(format == YEAR_MONTH){
		result = year + "-" + month;
	}else if(format == YEAR_LONG){
		result = year;
	}else{
		result = year + "-" + month + "-" + day;
	}
	return result;	
}

/**
 * yyyy-mm-dd转日期
 * @param str
 * @returns
 */
function shortStrToDate(str){
	if(isEmpty(str))return null;
	str = str.replace("-","/").replace("-","/");
	return new Date(str);
}

/**
 * 字符串转日期
 * @returns
 */
/*function strToDate(str){
	if(isEmpty(str))return null;
	str = str.replace("-","/").replace("-","/");
	return new Date(str);
}*/

/**
 * 获取本月日期
 * @returns
 */
function getThisMonthDay(){
	var date = new Date();
	return new Date(date.getFullYear(),date.getMonth(),1);
}

/**
 * 增加秒
 * @param date
 * @param amount
 * @returns
 */
//function addSeconds(date, amount){
//	if(!date)return null;
//	var dt = date.setSeconds(date.getSeconds() + amount);
//	return dt;
//}



/**
 * 增加分钟
 * @param date
 * @param amount
 * @returns
 */
//function addMinutes(date, amount){
//	if(!date)return null;
//	var dt = date.setMinutes(date.getMinutes() + amount);
//	return dt;
//}

/**
 * 增加小时
 * @param date
 * @param amount
 * @returns
 */
//function addHours(date, amount){
//	if(!date)return null;
//	var dt = date.setHours(date.getHours() + amount);
//	return dt;
//}

/**
 * 增加天数
 * @param date
 * @param amount
 * @returns
 */
function addDays(date, amount){
	if(!date)return null;
	var dt = date.setDate(date.getDate()+amount);
	return dt;
}

/**
 * 增加月份
 * @param date
 * @param amount
 * @returns
 */
function addMonths(date, amount){
	if(!date)return null;
	var dt = date.setMonth(date.getMonth()+amount);
	return new Date(dt);
}

/**
 * 增加年
 * @param date
 * @param amount
 * @returns
 */
function addYears(date,amount){
	if(!date)return null;
	var dt = date.setFullYear(date.getFullYear()+amount);
	return dt;
}

/**
 * yyyy-MM-dd hh:mm:ss字符串截取hh:mm:ss
 * @param str
 * @returns
 */
function getHHMMSS(str){
	return str.substring(11)
}

/**
* 获取当前月的最后一天
* @param date
* @returns {String}
*/
function lastDate(date){
	var arr = date.split('-');
	var year = arr[0]; //获取当前日期的年份
  var month = arr[1]; //获取当前日期的月份
  var day = arr[2]; //获取当前日期的日
  var days = new Date(year, month, 0);
  days = days.getDate(); //获取当前日期中的月的天数
  var year2 = year;
  var month2 = parseInt(month);
  if (month2 == 13) {
      year2 = parseInt(year2) + 1;
      month2 = 1;
  }
  var day2 = days;
  var days2 = new Date(year2, month2, 0);
  days2 = days2.getDate();
  if (day2 > days2) {
      day2 = days2;
  }
  if (month2 < 10) {
      month2 = '0' + month2;
  }
  var t2 = year2 + '-' + month2 + '-' + day2;
  return t2;
}

/**
* 获取当前月的第一天
* @returns {Date}
*/
function getCurrentMonth(){
	 var date=new Date;
	 var year=date.getFullYear(); 
	 var month=date.getMonth()+1;
	 month =(month<10 ? "0"+month:month); 
	 var mydate = (year.toString()+'-'+month.toString());
	 return mydate;
}

/**
 * 计算两个任意时间中间的间隔天数
 * 
 * @param startday：String
 * @param endday:String
 * @return
 */
function getIntervalDays(startday,endday){
	if(isEmpty(startday) || isEmpty(endday))return 0;
    var time1 = Date.parse(new Date(startday));
    var time2 = Date.parse(new Date(endday));
    var days = Math.abs(parseInt((time2 - time1)/1000/3600/24));
    return  days;
};

/***初始化时间控件***************************************************************/

/***
 * 初始化单个时间控件(日)
 * @param oneTimeDate
 */

function initDate(datetime,defaultDate){
	var datetimepicker = datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_SHORT,
    	defaultDate:defaultDate
    });
	datetimepicker.on("dp.change",function (e) {
		$(datetime).trigger("blur");
    });
}
function initDateCallBack(datetime,defaultDate,fn){
	var datetimepicker = datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_SHORT,
    	defaultDate:defaultDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'bottom'
        }
    });
	if(fn){
		datetimepicker.on("dp.change",function (e) {
			fn();
	    });	
	}
}
function initDateLeft(datetime,defaultDate){
	datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_SHORT,
    	defaultDate:defaultDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'bottom'
        }
    });
}
function initDateTop(datetime,defaultDate){
	datetime.datetimepicker({
		locale: 'zh-CN',
		format: TIME_SHORT,
		defaultDate:defaultDate,
		widgetPositioning: {
			vertical: 'top'
		}
	});
}


/***
 * 初始化单个时间控件(月)
 * @param oneTimeDate
 */
function initMonth(datetime,defaultDate){
	var datetimepicker = datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	defaultDate:defaultDate
    });
	datetimepicker.on("dp.change",function (e) {
		$(datetime).trigger("blur");
    });
}

function initMonthLeft(datetime,defaultDate){
	datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	defaultDate:defaultDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
        }
    });
}

/***
 * 初始化单个时间控件(年)
 * @param oneTimeDate
 */
function initYear(datetime,defaultDate){
	var datetimepicker = datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_LONG,
    	defaultDate:defaultDate
    });
	datetimepicker.on("dp.change",function (e) {
		$(datetime).trigger("blur");
    });
}

function initYearLeft(datetime,defaultDate){
	datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_LONG,
    	defaultDate:defaultDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
        }
    });
}

function initStartEndDateDefault(startTimeDate,endTimeDate,format){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: format,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
        }
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: format,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
        },
    	useCurrent: false //Important! See issue #1075
    });
	startTimeDate.on("dp.change",function (e) {
		if(e.date == null)return;
		endTimeDate.data("DateTimePicker").minDate(e.date);
	});
	endTimeDate.on("dp.change",function (e) {
		if(e.date == null)return;
		startTimeDate.data("DateTimePicker").maxDate(e.date);
	});
}
function setDateTime(timeDate,value){
	timeDate.data("DateTimePicker").date(value);
}
/***
 * 初始化时间控件(日，起止时间)
 * @param searchStartTimeDate
 * @param searchEndTimeDate
 */
function initStartEndDate(startTimeDate,endTimeDate,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_SHORT,
    	defaultDate: defaultStartDate
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_SHORT,
    	defaultDate: defaultEndDate,
    	useCurrent: false //Important! See issue #1075
    });
}

function initStartEndDateLeft(startTimeDate,endTimeDate,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
		locale: 'zh-CN',
		format: TIME_SHORT,
		defaultDate: defaultStartDate,
	    widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
	    }
	});
	endTimeDate.datetimepicker({
		locale: 'zh-CN',
		format: TIME_SHORT,
		defaultDate: defaultEndDate,
		widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
	    },
		useCurrent: false //Important! See issue #1075
	});
}

/***
 * 初始化时间控件()
 * @param searchStartTimeDate
 * @param searchEndTimeDate
 */
function initStartEndMonth(startTimeDate,endTimeDate,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	viewMode: 'months',
    	defaultDate: defaultStartDate
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	viewMode: 'months',
    	useCurrent: false, //Important! See issue #1075
    	defaultDate: defaultEndDate
    });
}

/***
 * 初始化时间控件()
 * @param searchStartTimeDate
 * @param searchEndTimeDate
 */
function initStartEndMonthLeft(startTimeDate,endTimeDate,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	viewMode: 'months',
    	defaultDate: defaultStartDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
	    }
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: YEAR_MONTH,
    	viewMode: 'months',
    	useCurrent: false, //Important! See issue #1075
    	defaultDate: defaultEndDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
	    }
    });
}

/***
 * 初始化时间控件
 * @param searchStartTimeDate
 * @param searchEndTimeDate
 */
function initStartEndDateTime(startTimeDate,endTimeDate,format,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: format,
    	defaultDate: defaultStartDate
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: format,
    	defaultDate: defaultEndDate,
    	useCurrent: false //Important! See issue #1075
    });
}

/**
 * 初始化起止时间控件，格式自定义
 * @param startTimeDate
 * @param endTimeDate
 * @param formate
 * @param defaultStartDate
 * @param defaultEndDate
 * @returns
 */
function initStartEndDateTimeLeft(startTimeDate,endTimeDate,formate,defaultStartDate,defaultEndDate){
	startTimeDate.datetimepicker({
		locale: 'zh-CN',
		format: formate,
		defaultDate: defaultStartDate,
		widgetPositioning: {
			horizontal: 'left',
			vertical: 'auto'
		}
	});
	endTimeDate.datetimepicker({
		locale: 'zh-CN',
		format: formate,
		defaultDate: defaultEndDate,
		widgetPositioning: {
			horizontal: 'left',
			vertical: 'auto'
		},
		useCurrent: false //Important! See issue #1075
	});
}



/***
 * 初始化时间控件限制只能选择当前月
 * @param searchStartTimeDate
 * @param searchEndTimeDate
 */
function initStartDateEndDateTimeMin(startTimeDate,endTimeDate,formate,dateStr){
	startTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: formate
    });
	endTimeDate.datetimepicker({
    	locale: 'zh-CN',
    	format: formate,
    	 useCurrent: false //Important! See issue #1075
    });
	startTimeDate.on("dp.change",function (e) {
		if(e.date == null)return;
		startTimeDate.data("DateTimePicker").minDate(dateStr);
    });
}

/**
 * 初始化事件控件（yyyy-mm-dd hh:mm:ss）
 */
function initDateTime (datetime,defaultDate){
	datetime.datetimepicker({
    	locale: 'zh-CN',
    	format: TIME_LONG,
    	defaultDate:defaultDate,
    	widgetPositioning: {
            horizontal: 'left',
            vertical: 'auto'
        }
    });
}
/***记住时间参数***start**************************************************/

/**
 * 记住时间参数
 * 周期：逐时,逐日,逐月
 * @param circletype
 * @param datatime
 * @returns
 */
function setDateParam(circletype,datatime){
	if(circletype == CIRCLE_TYPE_HOUR){//逐时
		setQueryParam(QRY_PARAM_DATE,datatime);
	}else if(circletype == CIRCLE_TYPE_DAY){//逐日
		setQueryParam(QRY_PARAM_MONTH,datatime);
	}else if(circletype == CIRCLE_TYPE_MONTH){//逐月
		setQueryParam(QRY_PARAM_YEAR,datatime);
	}
}

/**
 * 获取时间参数
 * 周期：逐时,逐日,逐月
 * @param circletype
 * @returns
 */
function getDateParam(circletype){
	var datatime;
	if(circletype == CIRCLE_TYPE_HOUR){//逐时
		datatime = getQueryParam(QRY_PARAM_DATE);
		if(isEmpty(datatime)){
			datatime = defaultDate;
		}
	}else if(circletype == CIRCLE_TYPE_DAY){//逐日
		datatime = getQueryParam(QRY_PARAM_MONTH);
		if(isEmpty(datatime)){
			datatime = defaultMonth;
		}
	}else if(circletype == CIRCLE_TYPE_MONTH){//逐月
		datatime = getQueryParam(QRY_PARAM_YEAR);
		if(isEmpty(datatime)){
			datatime = defaultYear;
		}
	}
	return datatime;
}


/**
 * 记住时间参数
 * 周期：自定义,月,年
 * @param circletype
 * @param startdate
 * @param enddate
 * @returns
 */
function setDateParam2(circletype,startdate,enddate){
	if(circletype == CIRCLE_TYPE_ZDY){//自定义
		setQueryParam(QRY_PARAM_STARTDATE_ZDY,startdate);
		setQueryParam(QRY_PARAM_ENDDATE_ZDY,enddate);
	}else if(circletype == CIRCLE_TYPE_MONTH){//月
		setQueryParam(QRY_PARAM_MONTH,startdate);
	}else if(circletype == CIRCLE_TYPE_YEAR){//年
		setQueryParam(QRY_PARAM_YEAR,startdate);
	}
}

/**
 * 获取时间参数
 * 周期：自定义,月,年
 * @param circletype
 * @returns
 */
function getDateParam2(circletype){
	var startdate;
	var enddate;
	if(circletype == CIRCLE_TYPE_ZDY){//自定义
		startdate = getQueryParam(QRY_PARAM_STARTDATE_ZDY);
		enddate = getQueryParam(QRY_PARAM_ENDDATE_ZDY);
		
		if(isEmpty(startdate)){
			startdate = defaultStartDate;
		}
		if(isEmpty(enddate)){
			enddate = defaultEndDate;
		}
	}else if(circletype == CIRCLE_TYPE_MONTH){//月
		startdate = getQueryParam(QRY_PARAM_MONTH);
		if(isEmpty(startdate)){
			startdate = defaultMonth;
		}
	}else if(circletype == CIRCLE_TYPE_YEAR){//年
		startdate = getQueryParam(QRY_PARAM_YEAR);
		if(isEmpty(startdate)){
			startdate = defaultYear;
		}
	}
	return {startdate:startdate,enddate:enddate};
}

/***记住时间参数***end**************************************************/
