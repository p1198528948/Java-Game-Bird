package bird;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Ground {
	BufferedImage image;
	int x, y;// �����λ���ǵ������Ͻ�����

	public Ground() throws Exception {
		image=ImageIO.read(new File("image/ground.png"));
		x = 0;
		y = 500;
	}// Ground() ����������

	// ��Ground���ڲ���ӷ��� step(), ʵ���ƶ�һ���Ĺ���
	public void step() {
		x--;
		if (x == -108) {
			x = 0;
		}
	}

	// ��Ground������� ��ײ��鷽��
	public boolean hitBy(Bird bird) {
		return bird.y > y - bird.size / 2;
	}
}