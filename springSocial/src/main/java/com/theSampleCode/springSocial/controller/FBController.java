package com.theSampleCode.springSocial.controller;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FBController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public FBController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        model.addAttribute("userimg",connection.createData().getImageUrl());
        String [] fields = { "id","name","birthday","email","location","hometown","gender","first_name","last_name"};
        //User user = facebook.fetchObject("me", User.class, fields);
        model.addAttribute("facebookProfile", facebook.fetchObject("me", User.class, fields));
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "fbHello";
    }

}