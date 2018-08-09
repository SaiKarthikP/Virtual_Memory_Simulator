import java.util.Scanner;
import java.io.*;

/*
 	OS
 	Handles page faults when thrown by MMU
 	Unsets reference bits in all tables once CPU processes 5 instructions
 	-replaces page
 	-writes back evicted page to drive if dirty bit is set, then set dbit to 0
 	-unsets rbit and dbits
 */
public class CustomOS {
	
	public int loadPage(String fileName) throws IOException{
		CSV csv = new CSV();
		csv.incThdRead();
		//check which page to evict - clock
		ClockPageReplacement clock = new ClockPageReplacement();
		ClockPoint cp = clock.findEvict();
		
		int pFrame = cp.getfNumber();
		//write page back to disk if dirty bit is set
		PageTable pt = new PageTable();
		csv.setDbit(0);
		if (cp.getvNumber()!=-1 && pt.getDirty(cp.getvNumber())==1){
			csv.setDbit(1);
			csv.incThdWrite();
			PhysicalMem memory = new PhysicalMem();
			String pName ="";
			if (cp.getfNumber()<16)
				pName += "0";
			pName += Integer.toHexString(cp.getfNumber()).toUpperCase() + ".pg";
			File file = new File(pName);
			FileWriter writer = new FileWriter(file, false);
			for(int i=0;i<256;i++){
				writer.write(memory.get(pFrame, i)+"");
				writer.write("\n");
			}
			writer.close();
		}
		
		//write from disk to page frame/memory
		if (fileName.length()==4)
			fileName = "0" + fileName;
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		
		PhysicalMem memory = new PhysicalMem();
		for(int i=0;i<256;i++){
			memory.set(pFrame, i, sc.nextInt());
		}
		//System.out.println(memory.get(pFrame, 0)); //outputs correctly
		TLB tlb = new TLB();
		tlb.update(cp.getvNumber(), pFrame);
		clock.update(cp.getvNumber(), pFrame, 1);
		sc.close();
		return pFrame;
	}
	
	public void updateAllFiles() throws IOException{
		//update final values from memory to disk when dbits are set 
		
		PageTable pt = new PageTable();
		PhysicalMem mem = new PhysicalMem();
		for (int i=0;i<256;i++){
			
			if (pt.getDirty(i)==1){
				int pFrame = pt.PageTableCheck(i);
				String filename ="";
				if (i<16)
					filename = "0";
				filename += Integer.toHexString(i) + ".pg";
				File f = new File(filename);
				FileWriter writer = new FileWriter(f, false);
				for(int j=0;j<256;j++){
					if(pFrame!=-1){
					writer.write(mem.get(pFrame, j)+"");
					writer.write("\n");
				
					}
					
				}

				writer.close();
			}
		}
	}

	public void resetRef() {
		PageTable pt = new PageTable();
		for(int i=0;i<256;i++){
			pt.unsetRef(i);
		}
	}
	
}
