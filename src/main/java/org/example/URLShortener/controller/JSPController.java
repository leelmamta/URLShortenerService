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

//    // Process shortening request
//    @PostMapping("/processURLShortenerResponse")
//    public String processShortenRequest(@RequestParam("longURL") String longURL,
//                                        @RequestParam(value = "customURL", required = false) String customURL,
//                                        @RequestParam(value = "expiryTimeStamp", required = false) String expiryTimeStamp,
//                                        @RequestParam(value = "user_Id", required = false) String userId,
//                                        Model model) {
//        URLRequest urlRequest = new URLRequest();
//        urlRequest.setLongURL(longURL);
//        urlRequest.setCustomURL(customURL);
//        urlRequest.setUser_Id(userId);
//        urlRequest.setExpiryTimeStamp(expiryTimeStamp);
//
//        URLResponse urlResponse = urlShortenerService.processShortening(urlRequest).getBody();
//        model.addAttribute("shortenedURL", urlResponse.getTinyURL());
//        model.addAttribute("originalURL", longURL);
//        model.addAttribute("createdTimeStamp", urlResponse.getCreatedTimeStamp());
//        model.addAttribute("expiryTimeStamp", urlResponse.getExpiryTimeStamp());
//        model.addAttribute("user_ID", urlResponse.getUser_Id());
//        return "processURLShortenerResponse"; // Forward to shortenResponse.jsp
//    }
//
//    // Show the form for retrieving the original URL
//    @GetMapping("/showURLRetrievalForm")
//    public String showRetrieveForm() {
//        return "showURLRetrievalForm"; // Renders retrieveForm.jsp
//    }
//
//    // Process retrieval request
//    @PostMapping("/processURLRetrievalResponse")
//    public String processRetrieveRequest(@RequestParam("tinyURL") String tinyURL, Model model) {
//        URLResponse urlResponse = urlShortenerService.fetchURLResponse(tinyURL);
//        model.addAttribute("shortenedURL", tinyURL);
//        model.addAttribute("originalURL", urlResponse.getLongURL());
//
//        return "processURLRetrievalResponse"; // Forward to retrieveResponse.jsp
//    }
}
