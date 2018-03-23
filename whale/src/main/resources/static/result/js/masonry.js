/**
 * Created by Mac on 2017/1/7.
 */
function $(id) {
    //�ж�id������
    return typeof id === 'string'?document.getElementById(id):id;
}

//����ҳ�������
window.onload = function () {
    //�ٲ������� ��֤���Ĳ����ܹ��ҵ�������
    waterFall('main','box');
    
    //�������غ���
    window.onscroll = function ()
    {
        //�ж��Ƿ����
        if (checkWillLoad())
        {
            //���������
            var data = {'dataImg':[{'img':'23.jpg'},{'img':'24.jpg'},{'img':'25.jpg'},{'img':'26.jpg'},{'img':'27.jpg'},{'img':'28.jpg'}]};
            //��������
            for(var i=0; i<data.dataImg.length; i++)
            {
                //����������ĺ���
                var newBox = document.createElement('div');
                newBox.className = 'i';
                $('main').appendChild(newBox);
                //������������
                var newPic = document.createElement('div');
                newPic.className = 'pic';
                newBox.appendChild(newPic);
                //����img
                var newImg = document.createElement('img');
                newImg.src = 'images/' + data.dataImg[i].img;
                newPic.appendChild(newImg);
            }
            //�Ѹմ����ĺ����ٲ�������
            waterFall('main','box');
        }
    }
}

//ʵ���ٲ�������
//����:�ӵڶ��п�ʼ��ͼƬ,����ƴ������һ�и߶����ͼƬ����
function  waterFall(parent,box) {
    //�����Ӿ���
    //ͨ���������õ����е��Ӻ���
    var allBox = $(parent).getElementsByClassName(box);
    //������ӵĿ��
    var boxWidth = allBox[0].offsetWidth;
    //���������Ŀ��(�����߿�Ŀ��)
    var screenWidtn = document.body.offsetWidth;
    //������� //ȡ������ȡ��
    var cols = Math.floor( screenWidtn/boxWidth);
    //����ǩ����
    //���������ǩ���
    $(parent).style.width = boxWidth * cols + 'px';
    //����
    $(parent).style.margin = '0 auto';
    
    //�Ӻ��Ӷ�λ
    //����һ���߶�����,�����еĸ߶�
    var heightArr = [];
    //����
    for(var i = 0; i < allBox.length ;i++)
    {
        //���ÿ�����ӵĸ߶�
        var boxHeight = allBox[i].offsetHeight;
        //��һ�еĺ��Ӳ���Ҫ���¶�λ//ÿһ�еĺ�������������ͬ
        if(i<cols)
        {
            //��ӵ�һ�����к��Ӹ߶�
            heightArr.push(boxHeight);
        }
        else//ʣ�µĺ��Ӷ���Ҫ�ٲ�������
        {
            //�����ĺ��Ӹ߶�
            var minBoxHeight = Math.min.apply(this,heightArr);
            //�������Ӷ�Ӧ������
            var minBoxIndex = getMinBoxIndex(minBoxHeight,heightArr);
            //�����ٲ�����λ  ��������������ӵĸ߶�
            allBox[i].style.position = 'absolute';
            allBox[i].style.top = minBoxHeight + 'px';
            allBox[i].style.left = minBoxIndex * boxWidth +'px';
            //�ؼ�:����������߶�,ʹ��һ��ͼƬ�ڸ߶���������������߶ȵ�ͼƬ����ƴ��
            heightArr[minBoxIndex] += boxHeight;
        }
    }
}

//�������Ӷ�Ӧ����������
function getMinBoxIndex(val,arr) {
    for(var i in arr)
    {
        if(val == arr[i])
        {
            return i;
        }
    }
}

//���ظ������:����������·�����ͼƬ�ĸ߶�һ��ʱ����ˢ�³���
//�ж��Ƿ���ϼ�������
function checkWillLoad() {
    //ȡ�����к���
    var allBox = $('main').getElementsByClassName('box');
    //ȡ�����һ������
    var lastBox = allBox[allBox.length - 1];
    //������һ�����Ӹ߶ȵ�һ�� + �����������ͷ����ƫ��λ��
    var lastBoxDis = lastBox.offsetHeight * 0.5 + lastBox.offsetTop;
    //���������ĸ߶�
    // ע��:JS�������������������� һ��ֱ�׼ģʽ(����Ļ��document.body.offsetHeight)�ͻ���ģʽ(������������)
    var screenHeight =  document.documentElement.clientHeight;
    //ҳ��ƫ����Ļ�ĸ߶�
    var scrollTopHeight = document.body.scrollTop;
    //�ж�
    return lastBoxDis <= screenHeight + scrollTopHeight;
}
