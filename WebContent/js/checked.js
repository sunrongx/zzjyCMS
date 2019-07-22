//如果外面想用里面的变量canvas和context那么需要在方法外声明
var canvas;
var context;
var txts=[];
// 打开页面直接调用方法
draw();
// 添加点击事件
canvas.onclick = function() {
	//当我点击的画布canvas的时候,需要把原来的全部删掉,然后重新绘制
	context.clearRect(0, 0, 210, 70); //删掉原来的canvas
	//重新绘制
	draw();
}
// 将所有的绘制封装成方法
function draw() {
	//因为变量在方法外声明,这里就可以直接使用
	canvas = document.getElementById("canvas"); //演员
	context = canvas.getContext("2d"); //演员的舞台
	//也可以在这里设置画布的高和宽,就是不能在css中设置
	// canvas.width=120;

	//开始绘制:
	// 0,0:起始坐标
	// 120,40:结束坐标
	context.strokeRect(0, 0, 210, 70);
	// 设置一个包含数字和字母的数组
	// aCode.length---->36
	// aCode[i]----->i应该为随机索引就可以实现随机生成验证码
	var aCode = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
		'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '你', '我', '他'
	];
	// 产生四个随机的索引值
	for (var i = 0; i < 4; i++) {
		// Math.random():生成0~1的随机小数
		// aCode.length:数组长度
		// Math.floor():向下取整,这样不会使数组下标越界
		var index = Math.floor(Math.random() * aCode.length);
		// 产生随机内容
		var txt = aCode[index];
		txts[i]=txt;
		// alert(txts[i]);
		// 设置字体样式
		context.font = "bold 30px 微软雅黑";
		// 设置水平方向坐标
		var x = 40 + 40 * i;
		// 设置垂直方向坐标,随机移动,在20到30之间
		var y = 30 + Math.random() * 10;

		//想要倾斜
		/* 
			1、需要让画布移动位置
			2、需要画布旋转
			3、绘制写入数组随机内容
			4、画布旋转回正常位置
			5、画布回到原来位置
		 */
		//前提需要设置需要旋转的弧度,随机值生成
		var deg = Math.random() * 210 * Math.PI / 180;
		// 1
		context.translate(x, y);
		// 2
		context.rotate(deg);
		// 3
		context.fillText(txt, 0, 0);
		// 4 
		context.rotate(-deg);
		// 5
		context.translate(-x, -y);

		// 添加随机字体颜色
		context.fillStyle = getColor();
	}

	// 随机颜色的方法
	function getColor() {
		// 设置红色
		var r = Math.floor(Math.random() * 256);
		// 设置绿色
		var g = Math.floor(Math.random() * 256);
		// 设置蓝色
		var b = Math.floor(Math.random() * 256);
		//返回rgb()取色方法
		return "rgb(" + r + "," + g + "," + b + ")";
	}

	// 制作干扰线
	for (var i = 0; i < 8; i++) {
		//首先要声明一个开始的路径
		context.beginPath();
		// 干扰线开始坐标只要在canvas中就可以了,所以x和y都随机就好了
		context.moveTo(Math.random() * 210, Math.random() * 70);
		// 同理干扰线结束也在canvas中就行
		context.lineTo(Math.random() * 210, Math.random() * 70);
		// 干扰线的绘制颜色
		context.strokeStyle = getColor();
		// 开始绘制
		context.stroke();
	}
}


// 制作干扰点,和线一样
for (var i = 0; i < 20; i++) {
	//首先要声明一个开始的路径
	context.beginPath();
	//只需要把干扰点的x和y设置成固定的值就可以了
	var x = Math.random() * 210;
	var y = Math.random() * 70;
	// 干扰点开始坐标只要在canvas中就可以了,所以x和y都随机就好了
	context.moveTo(x, y);
	// 干扰点结束也在canvas中,比开始的坐标大1就成了点
	context.lineTo(x + 1, y + 1);
	// 干扰点的绘制颜色
	context.strokeStyle = getColor();
	// 开始绘制
	context.stroke();
}