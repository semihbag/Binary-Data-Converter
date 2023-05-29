public enum OrderingType {
	littleEndian, bigEndian;
	
	public static OrderingType findOrderingType(String str) {
		str = str.trim();
		str = str.toLowerCase();
		if (str.equals("l")|| str.equals("little endian")) {
			return OrderingType.littleEndian;
		}
		if (str.equals("b")|| str.equals("big endian")) {
			return OrderingType.bigEndian;
		}
		
		throw new IllegalArgumentException("Invalid data type: " + str);
	}
}
