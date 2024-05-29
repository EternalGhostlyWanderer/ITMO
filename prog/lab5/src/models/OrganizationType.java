package models;

public enum OrganizationType {
    COMMERCIAL,
    PUBLIC,
    GOVERNMENT,
    TRUST,
    OPEN_JOINT_STOCK_COMPANY;
	
	public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var value : values()) {
            nameList.append(value.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}