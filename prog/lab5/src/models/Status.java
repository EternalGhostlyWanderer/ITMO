package models;

public enum Status {
	HIRED,
    REGULAR,
    PROBATION;
	
	 public static String names() {
	        StringBuilder nameList = new StringBuilder();
	        for (var value : values()) {
	            nameList.append(value.name()).append(", ");
	        }
	        return nameList.substring(0, nameList.length() - 2);
	    }
	}