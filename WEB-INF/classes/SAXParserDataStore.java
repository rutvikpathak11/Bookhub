import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class SAXParserDataStore extends DefaultHandler
{
	String currentCategorytemp;
	//PrintWriter out = null; 
	static HashMap<String,List<Products>> productMap = new HashMap<String,List<Products>>();
	
	List<Products> technologyList = new ArrayList<Products>();
	List<Products> comicsList = new ArrayList<Products>();
	List<Products> autobiographyList = new ArrayList<Products>();
	List<Products> sportsList = new ArrayList<Products>();
	List<Products> educationList = new ArrayList<Products>();
	List<Products> religionList = new ArrayList<Products>();
	List<Products> accessoriesList = new ArrayList<Products>();
	
	Products product = null;
	List<String> accessoryList = null;
	
	String elementValueHead;
	boolean nameFlag = false, priceFlag=false,imageFlag=false, manufacturerFlag=false, conditionFlag=false, discountFlag=false, accessoriesFlag=false;
	
	//This method is used to test using PrintWriter's object.
	/* void parse(String fileName, PrintWriter out)
	{
		this.out=out;
		//out.println(fileName);
		DefaultHandler handler = this;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try 
		{
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(fileName), handler);
        } 
		catch (Exception ex) 
		{
			out.println(ex.getMessage());
		}
    } */
	
	void parse(String fileName)
	{
		//this.out=out;
		//out.println(fileName);
		DefaultHandler handler = this;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try 
		{
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(fileName), handler);
        } 
		catch (Exception ex) 
		{
			ex.getMessage();
		}
    }
	
	public void startElement(String str1, String str2, String elementName, Attributes attrs) throws SAXException 
    {
		String currentCategory=null;
        try
		{
			if(elementName.equalsIgnoreCase("Technology"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			} 
			else if(elementName.equalsIgnoreCase("comics"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			} 
			else if(elementName.equalsIgnoreCase("Autobiography"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			} 
			else if(elementName.equalsIgnoreCase("Sports"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			}
			else if(elementName.equalsIgnoreCase("Education"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			}
			else if(elementName.equalsIgnoreCase("Religion"))
			{
				currentCategory = attrs.getValue("id");
				product = new Products();	
				accessoryList = new ArrayList<String>();
			}
			else if(elementName.equalsIgnoreCase("AccessoriesCatalog"))
			{
				currentCategory = attrs.getValue("id");
				currentCategorytemp = currentCategory;
				if(currentCategory.equalsIgnoreCase("accessory"))
				{
					product = new Products();
				}
			} 
			else if(elementName.equalsIgnoreCase("name"))
			{
				nameFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("price"))
			{
				priceFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("image"))
			{
				imageFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("Publisher"))
			{
				manufacturerFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("condition"))
			{
				conditionFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("discount"))
			{
				discountFlag = true;	
			}
			else if(elementName.equalsIgnoreCase("accessory"))
			{
				accessoriesFlag=true;	
			}
        }
        catch(Exception e){}
    }
	
	public void endElement(String namespaceURI, String localName, String elementName)
    throws SAXException 
    {
		if(elementName.equalsIgnoreCase("Technology"))
		{
			//out.println("P: "+product);
			technologyList.add(product);
			productMap.put("technologyList",technologyList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("comics"))
		{
			//out.println("P: "+product);
			comicsList.add(product);
			productMap.put("comicsList",comicsList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("Autobiography"))
		{
			//out.println("P: "+product);
			autobiographyList.add(product);
			productMap.put("autobiographyList",autobiographyList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("Sports"))
		{
			//out.println("P: "+product);
			sportsList.add(product);
			productMap.put("sportsList",sportsList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("Education"))
		{
			//out.println("P: "+product);
			educationList.add(product);
			productMap.put("educationList",educationList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("Religion"))
		{
			//out.println("P: "+product);
			religionList.add(product);
			productMap.put("religionList",religionList);
			//out.println(productMap);
		} 
		else if(elementName.equalsIgnoreCase("AccessoriesCatalog"))
		{
			//out.println("P: "+product);
			accessoriesList.add(product);
			productMap.put("accessoriesList",accessoriesList);
			//out.println("\naccessoriesList: "+accessoriesList);
		}  
    }

    public void characters(char buf[], int offset, int len)
    throws SAXException
    {
         try{
            String elementValueHead = new String(buf, offset, len);
            
			if(nameFlag)
			{
				product.setName(elementValueHead);
				//out.println(elementValueHead);
				nameFlag=false;
			}
			else if(priceFlag)
			{
				product.setPrice(elementValueHead);
				//out.println(elementValueHead);
				priceFlag=false;
			}
			else if(imageFlag)
			{
				product.setImage(elementValueHead);
				//out.println(elementValueHead);
				imageFlag=false;
			}
			else if(manufacturerFlag)
			{
				product.setManufacturer(elementValueHead);
				//out.println(elementValueHead);
				manufacturerFlag=false;
			}
			else if(conditionFlag)
			{
				product.setCondition(elementValueHead);
				//out.println(elementValueHead);
				conditionFlag=false;
			}
			else if(discountFlag)
			{
				product.setDiscount(elementValueHead);
				//out.println(elementValueHead);
				discountFlag=false;
			}
			else if(accessoriesFlag)
			{
				accessoryList.add(elementValueHead);
				product.setAccessoryList(accessoryList);
				//out.println(elementValueHead);
				accessoriesFlag=false;
			}
				
			//out.println("Test here 35");
			//out.println(elementValueHead);
            }
        
        catch(Exception e){} 
    } 

	public Map<String,List<Products>> getProductMap()
	{
		return productMap;
	}
	public List<Products> getTechnologyList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		technologyList = dbObj.getProductList("Technology");
		return technologyList;
	}
	public List<Products> getComicsList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		comicsList = dbObj.getProductList("Comics");
		return comicsList;
	}
	public List<Products> getAutobiographyList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		autobiographyList = dbObj.getProductList("Autobiography");
		return autobiographyList;
		//return (productMap.get("headphoneList"));
	}
	public List<Products> getSportsList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		sportsList = dbObj.getProductList("Sports");
		return sportsList;
		//return (productMap.get("phoneList"));
	}
	public List<Products> getEducationList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		educationList = dbObj.getProductList("Education");
		return educationList;
		//return (productMap.get("laptopList"));
	}
	public List<Products> getReligionList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		religionList = dbObj.getProductList("Religion");
		return religionList;
		//return (productMap.get("externalStorageList"));
	}
	public List<Products> getAccessoriesList()
	{
		MySQLDataStoreUtilities dbObj = new MySQLDataStoreUtilities();
		accessoriesList = dbObj.getProductList("AccessoriesList");
		return accessoriesList;
		//return (productMap.get("accessoriesList"));
	}
}
