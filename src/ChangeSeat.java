import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class ChangeSeat extends JFrame implements ActionListener {

	JLabel numLabel = new JLabel("학생 수 : ");
	JTextField number = new JTextField(5);
	
	JButton change = new JButton("자리 바꾸기");
	JPanel pp1 = new JPanel();
	JPanel pp2 = new JPanel();
	JPanel pp3 = new JPanel();
	JButton b;
	
	JButton save = new JButton("저장");
	JButton exit = new JButton("종료");
	static String[] seatNum = new String[26];
	
	ChangeSeat()
	{
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		c.add(p1, BorderLayout.NORTH);
		
		//학생 수
		p1.add(numLabel);
		p1.add(number);
		number.setText("26");
		number.setEnabled(false);
		
		//바꾸기 버튼
		p1.add(change);
		change.addActionListener(this);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 3, 10, 0));
		c.add(p2, BorderLayout.CENTER);
		
		//1분단
		pp1.setLayout(new GridLayout(5, 2));
		p2.add(pp1);
		
		//2분단
		pp2.setLayout(new GridLayout(5, 2));
		p2.add(pp2);
		
		//3분단
		pp3.setLayout(new GridLayout(5, 2));
		p2.add(pp3);
		
		//버튼 배치
		for(int i = 0; i < 26; i++)
		{
			seatNum[i] = String.valueOf(i + 1);
			b = new JButton(seatNum[i]);
			
			switch(i%6)
			{
				case 0: case 1: pp1.add(b); break;
				case 2: case 3: pp2.add(b); break;
				case 4: case 5: pp3.add(b); break;
			}
		}
		
		JPanel p3 = new JPanel();
		c.add(p3, BorderLayout.SOUTH);
		
		p3.add(save);
		save.addActionListener(this);
		p3.add(exit);
		exit.addActionListener(this);
		
		setSize(400, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {

		new ChangeSeat();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int num = Integer.parseInt(number.getText());
		
		//자리 바꾸기 클릭
		if(e.getSource() == change)
		{
			//JPanel 초기화
			pp1.removeAll();
			pp2.removeAll();
			pp3.removeAll();
			
			//랜덤변수 클래스 생성
			CreateRandom c = new CreateRandom();
			c.setTotalNum(num);
			
			for(int i = 0; i < num; i++)
			{	
				// 배열에 자리 번호 넣기
				seatNum[i] = c.getNum(i + 1);
				b = new JButton(seatNum[i]);
				
				//버튼 배치
				switch(i%6)
				{
					case 0: case 1: pp1.add(b); break;
					case 2: case 3: pp2.add(b); break;
					case 4: case 5: pp3.add(b); break;
				}
			}
			
			//JPanel 지연
			pp1.revalidate();
			pp2.revalidate();
			pp3.revalidate();
			
			//JPanel 다시 로드
			pp1.repaint();
			pp2.repaint();
			pp3.repaint();
		}
		
		//저장 버튼 클릭
		if(e.getSource() == save)
		{
			//자리.txt 파일 생성
			try {
				PrintWriter out = new PrintWriter(new FileWriter("자리.txt"));
				
				for(int i = 0; i < num; i++)
				{
					//2자리수로 포맷
					String foStr = String.format("%02d", Integer.parseInt(seatNum[i]));
					switch (i%6)
					{
						case 0: case 2: case 4: out.print(foStr + " "); break;
						case 1: case 3: out.print(foStr + "\t"); break;
						case 5: out.println(foStr); break;
					}
				}
				
				//dialog 호출
				Dialog d = new Dialog(this, "저장");
				d.setVisible(true);
				
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//종료 버튼 클릭
		if(e.getSource() == exit)
			System.exit(0);
		
	}

}

class Dialog extends JDialog
{
	Label label = new Label("저장이 완료되었습니다.");
	JButton ok = new JButton("확인");
	
	public Dialog(JFrame frame, String title)
	{
		//frame과 이름 설정
		super(frame, title);
		setLayout(new FlowLayout());
		
		add(label);
		label.setAlignment(WIDTH);
		add(ok);
		
		setSize(200, 100);
		
		//확인 버튼 클릭
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
			
		});
	}
}
