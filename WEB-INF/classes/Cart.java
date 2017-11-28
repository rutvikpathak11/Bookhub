import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;

public class Cart
{
	static List<Products> productList;
	Float cartTotal=0.0f;
	HashMap<String, Float> cartItems = new HashMap<String,Float>();
		 
	   /*  public Cart(){
	     cartItems = new HashMap<String, List<Integer>>();
	      
	    }*/
	   
	    /* public void addToCart(String itemId, int price , int quantity){
	    	List<Integer> a = new ArrayList<Integer>();
	    	a.add(price);
	    	a.add(quantity);
	    	cartItems.put(itemId, a);
	    }   */
	     
	public void deleteFromCart(String itemId)
	{
	       cartItems.remove(itemId);
			Iterator itr = this.productList.iterator();
			while(itr.hasNext())
			{
				Products p = (Products)itr.next();
				if((p.getName()).equalsIgnoreCase(itemId))
					itr.remove();
			}

	}
	
	public Float getCartTotal()
	{
		this.cartTotal=0.0f;
		calculateCartTotal();
		return this.cartTotal;
	}
	private Float calculateCartTotal()
	{
		/* for(String key:cartItems.keySet())
		{
			cartTotal+=cartItems.get(key);
		} */
		for(Map.Entry<String,Float> entry : cartItems.entrySet())
		{
			String key = entry.getKey();
			Float values = entry.getValue();
			
			cartTotal = cartTotal + values;
		}
		
		return this.cartTotal;
	}
	
	private void createCartMap()
	{
		for (int i=0;i<productList.size();i++)
		{
			Products p = productList.get(i);	
			//List<Integer> a = new ArrayList<Integer>();
	    	//a.add(price);
	    	//a.add(quantity);
	    	cartItems.put(p.getName(),Float.parseFloat(p.getPrice()));
		}
	}
		
	public List<Products> getProductList()
	{
		return this.productList;
	}
	public void setProductList(List<Products> productList)
	{
		this.productList = productList;
		createCartMap();
	}
	 public HashMap getCartItems(){
	        return this.cartItems;
	    }

}


