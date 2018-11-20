import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class ChangeSeat extends JFrame implements ActionListener {

	JLabel numLabel = new JLabel("�л� �� : ");
	JTextField number = new JTextField(5);
	
	JButton change = new JButton("�ڸ� �ٲٱ�");
	JPanel pp1 = new JPanel();
	JPanel pp2 = new JPanel();
	JPanel pp3 = new JPanel();
	JButton b;
	
	JButton save = new JButton("����");
	JButton exit = new JButton("����");
	static String[] seatNum = new String[26];
	
	ChangeSeat()
	{
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		c.add(p1, BorderLayout.NORTH);
		
		//�л� ��
		p1.add(numLabel);
		p1.add(number);
		number.setText("26");
		number.setEnabled(false);
		
		//�ٲٱ� ��ư
		p1.add(change);
		change.addActionListener(this);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 3, 10, 0));
		c.add(p2, BorderLayout.CENTER);
		
		//1�д�
		pp1.setLayout(new GridLayout(5, 2));
		p2.add(pp1);
		
		//2�д�
		pp2.setLayout(new GridLayout(5, 2));
		p2.add(pp2);
		
		//3�д�
		pp3.setLayout(new GridLayout(5, 2));
		p2.add(pp3);
		
		//��ư ��ġ
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
		
		//�ڸ� �ٲٱ� Ŭ��
		if(e.getSource() == change)
		{
			//JPanel �ʱ�ȭ
			pp1.removeAll();
			pp2.removeAll();
			pp3.removeAll();
			
			//�������� Ŭ���� ����
			CreateRandom c = new CreateRandom();
			c.setTotalNum(num);
			
			for(int i = 0; i < num; i++)
			{	
				// �迭�� �ڸ� ��ȣ �ֱ�
				seatNum[i] = c.getNum(i + 1);
				b = new JButton(seatNum[i]);
				
				//��ư ��ġ
				switch(i%6)
				{
					case 0: case 1: pp1.add(b); break;
					case 2: case 3: pp2.add(b); break;
					case 4: case 5: pp3.add(b); break;
				}
			}
			
			//JPanel ����
			pp1.revalidate();
			pp2.revalidate();
			pp3.revalidate();
			
			//JPanel �ٽ� �ε�
			pp1.repaint();
			pp2.repaint();
			pp3.repaint();
		}
		
		//���� ��ư Ŭ��
		if(e.getSource() == save)
		{
			//�ڸ�.txt ���� ����
			try {
				PrintWriter out = new PrintWriter(new FileWriter("�ڸ�.txt"));
				
				for(int i = 0; i < num; i++)
				{
					//2�ڸ����� ����
					String foStr = String.format("%02d", Integer.parseInt(seatNum[i]));
					switch (i%6)
					{
						case 0: case 2: case 4: out.print(foStr + " "); break;
						case 1: case 3: out.print(foStr + "\t"); break;
						case 5: out.println(foStr); break;
					}
				}
				
				//dialog ȣ��
				Dialog d = new Dialog(this, "����");
				d.setVisible(true);
				
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//���� ��ư Ŭ��
		if(e.getSource() == exit)
			System.exit(0);
		
	}

}

class Dialog extends JDialog
{
	Label label = new Label("������ �Ϸ�Ǿ����ϴ�.");
	JButton ok = new JButton("Ȯ��");
	
	public Dialog(JFrame frame, String title)
	{
		//frame�� �̸� ����
		super(frame, title);
		setLayout(new FlowLayout());
		
		add(label);
		label.setAlignment(WIDTH);
		add(ok);
		
		setSize(200, 100);
		
		//Ȯ�� ��ư Ŭ��
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
			
		});
	}
}
