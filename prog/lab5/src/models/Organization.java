package models;
/** 
 * Class Organization
 */

public class Organization {
	
    private String fullName; //Длина строки не должна быть больше 1654, Поле не может быть null
    private OrganizationType type; //Поле не может быть null
    
	public Organization(String fullName, OrganizationType type) {
		this.fullName = fullName;
		this.type = type;
	}
	
	public String getName() {
		return fullName;
	}
	
	public OrganizationType getOrganizationType() {
		return type;
	}


	@Override
	public String toString() {
		return "Organization{" +
				"fullName=" + fullName +
				", type=" + type + '\'' +
				'}';
	}
}