package bird;


import javax.swing.JFrame;

public class FlappyBird {
	public static void main(String[] args) throws Exception {
		System.out.println("Program is run");
		JFrame frame = new JFrame("�����С��");
		Game game = new Game();
		frame.add(game);
		frame.setSize(432, 644);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // ʹ������ʾ����Ļ����
		frame.setVisible(true);
		game.action();
		System.out.print("123");
	}// main ��������
}// FlappyBird ��Ľ���
