package rain;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Rain extends JFrame{
	private RainCanvas canvas = new RainCanvas(0,0);
	public Rain(){
//		super("Test");
		//����setUndecorated()�����Ϳ�����Frame����ʧȥ�߿�ͱ������������ˡ�
//		setUndecorated(true);
		//���ô��ڵ�״̬:MAXIMIZED_BOTH ˮƽ�������ֱ������� 
		setExtendedState(MAXIMIZED_BOTH);
		setSize(1500,900);
		setVisible(true);
		//getWidth();getHeight() �õ���ǰ��Ŀ����߶�
		canvas = new RainCanvas(getWidth(),getHeight());
		
		//��ȡ������壬��ΪJFrame����ֱ����������
		//��Ҫ��getContentPane()������ȡ������壬������������Ͻ���������
		getContentPane().add(canvas);
		
		//EXIT_ON_CLOSE���� JFrame �ж��壩��
		//ʹ�� System exit �����˳�Ӧ�ó��򡣽���Ӧ�ó�����ʹ�á�
		//Ĭ������£���ֵ������Ϊ HIDE_ON_CLOSE
		//Ҳ����˵û�����õĻ�,Ĭ�ϵ�ر�ʱֻ�����ش���,�ں�̨�����л����Կ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Rain test =new Rain();
		test.canvas.startRain();

	}

}
