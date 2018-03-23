function pubuliu(ulId, cols, liWidth) {
	var wrap = document.getElementById(ulId);
	var lis = wrap.getElementsByTagName("li");

	var li_h = [];
	for (var i = 0; i < lis.length; i++) {
		if (i < cols) {
			li_h[i] = lis[i].scrollHeight;
			// ��λԪ��
			lis[i].style.left = i * liWidth + "px";
			lis[i].style.top = "0px";
		} else {
			// ��ȡ������Сֵ
			var min_liH = Math.min.apply(Math, li_h);
			var key = getKeyByValue(li_h, min_liH);
			// �ض�����С�߶�
			li_h[key] = min_liH + lis[i].scrollHeight;

			// ��λԪ��
			lis[i].style.left = key * liWidth + "px";
			lis[i].style.top = min_liH + "px";
		}
	}
}

// ���������е�ֵ��ȡ����
function getKeyByValue(arr, value) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == value) {
			return i;
		}
	}
}
	