public class CalculateValue {
	
	public static boolean carry = false;
	
	public static StringBuilder addOne(StringBuilder  sb, int i) {
		if(i >= 0) {
			if(sb.charAt(i) == '0') {
				sb.setCharAt(i, '1');
				return sb;
			}
			else {
				sb.setCharAt(i, '0');
				addOne(sb, i-1);
			}
		}
		if(i == -1) {
			carry = true;
		}
		return sb;
	}
	
	public static long defaultCalcutation(String str) {
		long  number = 0;
		for(int i = 0; i < str.length(); i++) {
			int s = Character.getNumericValue(str.charAt(str.length()-1-i));
			if (s == 1) {
				number += Math.pow(2,i);
			}
		}
		return number;
	}
	
	public static String calculate(String binary, int dataTypeSize, DataType dataType) {
		int msb = (binary.charAt(0) == '0')? 0 : 1;
		carry = false;
		
		switch (dataType) {
		case unsignedInteger:
			return String.valueOf(defaultCalcutation(binary));
			
		case signedInteger:
			long  val = defaultCalcutation(binary.substring(1));
			
			if(msb == 1) {
				val -= Math.pow(2, binary.length() - 1);
			}
			return String.valueOf(val);
			
		case floatingPoint:
			int exponentSize = 4 + ((dataTypeSize-1) * 2);
			long bias = ((long) Math.pow(2, exponentSize - 1)) - 1;
			double mantissa;
			long e;
			String result = "error";
			String r, fraction;
			String exponent = binary.substring(1,exponentSize + 1);
			
			String rf = binary.substring(1 + exponentSize);				//raw fraction
			if(rf .length() > 13 && exponent.contains("0")) {
				String f13 = rf.substring(0, 13);							//first 13 bits of raw fraction
				String s = addOne(new StringBuilder(f13), 12).toString();
				fraction = (rf.charAt(13) == '0')? f13 : (rf.substring(14).contains("1"))? s : (rf.charAt(12) == '0')? f13 : s;
			}
			else {
				fraction = rf;
			}
			
			mantissa = defaultCalcutation(fraction) / Math.pow(2, fraction.length());
			if (exponent.contains("0")){
				if(carry) {
					exponent = addOne(new StringBuilder(exponent), 9).toString();
				}
				
				if (exponent.contains("1")) {	//normalized value
					mantissa += 1;
					e = defaultCalcutation(exponent) - bias;
				}
				else {							//denormalized value
					e = 1 - bias;
					if (!fraction.contains("1")) {
						return (msb == 0)? "0" :"-0";
					}
				}
				r = Double.toString(mantissa * Math.pow(2, e));
				result = (r.length() <= 7)? r : (!r.contains("E"))? r.substring(0,7) : r.substring(0,7) + r.substring(r.indexOf("E"));
				result = (msb == 0)? result : "-" + result;
				result = result.replace('E', 'e');
			}
			else {								//special values
				result = (fraction.contains("1"))? "NaN" : (msb == 0)? "∞" : "-∞";
			}
			return result;
		default:
			return "error in data type";
		}
	}
}
