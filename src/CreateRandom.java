import java.util.ArrayList;
import java.util.Collections;

public class CreateRandom {
	
	//����Ʈ ũ��
	int total;
	ArrayList<Integer> randomNum = new ArrayList<Integer>();
	
	//list ũ�� ����
	void setTotalNum(int total)
	{
		this.total = total;
		suffleNum();
	}
	
	//list ����
	private void suffleNum()
	{
		for(int i = 0; i < total; i++)
			randomNum.add(i + 1);
		
		Collections.shuffle(randomNum);
	}
	
	//index��° ���� ���ϱ�
	String getNum(int index)
	{
		return String.valueOf(randomNum.get(index - 1));
	}
	
}
