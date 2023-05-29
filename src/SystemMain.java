import java.io.*;
import java.util.Scanner;

public class SystemMain {

	public static void main(String[] args) {
		Scanner consolScanner = new Scanner(System.in);
		
		//take inputs from user
		System.out.println("Please enter File Name(e.g. input.txt), Byte Ordering Type, Data Type and Data Type Size respectively: ");
		String file = consolScanner.nextLine();
		OrderingType byteOrdering = OrderingType.findOrderingType(consolScanner.nextLine());
		DataType dataType = DataType.findDataType(consolScanner.nextLine());
		String dataTypeSizeStr = consolScanner.nextLine();
		consolScanner.close();
		int dataTypeSize = Integer.parseInt((dataTypeSizeStr.split(" "))[0]);
		
		try {
			Scanner input = new Scanner(new File(file));
			PrintWriter output = new PrintWriter(new File("output.txt"));
			
			int count = 1;
			String str, binary, outStr;
			while(input.hasNextLine()) {
				str = "";
				for(int i = 0; i < dataTypeSize; i++){
					str = str + input.next() + " ";
				}
				binary = ConvertHexToBinary.convert(str, byteOrdering);
				outStr = CalculateValue.calculate(binary, dataTypeSize, dataType);
				
				if(count % (12 / dataTypeSize) == 0) {
					output.println(outStr);
					System.out.println(outStr);
				}
				else {
					outStr = outStr + " ";
					output.print(outStr);
					System.out.print(outStr);
				}
				count++;
			}
			input.close();
			output.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}