<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>demo water-basic</title>
<script src="../asset/result/js/echarts.common.min.js"></script>
<script src="../asset/result/js/echarts-wordcloud.js"></script>
<link rel="stylesheet" href="../asset/result/bootstrap/css/bootstrap.min.css"
	type="text/css" />
<script type="text/javascript"
	src="../asset/result/bootstrap/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="../asset/result/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../asset/result/css/flip.css">

<style type="text/css">
img {
	display: block;
	width: 100%;
}

.item-info {
	width: 200px;
}

.item-info p.item-name {
	font-size: 0.9em;
	font-weight: 700;
	color: #717171;
	margin: 0.6em 0.6em;
	line-height: 1.5em;
}

.item-info span.item-price {
	margin-left: 0.5em;
	float: left;
	font-size: 20px;
	font-weight: 400;
	color: #6495ED;
	font-family: Verdana;
}

.water-basic {
	position: relative;
	position: relative;
}

.item {
	border: 1px solid #dedede;
	position: absolute;
	width: 200px;
	margin: 5px;
	transition: all 1s;
}
</style>

</head>
<body>




	<div class="water-basic">
		<c:forEach items="${request.bigGoods}" var="GoodsInfo">
			<div class="item">
				<div class="flip-container">
					<div class="flipper">
						<div class="front">
							<img src="${GoodsInfo.imgUrl}" />
						</div>
						<div class="back" style="width:200px;height:300px;">
							<div id="b${GoodsInfo.goodsId}"
								style="width: 200px;height: 300px;"></div>
							<script type="text/javascript">
								var myChart = echarts
										.init(document
												.getElementById('b${GoodsInfo.goodsId}'));
								option = {
									tooltip : {},
									series : [ {
										type : 'wordCloud',
										gridSize : 20,
										sizeRange : [ 12, 50 ],
										rotationRange : [ 0, 0 ],
										shape : 'circle',
										textStyle : {
											normal : {
												color : function() {
													return 'rgb('
															+ [
																	Math
																			.round(Math
																					.random() * 160),
																	Math
																			.round(Math
																					.random() * 160),
																	Math
																			.round(Math
																					.random() * 160) ]
																	.join(',')
															+ ')';
												}
											},
											emphasis : {
												shadowBlur : 10,
												shadowColor : '#333'
											}
										},
										data : [ 	
										<c:forEach items="${GoodsInfo.wordFrequenciesList}" var="wordFrequencies">
																		{
										name : '${wordFrequencies.getWord()}',
										value : ${wordFrequencies.getFrequency()}
									},
    
										</c:forEach>
										{
            name: 'Farrah Abraham',
            value: 366
        }, {
            name: 'Rita Ora',
            value: 360
        }, {
            name: 'Serena Williams',
            value: 282
        }, {
            name: 'NCAA baseball tournament',
            value: 273
        }, {
            name: 'Point Break',
            value: 265
        } ]
									} ]
								};
								myChart.setOption(option);
							</script>
						</div>

					</div>
				</div>
				<div class="item-info">
					<p class="item-name">${GoodsInfo.goodsName}</p>
					<div class="">
						<span class="item-price"> <em>￥</em> <i>${GoodsInfo.goodsPrice}</i>
						</span>
						<div id="${GoodsInfo.goodsId}" style="width: 100%;height: 150px;">
						</div>
						<script type="text/javascript">
							var myChart = echarts.init(document
									.getElementById('${GoodsInfo.goodsId}'));
							option = {
								tooltip : {
									trigger : 'item',
									formatter : "{a} <br/>{b}: {c} ({d}%)",
								},
								color : [ '#fdd922', '#ff7878', '#46aad7' ],
								series : [ {
									center : [ 104, 60 ],
									name : '评价比率',
									type : 'pie',
									radius : [ '30%', '40%' ],

									data : [ {
										value : ${GoodsInfo.midNum},
										name : '中性'
									}, {
										value : ${GoodsInfo.negNum},
										name : '消极'
									}, {
										value : ${GoodsInfo.posNum},
										name : '积极'
									}, ]
								} ]
							};
							myChart.setOption(option);
						</script>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>


	<script type="text/javascript">
		var waterBasic = (function() {
			function init() {
				var nodeWidth = $(".item").outerWidth(true), colNum = parseInt($(
						window).width()
						/ nodeWidth), colSumHeight = [];
				for (var i = 0; i < colNum; i++) {
					colSumHeight.push(0);
				}
				$(".item")
						.each(
								function() {
									var $cur = $(this), idx = 0, minSumHeight = colSumHeight[0];
									// 获取到solSumHeight中的最小高度
									for (var i = 0; i < colSumHeight.length; i++) {
										if (minSumHeight > colSumHeight[i]) {
											minSumHeight = colSumHeight[i];
											idx = i;
										}
									}
									// 设置各个item的css属性
									$cur.css({
										left : 25 + nodeWidth * idx,
										top : minSumHeight
									})
									// 更新solSumHeight
									colSumHeight[idx] = colSumHeight[idx]
											+ $cur.outerHeight(true);
								})
			}
			// 设置窗口改变时也能重新加载
			$(window).on("resize", function() {
				init();
			})
			return {
				init : init
			}
		})();
		waterBasic.init();
	</script>
</body>
</html>