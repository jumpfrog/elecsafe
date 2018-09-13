var countChart;
$(function(){
	//$("#countChartDiv").height(window.parent.window.innerHeight-700)
setTimeout(function(){
	initSize();
	initChart();
}, 800)
})

function initSize(){
	$("#countChartDiv").height($("#homePageChartDiv").height())
}
function initChart(){
	countChart =  echarts.init(document.getElementById('countChartDiv'),'customed');
	countChart.showLoading({text: '正在努力的读取数据中...'  });
	countChart.setOption(countOption);
	countChart.hideLoading();
}

countOption = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        type: 'scroll',
        x: 'right',
        top:'22%',
       // left:'70%',
        itemWidth:10,
        itemHeight:10,
        data:['报警点数','报警次数','已排除隐患','待排除隐患']
    },
    color:['#f39c12', '#dd4b39','#00a65a','#00c0ef'] ,
    series: [
        {
            name:'服务数据',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center',
                   // formatter: '{b}: {c} ({d}%)'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:homePageContData
        }
    ]
};
