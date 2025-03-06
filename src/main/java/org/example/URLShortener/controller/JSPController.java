package org.example.URLShortener.controller;

import org.example.URLShortener.model.URLRequest;
import org.example.URLShortener.model.URLResponse;
import org.example.URLShortener.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui")
public class JSPController {

    @Autowired
    private URLShortenerService urlShortenerService;

    @GetMapping("/showURLShortenerForm")
    public String showURLShortenerForm() {
        return "showURLShortenerForm"; // Renders showURLShortenerForm.jsp
    }

    // Process shortening request
    @PostMapping("/processURLShortenerResponse")
    public String processShortenRequest( @RequestParam(value = "customURL", required = false) String customURL,
                                         @RequestParam("longURL") String longURL,
                                        @RequestParam(value = "expiryTimeStamp", required = false) String expiryTimeStamp,
                                        @RequestParam(value = "user_Id", required = false) String user_Id,
                                        Model model) {
        URLRequest urlRequest = new URLRequest();
        urlRequest.setLongURL(longURL);
        urlRequest.setCustomURL(customURL);
        urlRequest.setUser_Id(user_Id);
        urlRequest.setExpiryTimeStamp(expiryTimeStamp);

        URLResponse urlResponse = urlShortenerService.processShortening(urlRequest).getBody();
        model.addAttribute("customURL", urlResponse.getTinyURL());
        model.addAttribute("longURL", longURL);
        model.addAttribute("expiryTimeStamp", urlResponse.getExpiryTimeStamp());
        model.addAttribute("createdTimeStamp", urlResponse.getCreatedTimeStamp());
        model.addAttribute("userId", urlResponse.getUser_Id());
        return "processURLShortenerResponse"; // Forward to shortenResponse.jsp
    }

//    Show the form for retrieving the original URL
    @GetMapping("/showURLRetrievalForm")
    public String showRetrieveForm() {
        return "showURLRetrievalForm"; // Renders retrieveForm.jsp
    }

    // Process retrieval request
    @PostMapping("/processURLRetrievalResponse")
    public String processRetrieveRequest(@RequestParam("tinyURL") String tinyURL,
                                         @RequestParam("originalURL") String originalURL,  Model model) {
        URLResponse urlResponse = urlShortenerService.fetchURLResponse(tinyURL);
        model.addAttribute("tinyURL", tinyURL);
        model.addAttribute("originalURL", originalURL);

        return "processURLRetrievalResponse"; // Forward to retrieveResponse.jsp
    }
}
