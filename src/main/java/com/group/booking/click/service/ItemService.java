package com.group.booking.click.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.business.AvailabilityProcessor;
import com.group.booking.click.business.ItemProcessor;
import com.group.booking.click.model.Image;
import com.group.booking.click.model.Item;
import com.group.booking.click.model.ItemSearchCriteria;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@CrossOrigin(origins="http://localhost:8081", maxAge=3600)
@RequestMapping("/item")
public class ItemService {
 
    public static final Logger logger = LoggerFactory.getLogger(ItemService.class);
 
    @Autowired
    AvailabilityProcessor avaliabilityProcessor; //Service which will do all data retrieval/manipulation work
    @Autowired
    ItemProcessor itemProcessor;
    
    
    /*---------------------------- Item Details --------------------------------------------------------------------*/

    /**
     * Method to fetch all items based on type
     * @param type
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/fetch", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.POST})
    @ResponseBody
    public List<Item> getItems(@RequestBody Item item) throws CustomException {
     
    	if(false){
    		throw new CustomException();
    	} else {
    		List<Item> itemDetailsList = itemProcessor.fetchItems(item);
            return itemDetailsList;
    	}
    }
    /**
     * Method to filter results based on the criteria
     * @param ItemSearchCriteria
     * @return List<Item>
     * @throws CustomException
     */
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ResponseBody
    public List<Item> getFilteredItems(@RequestBody ItemSearchCriteria itemSearchCriteria) throws CustomException {
     
    	if(false){
    		throw new CustomException();
    	} else {
    		List<Item> itemDetailsList = itemProcessor.filteredItems(itemSearchCriteria);
            return itemDetailsList;
    	}
    }
    
    /**
     * Method to fetch item details based on itemId
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/{itemId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public Item getItemDetails(@PathVariable("itemId") String itemId) {
     
    	Item itemDetails = itemProcessor.retrieveItemDetails(itemId);
        return itemDetails;
    }
    
    /**
     * Method to fetch item details based on itemId
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/vendor/{vendorId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItemsBasedOnVendorId(@PathVariable("vendorId") String vendorId) {
     
    	List<Item> itemDetailsList = itemProcessor.getItemsBasedOnVendorId(vendorId);
        return itemDetailsList;
    }
    
    /**
     * Method to insert a new item
     * @param itemDetails
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Item createItem(@RequestBody Item itemDetails) throws JsonProcessingException {
       
    	itemProcessor.insertItemDetails(itemDetails);
        return itemDetails;
    }
    
    /**
     * Method to update item details
     * @param itemDetails
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Item updateItem(@RequestBody Item itemDetails) throws JsonProcessingException {
       
    	itemProcessor.updateItemDetails(itemDetails);
        return itemDetails;
    }
    
    ///Need to implemet the logic..Need to check the booking status when deactivate and send mail to all concerned...
    public Item updateItemStatus(@RequestBody Item itemDetails) throws JsonProcessingException {
        
    	itemProcessor.updateItemDetails(itemDetails);
        return itemDetails;
    }
   
    
    //Not being used now...Need to change the existing with this
    /**
     * Method to retrieve booked details based on vendorId
     * @param vendorId
     * @return List<Booking>
     */
    @RequestMapping(value = "/itemBooking/{vendorId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItemsForBookingByVendor(@PathVariable("vendorId") String vendorId) {
       
    	List<Item> itemList = itemProcessor.getItemsForBookingByVendor(vendorId);
    	return itemList;
    }

    
	@RequestMapping(value = "/image/upload/{itemId}/{mainImageUrl}", method = RequestMethod.POST/*
																		 * ,
																		 * consumes=MediaType.MULTIPART_FORM_DATA_VALUE
																		 */)
    @ResponseBody
    public Item imageUpload(@PathVariable("itemId") String itemId, @PathVariable("mainImageUrl") boolean isMainImage, MultipartHttpServletRequest request, HttpServletResponse response) 
    		throws IllegalStateException, IOException {
	
		Iterator<String> itr = request.getFileNames();
		java.util.Map<java.lang.String,java.lang.String[]> ParameterMap=request.getParameterMap();
	    MultipartFile file=null;

	    while (itr.hasNext()) {
	        file = request.getFile(itr.next());
	        String fileName = file.getOriginalFilename();//request.getParameter("filename");

	        String location = System.getProperty("user.dir");
			location = location + "/src/main/resources/";
			File pathFile = new File(location);
			//create the actual file
			pathFile = new File(location + fileName);
			//save the actual file
			try {
				file.transferTo(pathFile);   
			}catch(Exception e) {
				System.out.println("lllll");
			}
			InputStream inputStream = new FileInputStream(location+fileName);
	        itemProcessor.saveImageNames(itemId, fileName, isMainImage);
		   	itemProcessor.saveImage(inputStream, fileName);
	    }
		
    	return null;
    }
	
	@RequestMapping(value = "/image/retrieve/{fileName}", produces="image/jpeg" ,method = RequestMethod.GET)
	public @ResponseBody void retrieveImage(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IllegalStateException, IOException {
		GridFSDBFile imageForOutput =  (GridFSDBFile) itemProcessor.retrieveImages(fileName);
		
		//kk.writeTo("C:/projects/kk.jpg");
		
		 InputStream is = imageForOutput.getInputStream();
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();
         int nRead;
         byte[] data = new byte[16384];
         while ((nRead = is.read(data, 0, data.length)) != -1) {
             buffer.write(data, 0, nRead);
         }
         buffer.flush();
         byte[]imagenEnBytes = buffer.toByteArray();

         response.setHeader("Accept-ranges","bytes");
         response.setContentType( "image/jpeg" );
         response.setContentLength(imagenEnBytes.length);
         response.setHeader("Expires","0");
         response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
         response.setHeader("Content-Description","File Transfer");
         response.setHeader("Content-Transfer-Encoding:","binary");

         OutputStream out = response.getOutputStream();
         out.write( imagenEnBytes );
         out.flush();
         out.close();
		
     }

	/*@RequestMapping(value = "/imageNames/{itemId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Images> getImageDetails(@PathVariable("itemId") String itemId) {
       
    	List<Images> itemList = itemProcessor.getImageDetails(itemId);
    	return itemList;
    }*/

 
}
