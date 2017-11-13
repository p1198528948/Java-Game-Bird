package bird;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Bird {
	BufferedImage image;
	int x, y;// 鸟的位置是鸟的中心点
	int width, height;
	int size;// 是鸟是碰撞检查范围。
	// 在Bird类内部添加动画帧数组
	BufferedImage[] images;// 只有数组变量，没有数组
	int index;
	// 在Bird类内部添加飞扬上抛运动属性
	double g;
	// 经过时间t以后，的运动速度
	double vs;
	// 两次间隔时间，移动的距离：位移
	double t;
	
	double v;
	// 初始速度
	double v0;
	double s;
	// 在Bird类中增加鸟的倾角
	double alpha;

	public Bird() throws Exception {
		image=ImageIO.read(new File("image/0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 35;
		// 在Bird构造器里面，添加创建数组，读取动画帧的代码
		// 创建数组：鸡蛋篮子, 有8个空位置，序号：0~7
		images = new BufferedImage[8];// {^,^,^,^,^,^,^,^}
		for (int i = 0; i < 8; i++) {
			// int i = 0 1 2 3 4 5 6 7;初始i=0 结束i<8 每次1++
			
			images[i]=ImageIO.read(new File("image/"+i+".png"));
		}
		// 凡是处理 与次数或个数有关的循环一般使用for处理
		index = 0;
		// 在Bird构造器中添加飞扬属性初始化
		
		//g = 4.5;// 重力加速度
		g=4.5;
		
		vs = 20; // 初始速度
		t = 0.25;//

		v0 = vs;
		s = 0;
		v = 0;
		// 在Bird构造器中添加初始化倾角属性
		 alpha = 0;
	} // Bird()构造器结束

	// 在Bird类中添加移动一步的方法step
	public void step() {
		s = v0 * t - g * t * t / 2;
		v = v0 - g * t;
		v0 = v;
		y = (int) (y - s);
		alpha = Math.atan(s /4.5);
		if (y > 500 - size / 2) {
			y = 500 - size / 2 + 1;
		}
	}

	// 从当前位置开始向上重新飞扬（上抛）
	public void flappy() {
		// 使鸟从当前位置，重新以vs速度上抛
		v0 = 20;
	}

	// 在Bird类中添加飞翔方法fly
	public void fly() {
		index++;
		// images[0], images[1], images[2],....
		image = images[index / 8 % 8];
	}
}
