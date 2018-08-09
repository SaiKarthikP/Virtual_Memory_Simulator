import java.io.BufferedReader;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("Virtual Memory Simulator with TLB");
		System.out.println("Enter the input file location");
		Scanner input = new Scanner(System.in);
		String filename = input.nextLine();
		input.close();
		String fileto = Paths.get(".").toAbsolutePath().normalize().toString();
		String fileFrom = fileto;
		fileto += "";
		fileFrom += "//page_files_original";
		File dir = new File(fileFrom);
		for(File file: dir.listFiles()){
			String a = (fileFrom+"//"+file.getName());
			String b = (fileto+"//"+file.getName());
			Files.copy(Paths.get(a), Paths.get(b), StandardCopyOption.REPLACE_EXISTING);
		}
		
		
		PhysicalMem p = new PhysicalMem();
		
		PageTable pt = new PageTable();
		pt.initialize();

		TLB tlb = new TLB();
		tlb.initialize();
		
		ClockPageReplacement clock = new ClockPageReplacement();
		clock.initialize();
		
		newCPU cpu = new newCPU();
		
		CSV csv = new CSV();
		csv.setFilename(filename + "_output.csv");
		csv.header();
		
		cpu.readFile(filename);
		csv.totals();
		
		System.out.println("Virtualization complete! Output file: " + filename + "_output.csv");
	}
}
