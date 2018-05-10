package rain;

import java.awt.*;
import java.util.*;

//Canvas 组件表示屏幕上一个空白矩形区域，应用程序可以在该区域内绘图，或者可以从该区域捕获用户的输入事件。
@SuppressWarnings("serial")
public class RainCanvas extends Canvas implements Runnable{
	private int width,height;
	private Image OffScreen;//缓冲图片	
	private char[][]charset;//随机字符集合
	private int[]pos;//列的起始位置
	private Color[] colors = new Color[30];//列的长度和列的渐变颜色
	
	public RainCanvas(int width,int height){
		this.width = width;
		this.height =height;
		
		//生成ASCII可见字符集合
		
		Random rand = new Random();
		charset = new char[width/10][height/10];
		for(int i = 0;i<charset.length;i++){
			for(int j =0;j<charset[i].length;j++){
				charset[i][j]=(char)(rand.nextInt(92)+33);
			}
		}
	
	//随机化列起始位置
		pos = new int [charset.length];
		for(int i =0;i<pos.length;i++){
			pos[i] = rand.nextInt(pos.length); 
		}
	
		//生成从黑色到绿色的渐变颜色，最后一个保持为白色
		for(int i =0;i<colors.length-1;i++){
			colors[i]=new Color(0,255/colors.length*(i+1),0);
		}
		colors[colors.length-1] =new Color(255,255,255);
		
		//背景色(黑色)
		setBackground(Color.BLACK);
		
		//定义Canvas的大小
		//setSize(width,height);
		
		//setVisible(true)目的是使控件可以显示出来,
		//如果该控件已经被显示出来，那么该方法是控件显示在窗口的最前方。
		setVisible(true);
	}
	
	
	public void startRain(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void drawRain(){
		if(OffScreen == null){
			return;
		}
		//Random rand = new Random();
		
		//getGraphics()调用此方法相当于给了你一只画笔让你使用。得到画笔后你可以用它画图形等
		Graphics g = OffScreen.getGraphics();
		
		//clearRect() 方法清空给定矩形内的指定像素
		g.clearRect(0,0,width,height);
		
		//设计字体显示效果。例：(String 字体，int 风格，int 字号);
		//字体：TimesRoman, Courier, Arial等
		//风格：三个常量  Font.PLAIN, Font.BOLD, Font.ITALIC
		//字号：字的大小（磅数）
		g.setFont(new Font("Arial",Font.PLAIN,14));
		
		
		for(int i = 0;i<charset.length;i++){
			//int speed = rand.nextInt(3);
			for(int j = 0;j<colors.length;j++){//30
				int index = (pos[i]+j)%charset[i].length;
				//setColor()是设置画笔的颜色
				g.setColor(colors[j]);
				//drawChars使用此图形上下文的当前字体和颜色绘制由指定字符数组给定的文本
				g.drawChars(charset[i],index,1,i*10,index*10);
			}
			pos[i]=(pos[i]+1)%charset[i].length;
		}
	}
	
	//update()：清除当前显示并调用paint()方法．
	//更新此 canvas。 
	//调用此方法响应对 repaint 的调用。首先通过使用背景色填充 canvas 来清理它，
	//然后通过调用此 canvas 的 paint 方法重绘它。
	public void update(Graphics g){
		paint(g);
	}
	
	@Override
	public void run() {
		while(true){
			drawRain();
			//repaint()是重绘component的方法(component是Canvas的父类)，
			//component中己有的图形发生变化后不会立刻显示，须使用repaint方法
			//是用来将面板以前的内容擦去
			repaint();
			try {
				Thread.sleep(50);//改变睡眠时间以调节速度
			} 
			catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	public void paint(Graphics g){
//当组件显示时检测是否要创建缓冲图片，在组件还不可见时调用createImage将返回null
		if(OffScreen == null){
			OffScreen = createImage(width,height);
		}
		g.drawImage(OffScreen,0,0,this);
	}
	
	

}
