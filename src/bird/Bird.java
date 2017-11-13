package bird;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Bird {
	BufferedImage image;
	int x, y;// ���λ����������ĵ�
	int width, height;
	int size;// ��������ײ��鷶Χ��
	// ��Bird���ڲ���Ӷ���֡����
	BufferedImage[] images;// ֻ�����������û������
	int index;
	// ��Bird���ڲ���ӷ��������˶�����
	double g;
	// ����ʱ��t�Ժ󣬵��˶��ٶ�
	double vs;
	// ���μ��ʱ�䣬�ƶ��ľ��룺λ��
	double t;
	
	double v;
	// ��ʼ�ٶ�
	double v0;
	double s;
	// ��Bird��������������
	double alpha;

	public Bird() throws Exception {
		image=ImageIO.read(new File("image/0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 35;
		// ��Bird���������棬��Ӵ������飬��ȡ����֡�Ĵ���
		// �������飺��������, ��8����λ�ã���ţ�0~7
		images = new BufferedImage[8];// {^,^,^,^,^,^,^,^}
		for (int i = 0; i < 8; i++) {
			// int i = 0 1 2 3 4 5 6 7;��ʼi=0 ����i<8 ÿ��1++
			
			images[i]=ImageIO.read(new File("image/"+i+".png"));
		}
		// ���Ǵ��� �����������йص�ѭ��һ��ʹ��for����
		index = 0;
		// ��Bird����������ӷ������Գ�ʼ��
		
		//g = 4.5;// �������ٶ�
		g=4.5;
		
		vs = 20; // ��ʼ�ٶ�
		t = 0.25;//

		v0 = vs;
		s = 0;
		v = 0;
		// ��Bird����������ӳ�ʼ���������
		 alpha = 0;
	} // Bird()����������

	// ��Bird��������ƶ�һ���ķ���step
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

	// �ӵ�ǰλ�ÿ�ʼ�������·�����ף�
	public void flappy() {
		// ʹ��ӵ�ǰλ�ã�������vs�ٶ�����
		v0 = 20;
	}

	// ��Bird������ӷ��跽��fly
	public void fly() {
		index++;
		// images[0], images[1], images[2],....
		image = images[index / 8 % 8];
	}
}
