public enum DataType {
	signedInteger, unsignedInteger, floatingPoint;
	
	public static DataType findDataType(String str) {
		str = str.trim();
		str = str.toLowerCase();
		str = str.replace('�', 'i');
		
		if(str.equals("int")|| str.equals("signed integer")) {
			return DataType.signedInteger;
		}
		
		if(str.equals("unsigned")|| str.equals("unsigned integer")) {
			return DataType.unsignedInteger;
		}
		
		if(str.equals("float")|| str.equals("floating point")) {
			return DataType.floatingPoint;
		}
		
		throw new IllegalArgumentException("Invalid data type: " + str);
	}
}
