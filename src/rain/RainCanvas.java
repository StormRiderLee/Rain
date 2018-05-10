package rain;

import java.awt.*;
import java.util.*;

//Canvas �����ʾ��Ļ��һ���հ׾�������Ӧ�ó�������ڸ������ڻ�ͼ�����߿��ԴӸ����򲶻��û��������¼���
@SuppressWarnings("serial")
public class RainCanvas extends Canvas implements Runnable{
	private int width,height;
	private Image OffScreen;//����ͼƬ	
	private char[][]charset;//����ַ�����
	private int[]pos;//�е���ʼλ��
	private Color[] colors = new Color[30];//�еĳ��Ⱥ��еĽ�����ɫ
	
	public RainCanvas(int width,int height){
		this.width = width;
		this.height =height;
		
		//����ASCII�ɼ��ַ�����
		
		Random rand = new Random();
		charset = new char[width/10][height/10];
		for(int i = 0;i<charset.length;i++){
			for(int j =0;j<charset[i].length;j++){
				charset[i][j]=(char)(rand.nextInt(92)+33);
			}
		}
	
	//���������ʼλ��
		pos = new int [charset.length];
		for(int i =0;i<pos.length;i++){
			pos[i] = rand.nextInt(pos.length); 
		}
	
		//���ɴӺ�ɫ����ɫ�Ľ�����ɫ�����һ������Ϊ��ɫ
		for(int i =0;i<colors.length-1;i++){
			colors[i]=new Color(0,255/colors.length*(i+1),0);
		}
		colors[colors.length-1] =new Color(255,255,255);
		
		//����ɫ(��ɫ)
		setBackground(Color.BLACK);
		
		//����Canvas�Ĵ�С
		//setSize(width,height);
		
		//setVisible(true)Ŀ����ʹ�ؼ�������ʾ����,
		//����ÿؼ��Ѿ�����ʾ��������ô�÷����ǿؼ���ʾ�ڴ��ڵ���ǰ����
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
		
		//getGraphics()���ô˷����൱�ڸ�����һֻ��������ʹ�á��õ����ʺ������������ͼ�ε�
		Graphics g = OffScreen.getGraphics();
		
		//clearRect() ������ո��������ڵ�ָ������
		g.clearRect(0,0,width,height);
		
		//���������ʾЧ��������(String ���壬int ���int �ֺ�);
		//���壺TimesRoman, Courier, Arial��
		//�����������  Font.PLAIN, Font.BOLD, Font.ITALIC
		//�ֺţ��ֵĴ�С��������
		g.setFont(new Font("Arial",Font.PLAIN,14));
		
		
		for(int i = 0;i<charset.length;i++){
			//int speed = rand.nextInt(3);
			for(int j = 0;j<colors.length;j++){//30
				int index = (pos[i]+j)%charset[i].length;
				//setColor()�����û��ʵ���ɫ
				g.setColor(colors[j]);
				//drawCharsʹ�ô�ͼ�������ĵĵ�ǰ�������ɫ������ָ���ַ�����������ı�
				g.drawChars(charset[i],index,1,i*10,index*10);
			}
			pos[i]=(pos[i]+1)%charset[i].length;
		}
	}
	
	//update()�������ǰ��ʾ������paint()������
	//���´� canvas�� 
	//���ô˷�����Ӧ�� repaint �ĵ��á�����ͨ��ʹ�ñ���ɫ��� canvas ����������
	//Ȼ��ͨ�����ô� canvas �� paint �����ػ�����
	public void update(Graphics g){
		paint(g);
	}
	
	@Override
	public void run() {
		while(true){
			drawRain();
			//repaint()���ػ�component�ķ���(component��Canvas�ĸ���)��
			//component�м��е�ͼ�η����仯�󲻻�������ʾ����ʹ��repaint����
			//�������������ǰ�����ݲ�ȥ
			repaint();
			try {
				Thread.sleep(50);//�ı�˯��ʱ���Ե����ٶ�
			} 
			catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	public void paint(Graphics g){
//�������ʾʱ����Ƿ�Ҫ��������ͼƬ������������ɼ�ʱ����createImage������null
		if(OffScreen == null){
			OffScreen = createImage(width,height);
		}
		g.drawImage(OffScreen,0,0,this);
	}
	
	

}
