import java.io.FileNotFoundException;
import java.io.IOException;

/*
 	MMU
 	Checks TLB and Page table
 	throws fault exceptions if not valid, traps in OS

 	-if address is preceded by 0, then read and output value
 	-else if 1, then address will be followed by decimal value, 
 		need to be written to physical memory, set dbit in TLB and virtual page table
 	-if soft miss, overwrite record in TLB
 	-if TLB is full, FIFo algo 
 */
public class MMU extends newCPU {
	public int read(String vAddress) throws IOException{
		int pageNumber;
		int offset;
		char[] x =  vAddress.toCharArray();
		String y = "" + x[0] + x[1];
		pageNumber = Integer.parseInt(y,16);
		String z = "" + x[2] + x[3];
		offset = Integer.parseInt(z,16);

		PageTable pt = new PageTable();
		TLB tlb = new TLB();
		int FrameNumber = tlb.TLBCheck(pageNumber);
		CSV csv = new CSV();
		if (FrameNumber!=-1){
			csv.setHit(1);
		}
		//soft miss
		if (FrameNumber==-1){
			csv.setSoftMiss(1);
			//check page table	and get frame number
			//update tlb
			FrameNumber = pt.PageTableCheck(pageNumber);
			if (FrameNumber!=-1)
				tlb.update(pageNumber, FrameNumber); //update tlb from page table

			//hardmiss
			if(FrameNumber==-1){
				csv.setSoftMiss(0);
				csv.setHardMiss(1);
				ClockPageReplacement cpr = new ClockPageReplacement();

				CustomOS os = new CustomOS();
				//read from file and write to memory/2D array
				
				String hex = Integer.toHexString(pageNumber).toUpperCase() + ".pg";
				if(pageNumber<16)
					hex = "0" + hex;
				FrameNumber = os.loadPage(hex);
				//update TLB and pageTable from memory
				cpr.update(pageNumber, FrameNumber, 1);				
				tlb.update(pageNumber, FrameNumber);
				pt.update(pageNumber, FrameNumber);
				
			}
			
		}
		PhysicalMem mem = new PhysicalMem();
		return mem.get(FrameNumber, offset);
	}
	public void write(String vAddress, int data) throws IOException{
		int pageNumber;
		int offset;
		char[] x = vAddress.toCharArray();
		String y = "" + x[0] + x[1];
		pageNumber = Integer.parseInt(y,16);
		String z = "" + x[2] + x[3];
		offset = Integer.parseInt(z,16);

		TLB tlb = new TLB();
		PageTable pt = new PageTable();
		
		int frameNumber = tlb.TLBCheck(pageNumber);
		if (frameNumber == -1)
			frameNumber = pt.PageTableCheck(pageNumber);
		if (frameNumber ==-1){
			CustomOS os = new CustomOS();
			frameNumber = os.loadPage(Integer.toHexString(pageNumber)+ ".pg");
		}
		
		PhysicalMem mem = new PhysicalMem();
		mem.set(frameNumber, offset, data);
		tlb.updateDirty(pageNumber);
		pt.setDirty(pageNumber);
		pt.setRef(pageNumber);
	}


}
