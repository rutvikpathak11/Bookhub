/**
*This is a bean for maintaining users information.
**/
import java.io.Serializable;  

public class User implements Serializable
{
	static final long serialVersionUID = 3763764155951099462L;
	private String userId;
	private String password;
	private String userType;
	
	User()
	{
		
	}
	User(String userId, String password, String userType)
	{
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}
	
	public String getUserId()
	{
		return(this.userId);
	}
	public String getPassword()
	{
		return(this.password);
	}
	public String getUserType()
	{
		return(this.userType);
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	
	 public String toString() { 
         return (this.userId +" "+this.password +" "+ this.userType);
      } 
	
}