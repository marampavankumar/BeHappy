package test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/*import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;*/

public class CSVByteWriter {
	/*

	public static void main(String[] args) {
		String[] headers = {"name", "job"};
		String[] row1 = {"pavan", "software"};
		String[] row2 = {"abc", "hardware"};
		List<String[]> data = new ArrayList<String[]>();
		data.add(row1);
		data.add(row2);
		
		 StringWriter sw = new StringWriter();
		BufferedWriter writer = new BufferedWriter(sw);
		try{
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.RFC4180.withHeader(headers));
			csvPrinter.printRecords(data);
			csvPrinter.flush();
			csvPrinter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(sw.getBuffer());
		try {
			String encoded = new String(Base64.getEncoder().encode(sw.getBuffer().toString().getBytes()));
			createCSV(encoded);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workOnExcel();
	}


	private static void createCSV(String string) throws IOException {
		String inputFilePath = "C:\\Users\\pavan\\Desktop\\csvstringwriter.csv";
		byte[] decodedBytes = Base64.getDecoder().decode(string);
		Files.write(Paths.get(inputFilePath), decodedBytes);
	}
	
	private static void workOnExcel() {
		// TODO Auto-generated method stub
		
	}
	
*/
}
