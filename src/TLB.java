/*
  TLB
  stores a table that can be accessed by the CPU directly
  operates in FIFO replacement order
 */
public class TLB{
	private static TLBEntry[] table;
	private static int firstIn = 0;
	private static int isFull = 0;
	
	public int TLBCheck(int pageNumber){
		int FrameNumber = -1;
		for(int i = 0; i < 8; i++){
			if(pageNumber == table[i].getVpage())
				FrameNumber = table[i].getPFrame();
		}	
		
		return FrameNumber;
	}
	
	
	
	
	public void TLBreplace(int vPage, int fNumber){
		TLBEntry entry = new TLBEntry(vPage,1,1,0,fNumber);
		if(isFull < 8){
			table[isFull] = entry;
			isFull++;

		}
		else{
			table[firstIn] = entry;
			firstIn++;
			if(firstIn == 8)
				firstIn = 0;
		}
		//return entry;
	}
	public int getFirstIn(){
		return firstIn;
	}
	public void update(int vPage, int fNumber){
		int pointer = firstIn;
		table[pointer].setVpage(vPage);
		table[pointer].setRef(1);
		table[pointer].setValid(1);
		table[pointer].setDirty(0);
		table[pointer].setPFrame(fNumber);
		
	}
	public void updateDirty(int vPage){
		for (int i=0;i<8;i++){
			if(table[i].getVpage()==vPage)
				table[i].setDirty(1);
		}
		
	}


	public void initialize() {
		table = new TLBEntry[8];
		for (int i=0;i<8;i++){
			table[i] = new TLBEntry(-1, 0,0,0,-1);
		}
	}
}
