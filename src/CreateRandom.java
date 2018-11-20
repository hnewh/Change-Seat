import java.util.ArrayList;
import java.util.Collections;

public class CreateRandom {
	
	//리스트 크기
	int total;
	ArrayList<Integer> randomNum = new ArrayList<Integer>();
	
	//list 크기 지정
	void setTotalNum(int total)
	{
		this.total = total;
		suffleNum();
	}
	
	//list 섞기
	private void suffleNum()
	{
		for(int i = 0; i < total; i++)
			randomNum.add(i + 1);
		
		Collections.shuffle(randomNum);
	}
	
	//index번째 숫자 구하기
	String getNum(int index)
	{
		return String.valueOf(randomNum.get(index - 1));
	}
	
}
