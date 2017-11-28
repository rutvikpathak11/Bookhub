import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDetails implements Serializable
{
	static final long serialVersionUID = 3763764155951099462L;
	String orderNo, userId, modelName, shippingAddress, zipCode; 
	Float price;
	String deliveryDate;
	
	public void setOrderNo(String orderNo)
	{
		this.orderNo=orderNo;
	}
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	public void setModelName(String modelName)
	{
		this.modelName=modelName;
	}
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress=shippingAddress;
	}
	public void setPrice(Float price)
	{
		this.price=price;
	}
	public void setDeliveryDate(String deliveryDate)
	{
		this.deliveryDate=deliveryDate;
	}
	public void setZipCode(String zipCode)
	{
		this.zipCode=zipCode;
	}
	
	public String getOrderNo()
	{
		return this.orderNo;
	}
	public String getUserId()
	{
		return this.userId;
	}
	public String getModelName()
	{
		return this.modelName;
	}
	public String getShippingAddress()
	{
		return this.shippingAddress;
	}
	public Float getPrice()
	{
		return this.price;
	}
	public String getDeliveryDate()
	{
		return this.deliveryDate;
	}
	public String getZipCode()
	{
		return this.zipCode;
	}
}