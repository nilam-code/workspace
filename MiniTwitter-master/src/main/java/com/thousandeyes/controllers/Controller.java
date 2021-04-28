package com.thousandeyes.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import com.thousandeyes.services.TwitterService;
import com.thousandeyes.mappers.*;
import com.thousandeyes.repositories.*;
import java.util.*;



@RestController
public class Controller {

    @Autowired
    private TwitterService ts;       

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //gets logged in username
            return "{\"message\": \"Welcome to Mini Twitter App, " + username + "!\"}";            
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    public ArrayList<Person> following(@RequestParam(value="name", defaultValue="unknown") String name) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name.contains("unknown"))
            name = username;
        List ar = ts.following(name);
        ArrayList<Person> foll = new ArrayList<Person>(ar);
        return foll;
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public ArrayList<Person> followers(@RequestParam(value="name", defaultValue="unknown") String name) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name.contains("unknown"))
            name = username;
        List ar = ts.followers(name);
        ArrayList<Person> foll = new ArrayList<Person>(ar);
        return foll;
    }

    @RequestMapping(value = "/follow", method = { RequestMethod.PUT, RequestMethod.POST })
    public String follow(@RequestParam(value="name", required=true) String name) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ts.follow(name, username) == 1)
          return "{\"status\": \"Success\", \"message\": \"You have followed the user successfully\"} ";
        else if (ts.follow(name, username) == 2)
          return "{\"status\": \"Error\", \"message\": \"You cannot follow yourself\"}";
        else if (ts.follow(name, username) == 0)
          return "{\"status\": \"Error\", \"message\": \"You already follow the user\"}";
        else
          return "{\"status\": \"Error\", \"message\": \"None\"}";       
    }

    @RequestMapping(value = "/unfollow", method = RequestMethod.PUT)
    public String unfollow(@RequestParam(value="name", required=true) String name) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ts.unfollow(name, username) == 1)
          return "{\"status\": \"Success\", \"message\": \"You have unfollowed the user successfully\"} ";
        else if (ts.follow(name, username) == 2)
          return "{\"status\": \"Error\", \"message\": \"You cannot unfollow yourself\"}";
        else if (ts.follow(name, username) == 0)
          return "{\"status\": \"Error\", \"message\": \"You need to follow the user to unfollow\"}";
        else
          return "{\"status\": \"Error\", \"message\": \"None\"}";     
    }

    @RequestMapping(value="/feed", method = RequestMethod.GET)
    public ArrayList<Tweet> feed(@RequestParam(value="search", defaultValue="not_given") String search) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (search.contains("not_given"))
            search = "";        
        List ar = ts.feed(username, search);
        ArrayList<Tweet> foll = new ArrayList<Tweet>(ar);
        return foll;
    }

    @RequestMapping(value = "/popularfollower", method = RequestMethod.GET)
    public String popularfollower() {

        return ts.popularFollower();
    }        

}