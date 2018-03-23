
						var myChart = echarts.init(document.getElementById('item-echart'));
						option = {
							tooltip: {
								trigger: 'item',
								formatter: "{a} <br/>{b}: {c} ({d}%)"
							},
							color: ['#fdd922', '#ff7878', '#46aad7'],
							series: [{
								center: [104, 60],
								name: '评价比率',
								type: 'pie',
								radius: ['30%', '40%'],
								data: [{
										value: 335,
										name: '中性评论'
									},
									{
										value: 310,
										name: '消极评论'
									},
									{
										value: 234,
										name: '积性评论'
									},
								]
							}]
						};
						myChart.setOption(option);
					