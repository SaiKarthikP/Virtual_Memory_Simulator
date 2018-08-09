
public class PageTable {
	private static PageEntry[] pageTable;
	private static int pointer = 0;
	
	
	public void initialize(){
		pageTable = new PageEntry[256];	
		PageEntry n;
		for (int i=0;i<256;i++){
			pageTable[i] = new PageEntry(0, 0, 0, -1);
		}
	}
	public int PageTableCheck(int Vnumber){
		int FrameNumber = -1;
		
		if(pageTable[Vnumber].getValid()==1){
			return pageTable[Vnumber].getPFrame();
		} 	
		return FrameNumber;
	}
	
	public int replace(){
	
		while(pageTable[pointer].getRef() == 1){
			pageTable[pointer].setRef(0);
			pointer++;
			if(pointer==256)
				pointer=0;
		}
		//pageTable[pointer] = newEntry;
		//return newEntry.getPFrame();
		pointer++;
		if(pointer == 256)
			pointer=0;	
		
		int oldPFrame = pageTable[pointer].getPFrame();
		pageTable[pointer].setPFrame(-1);
		pageTable[pointer].setValid(0);
		return oldPFrame;
	}
	
	public void update(int vAddress, int pFrame){
		pageTable[vAddress].setPFrame(pFrame);
		pageTable[vAddress].setValid(1);
		pageTable[vAddress].setRef(1);
		pageTable[vAddress].setDirty(0);
	}
	
	public int getPointer(){
		return pointer;
	}
	public int getDirty(int vNumber) {
		return pageTable[vNumber].getDirty();
	}
	public void setDirty(int vNumber){
		pageTable[vNumber].setDirty(1);
	}
	public void setRef(int vNumber){
		pageTable[vNumber].setRef(1);
	}
	public void unsetRef(int vNumber){
		pageTable[vNumber].setRef(0);
	}
	public int getEntryRef(int vNumber){
		return pageTable[vNumber].getRef();
	}
}
