import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class newCPU {
	//reads test file and does read or write of memory data
	public void readFile(String fileLocation) throws IOException{
		int refCounter = 0;
		CSV csv = new CSV();

		File file = new File(fileLocation);
		Scanner sc = new Scanner(file);
		MMU mmu = new MMU();
		while (sc.hasNextInt()){
			int rw = sc.nextInt();
			csv.setRw(rw);
			if (rw==0){
				String x =sc.next();
				csv.setvAddress(x);
				csv.setValue(mmu.read(x));
				refCounter++;
			}
			else if (rw==1){
				String x = sc.next();
				csv.setvAddress(x);
				int value = sc.nextInt();
				csv.setValue(value);
				mmu.write(x, value);
				refCounter++;
			}
			if(refCounter>4){
				CustomOS os = new CustomOS();
				os.resetRef();
				refCounter =0;
			}
			csv.print();
		}
		sc.close();

		//update final values from memory to disk when dbits are set 
		CustomOS os = new CustomOS();
		os.updateAllFiles();
		
	}
}
