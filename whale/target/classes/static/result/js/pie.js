var myChart = echarts.init(document
    .getElementById([[${GoodsInfo.goodsId}]]));
option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)",
    },
    color: ['#fdd922', '#ff7878', '#46aad7'],
    series: [{
        center: [104, 60],
        name: '评价比率',
        type: 'pie',
        radius: ['30%', '40%'],

        data: [{
            value: [[${GoodsInfo.midNum}]],
            name: '中性'
        }, {
            value: [[${GoodsInfo.negNum}]],
            name: '消极'
        }, {
            value: [[${GoodsInfo.posNum}]],
            name: '积极'
        },]
    }]
};
myChart.setOption(option);
