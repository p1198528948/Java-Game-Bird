package bird;


import javax.swing.JFrame;

public class FlappyBird {
	public static void main(String[] args) throws Exception {
		System.out.println("Program is run");
		JFrame frame = new JFrame("飞扬的小鸟");
		Game game = new Game();
		frame.add(game);
		frame.setSize(432, 644);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // 使窗口显示在屏幕中央
		frame.setVisible(true);
		game.action();
		System.out.print("123");
	}// main 方法结束
}// FlappyBird 类的结束
