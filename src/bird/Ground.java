package bird;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Ground {
	BufferedImage image;
	int x, y;// 地面的位置是地面左上角坐标

	public Ground() throws Exception {
		image=ImageIO.read(new File("image/ground.png"));
		x = 0;
		y = 500;
	}// Ground() 构造器结束

	// 在Ground类内部添加方法 step(), 实现移动一步的功能
	public void step() {
		x--;
		if (x == -108) {
			x = 0;
		}
	}

	// 在Ground类中添加 碰撞检查方法
	public boolean hitBy(Bird bird) {
		return bird.y > y - bird.size / 2;
	}
}