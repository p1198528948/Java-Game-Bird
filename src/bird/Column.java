package bird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;


public class Column {
	BufferedImage image;
	int x, y;// ���ӵ�λ�������ӵ����ĵ�
	int width, height;
	int gap;// ���ӵķ�϶
	int distance;// ���ӵļ��

	// n��ʾ�ڼ�������
	public Column(int n) throws Exception {
		distance = 255;// ����
		gap = 144;// ��϶
		image=ImageIO.read(new File("image/column.png"));
		width = image.getWidth();
		height = image.getHeight();
		
		
		x = 432 + width / 2 + distance * (n - 1);// ���ӵĳ���λ��
		Random r = new Random();
		y = r.nextInt((460 - gap / 2) - (40 + gap / 2)) + (40 + gap / 2);// ���Ӿ������±߽�����Ҫ��40px����
	}

	// ��������Column�������ƶ��ķ���step()
	// ����ѭ���е���step()����
	public void step() {
		x--;
		if (x <= -width / 2) {
			x = distance * 2 - width / 2;// ?
			Random r = new Random();
			y = r.nextInt((460 - gap / 2) - (40 + gap / 2)) + (40 + gap / 2);
		}
	}

	// �����ӵ��ڲ������ײ��鷽��
	// ��鵱ǰ�����Ƿ���ײ�ϣ�true��ʾײ��
	public boolean hitBy(Bird bird) {
		// ����bird�������ݣ�������������
		int x1 = x - width / 2 - bird.size / 2;
		int x2 = x + width / 2 + bird.size / 2;
		int y1 = y - gap / 2 + bird.size / 2;
		int y2 = y + gap / 2 - bird.size / 2;
		// ��������ӵķ�Χ֮��(x1<bird.x<x2)
		// ����ڷ�϶�� (y1<bird.y<y2) ���� false
		// ���򷵻�true
		if (x1 < bird.x && bird.x < x2) {// �����ӵķ�Χ֮��
			if (y1 < bird.y && bird.y < y2) {
				return false;// �ڷ�϶֮�� û������
			}
			return true;// ���ӷ�Χ�ڣ����ڷ�϶֮��    ��ԭtrue
		}
		return false;// �������ӷ�Χ֮��
	}
}// Column��Ľ���