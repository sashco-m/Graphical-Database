
public class CustomerRecord
{
    private String firstName,lastName,address,telephone,age,income;
    
    public CustomerRecord(String firstName, String lastName, String address, String telephone, String age, String income)
    {
	this.firstName=firstName;
	this.lastName=lastName;
	this.address=address;
	this.telephone=telephone;
	this.age=age;
	this.income=income;
    }
    public String getFirstName()
    {
	return firstName;
    }
    public String getLastName()
    {
	return lastName;
    }
    public String getAddress()
    {
	return address;
    }
    public String getAge()
    {
	return age;
    }
    public String getTelephone()
    {
	return telephone;
    }
    public String getIncome()
    {
	return income;
    }
    
    public String toString()
    {
	return firstName+" "+lastName;   
    } 
}
