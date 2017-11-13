package bird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;


public class Column {
	BufferedImage image;
	int x, y;// 柱子的位置是柱子的中心点
	int width, height;
	int gap;// 柱子的缝隙
	int distance;// 柱子的间距

	// n表示第几个柱子
	public Column(int n) throws Exception {
		distance = 255;// 距离
		gap = 144;// 缝隙
		image=ImageIO.read(new File("image/column.png"));
		width = image.getWidth();
		height = image.getHeight();
		
		
		x = 432 + width / 2 + distance * (n - 1);// 柱子的出场位置
		Random r = new Random();
		y = r.nextInt((460 - gap / 2) - (40 + gap / 2)) + (40 + gap / 2);// 柱子距离上下边界至少要留40px距离
	}

	// 在柱子类Column中增加移动的方法step()
	// 在主循环中调用step()方法
	public void step() {
		x--;
		if (x <= -width / 2) {
			x = distance * 2 - width / 2;// ?
			Random r = new Random();
			y = r.nextInt((460 - gap / 2) - (40 + gap / 2)) + (40 + gap / 2);
		}
	}

	// 在柱子的内部添加碰撞检查方法
	// 检查当前柱子是否被鸟撞上，true表示撞上
	public boolean hitBy(Bird bird) {
		// 参数bird用来传递，被检查的鸟数据
		int x1 = x - width / 2 - bird.size / 2;
		int x2 = x + width / 2 + bird.size / 2;
		int y1 = y - gap / 2 + bird.size / 2;
		int y2 = y + gap / 2 - bird.size / 2;
		// 如果在柱子的范围之内(x1<bird.x<x2)
		// 如果在缝隙中 (y1<bird.y<y2) 返回 false
		// 否则返回true
		if (x1 < bird.x && bird.x < x2) {// 在柱子的范围之内
			if (y1 < bird.y && bird.y < y2) {
				return false;// 在缝隙之中 没有碰到
			}
			return true;// 柱子范围内，不在缝隙之中    还原true
		}
		return false;// 不在柱子范围之内
	}
}// Column类的结束