package rain;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Rain extends JFrame{
	private RainCanvas canvas = new RainCanvas(0,0);
	public Rain(){
//		super("Test");
		//调用setUndecorated()方法就可以让Frame窗口失去边框和标题栏的修饰了。
//		setUndecorated(true);
		//设置窗口的状态:MAXIMIZED_BOTH 水平方向和竖直方向都最大化 
		setExtendedState(MAXIMIZED_BOTH);
		setSize(1500,900);
		setVisible(true);
		//getWidth();getHeight() 得到当前层的宽度与高度
		canvas = new RainCanvas(getWidth(),getHeight());
		
		//获取内容面板，因为JFrame不能直接添加组件，
		//需要用getContentPane()函数获取内容面板，再在内容面板上进行添加组件
		getContentPane().add(canvas);
		
		//EXIT_ON_CLOSE（在 JFrame 中定义）：
		//使用 System exit 方法退出应用程序。仅在应用程序中使用。
		//默认情况下，该值被设置为 HIDE_ON_CLOSE
		//也就是说没有设置的话,默认点关闭时只是隐藏窗体,在后台进程中还可以看到
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Rain test =new Rain();
		test.canvas.startRain();

	}

}
