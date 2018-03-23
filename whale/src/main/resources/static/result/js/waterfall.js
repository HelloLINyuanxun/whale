var waterBasic = (function () {
    function init() {
        var nodeWidth = $(".item").outerWidth(true), colNum = parseInt($(window).width() / nodeWidth),
            colSumHeight = [];
        for (var i = 0; i < colNum; i++) {
            colSumHeight.push(0);
        }
        $(".item")
            .each(
                function () {
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
                        left: 25 + nodeWidth * idx,
                        top: minSumHeight
                    })
                    // 更新solSumHeight
                    colSumHeight[idx] = colSumHeight[idx]
                        + $cur.outerHeight(true);
                })
    }

    // 设置窗口改变时也能重新加载
    $(window).on("resize", function () {
        init();
    })
    return {
        init: init
    }
})();
waterBasic.init();