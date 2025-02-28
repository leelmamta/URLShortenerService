### Feature -01 
Taking URL information in requestBody -> and return short URL Response           
User can Customize the shortURL                      
User can customize the URlExpiryTime, defaulting of URLexpiryTime            
I will be using Hashing technique to short it


### Feature -02 
AOP - Schema Validation of requestBody and Logging 

### Feature -03 
API ShortURL Redirection                                       
Endpoint: GET /{short_url_key}                                            
This endpoint redirects the user to the original long URL.                                  
Response                                                                     
Sample Response:                                                                                 
HTTP/1.1 301 Moved Permanently Location: https://www.example.com/some/very/long/url

### Feature-04   
Caching of URLs


### Feature-05 
Create a utility service to handle Exceptions and Customize it. 
