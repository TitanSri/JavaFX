package application;

public class Exercise_33_6_StudentAddress implements java.io.Serializable {
	  /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	private String name;
	  private String street;
	  private String city;
	  private String state;
	  private String zip;

	  public Exercise_33_6_StudentAddress(String name, String street, String city,
	    String state, String zip) {
	    this.name = name;
	    this.street = street;
	    this.city = city;
	    this.state = state;
	    this.zip = zip;
	  }

	  public String getName() {
	    return name;
	  }

	  public String getStreet() {
	    return street;
	  }

	  public String getCity() {
	    return city;
	  }

	  public String getState() {
	    return state;
	  }

	  public String getZip() {
	    return zip;
	  }
	}