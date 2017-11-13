package bird;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Game extends JPanel {// MyPanel
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage background;
	Bird bird;
	Ground ground;
	Column column1, column2;
	int score;
	int state; // 游戏状态
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	BufferedImage startImage;
	BufferedImage gameOverImage;

	public Game() throws Exception {
		
		background=ImageIO.read(new File("image/bg.png"));
		bird = new Bird();// 调用构造器创建鸟对象，初始化数据
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		score = 0;

		state = START;
		
		
		gameOverImage=ImageIO.read(new File("image/gameover.png"));
		
		
		
		startImage=ImageIO.read(new File("image/start.png"));
	}// Game()构造器结束

	// 自定义绘制
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		// x1,y1 代表column1的绘制位置
		int x1 = column1.x - column1.width / 2;
		int y1 = column1.y - column1.height / 2;
		g.drawImage(column1.image, x1, y1, null);
		int x2 = column2.x - column2.width / 2;
		int y2 = column2.y - column2.height / 2;
		g.drawImage(column2.image, x2, y2, null);
		// 绘制 地面
		g.drawImage(ground.image, ground.x, ground.y, null);
		// 绘制分数，score+"" 是将整数score转化为字符串
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 60);
		g.setFont(f);
		// // 设置半透明的阴影色，绘制阴影
		g.setColor(new Color(0x88000000, true));
		g.drawString(score + "", 190, 80);
		// 设置白色前景色，绘制前景数字
		g.setColor(Color.WHITE);
		g.drawString(score + "", 190 - 3, 80 - 3);

		// // 绘制鸟, x,y是贴图位置，用鸟的位置换算得到
		int x = bird.x - bird.width / 2;
		int y = bird.y - bird.height / 2;
		
		// 以鸟的中心点旋转绘图坐标系:g2.rotate()
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, x, y, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		
		// 绘制鸟的碰撞检查范围
		g.drawRect(bird.x - bird.size / 2, bird.y - bird.size / 2, bird.size,
				bird.size);
		g.drawRect(column1.x - column1.width / 2, column1.y - column1.height
				/ 2, column1.width, column1.height);
		g.drawRect(column1.x - column1.width / 2, column1.y - column1.gap / 2,
				column1.width, column1.gap);
		g.drawRect(column2.x - column2.width / 2, column2.y - column2.height
				/ 2, column2.width, column2.height);
		g.drawRect(column2.x - column2.width / 2, column2.y - column2.gap / 2,
				column2.width, column2.gap);
		switch (state) {
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameOverImage, 0, 0, null);
			break;
		}
	}

	// 在Game中添加新方法action(), 主循环
	// 在main方法中调用action()方法执行主循环
	public void action() throws Exception {
		// 创建鼠标监听器（遵守约定）
		// Mouse: Jerry Listener:监听器 Adapter 适配器
		MouseListener l = new MouseAdapter() {
			// Java约定的方法，在鼠标被按下（Press）以后
			// 会执行这个方法。
			public void mousePressed(MouseEvent e) {
				switch (state) {
				case START:
					state = RUNNING;
					bird.flappy();
					break;
				case RUNNING:
					bird.flappy();
					break;
				case GAME_OVER:
					column1.x = 432 + column1.width / 2;
					column2.x = 432 + column1.width / 2 + 255;
					
					bird.y = 280;
					bird.alpha = 0;
					score = 0;
					state = START;
					break;
				}
			}
		};
		// this这个，代表当前这个面板Game
		// 将监听器l注册到当前面板，l被挂接到当前面板上
		// 当有鼠标在Game面板上点击，就会执行l引用对象
		// 的 mousePressed（）方法
		this.addMouseListener(l);
		while (true) {
			switch (state) {
			case START:
				bird.fly();
				ground.step();
				break;
			case RUNNING:
				bird.step();
				bird.fly();
				column1.step();
				column2.step();
				ground.step();
				if (column1.hitBy(bird) || column2.hitBy(bird)
						|| ground.hitBy(bird)) {
					state = GAME_OVER;
				}
				if (bird.x == column1.x || bird.x == column2.x) {
					score++;// 计分， ||或者
				}
				break;
			case GAME_OVER:
//				 bird.fly();
			}
			repaint();
			Thread.sleep(1000 / 60);
		}
	}
}// Game类的结束

