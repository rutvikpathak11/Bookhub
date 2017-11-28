import java.util.*;

public class Products
{
	String name,price,image,manufacturer,condition,discount,quantity,manufacturerRebate,category;
	List<String> accessoryList;
	
	Products()
	{
		
	}
	
	/* Products(String name, String price, String image, String manufacturer, String condition, String discount, List<String> accessoryList)
	{
		this.name=name;
		this.price=price;
		this.image=image;
		this.manufacturer=manufacturer;
		this.condition=condition;
		this.discount=discount;
		this.accessoryList=accessoryList;
	} */
	
	public void setName(String name)
	{
		this.name=name;
	}
	public void setPrice(String price)
	{
		this.price=price;
	}
	public void setImage(String image)
	{
		this.image=image;
	}
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer=manufacturer;
	}
	public void setCondition(String condition)
	{
		this.condition=condition;
	}
	public void setDiscount(String discount)
	{
		this.discount=discount;
	}
	public void setAccessoryList(List<String> accessoryList)
	{
		this.accessoryList=accessoryList;
	}
	public void setQuantity(String quantity)
	{
		this.quantity=quantity;
	}
	public void setManufacturerRebate(String manufacturerRebate)
	{
		this.manufacturerRebate=manufacturerRebate;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	
	
	public String getName()
	{
		return this.name;
	}
	public String getPrice()
	{
		return price;
	}
	public String getImage()
	{
		return image;
	}
	public String getManufacturer()
	{
		return manufacturer;
	}
	public String getCondition()
	{
		return condition;
	}
	public String getDiscount()
	{
		return discount;
	}
	public List<String> getAccessoryList()
	{
		return accessoryList;
	}
	public String getQuantity()
	{
		return this.quantity;
	}
	public String getManufacturerRebate()
	{
		return this.manufacturerRebate;
	}
	public String getCategory()
	{
		return this.category;
	}
	
	
	 public String toString() { 
         return (this.name +" "+this.price +" "+ this.image +" "+this.manufacturer +" "+ this.condition +" "+ this.discount +" " +this.accessoryList +" "+this.quantity);
      } 
}