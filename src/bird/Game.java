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
	int state; // ��Ϸ״̬
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	BufferedImage startImage;
	BufferedImage gameOverImage;

	public Game() throws Exception {
		
		background=ImageIO.read(new File("image/bg.png"));
		bird = new Bird();// ���ù�������������󣬳�ʼ������
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		score = 0;

		state = START;
		
		
		gameOverImage=ImageIO.read(new File("image/gameover.png"));
		
		
		
		startImage=ImageIO.read(new File("image/start.png"));
	}// Game()����������

	// �Զ������
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		// x1,y1 ����column1�Ļ���λ��
		int x1 = column1.x - column1.width / 2;
		int y1 = column1.y - column1.height / 2;
		g.drawImage(column1.image, x1, y1, null);
		int x2 = column2.x - column2.width / 2;
		int y2 = column2.y - column2.height / 2;
		g.drawImage(column2.image, x2, y2, null);
		// ���� ����
		g.drawImage(ground.image, ground.x, ground.y, null);
		// ���Ʒ�����score+"" �ǽ�����scoreת��Ϊ�ַ���
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 60);
		g.setFont(f);
		// // ���ð�͸������Ӱɫ��������Ӱ
		g.setColor(new Color(0x88000000, true));
		g.drawString(score + "", 190, 80);
		// ���ð�ɫǰ��ɫ������ǰ������
		g.setColor(Color.WHITE);
		g.drawString(score + "", 190 - 3, 80 - 3);

		// // ������, x,y����ͼλ�ã������λ�û���õ�
		int x = bird.x - bird.width / 2;
		int y = bird.y - bird.height / 2;
		
		// ��������ĵ���ת��ͼ����ϵ:g2.rotate()
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, x, y, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		
		// ���������ײ��鷶Χ
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

	// ��Game������·���action(), ��ѭ��
	// ��main�����е���action()����ִ����ѭ��
	public void action() throws Exception {
		// ������������������Լ����
		// Mouse: Jerry Listener:������ Adapter ������
		MouseListener l = new MouseAdapter() {
			// JavaԼ���ķ���������걻���£�Press���Ժ�
			// ��ִ�����������
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
		// this���������ǰ������Game
		// ��������lע�ᵽ��ǰ��壬l���ҽӵ���ǰ�����
		// ���������Game����ϵ�����ͻ�ִ��l���ö���
		// �� mousePressed��������
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
					score++;// �Ʒ֣� ||����
				}
				break;
			case GAME_OVER:
//				 bird.fly();
			}
			repaint();
			Thread.sleep(1000 / 60);
		}
	}
}// Game��Ľ���

