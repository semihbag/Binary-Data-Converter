public class ConvertHexToBinary {
	public static String convert(String str, OrderingType orderType) {
		String byteList[] = str.split(" ");
		
		//if type is little endian reverse list
		if(orderType == OrderingType.littleEndian) {
			for(int i = 0, j = byteList.length - 1; i < j; i++, j--) {
				String temp = byteList[i];
				byteList[i] = byteList[j];
				byteList[j] = temp;
			}
		}
		
		//to get a single string
		String sum = "";		
		for(int i = 0; i < byteList.length ; i++) {
			sum += byteList[i];
		}
		
		//convert binary
		String output = "";
		for(int i = 0; i < sum.length(); i++) {
			char c = sum.charAt(i);
			
			switch (c) {
			case '0':
				output += "0000";
				break;
			case '1':
				output += "0001";
				break;
			case '2':
				output += "0010";
				break;
			case '3':
				output += "0011";
				break;
			case '4':
				output += "0100";
				break;
			case '5':
				output += "0101";
				break;
			case '6':
				output += "0110";
				break;
			case '7':
				output += "0111";
				break;
			case '8':
				output += "1000";
				break;
			case '9':
				output += "1001";
				break;
			case 'a':
				output += "1010";
				break;
			case 'b':
				output += "1011";
				break;
			case 'c':
				output += "1100";
				break;
			case 'd':
				output += "1101";
				break;
			case 'e':
				output += "1110";
				break;
			case 'f':
				output += "1111";
				break;			
			default:
				break;
			}
		}
		return output;
	}
}
