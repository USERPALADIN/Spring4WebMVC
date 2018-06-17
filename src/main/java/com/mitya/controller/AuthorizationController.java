package com.mitya.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.HHApi;
import com.github.scribejava.apis.VkontakteApi;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.mitya.model.Role;
import com.mitya.model.User;
import com.mitya.model.VKOAuth2AccessToken;
import com.mitya.service.RoleService;
import com.mitya.service.UserDetailsServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class AuthorizationController {

    private OAuth20Service serviceVk;
    private OAuth20Service serviceG;
    private final
    UserDetailsServiceImpl userDetailsService;
    private final RoleService roleService;

    @Autowired
    public AuthorizationController(UserDetailsServiceImpl userDetailsService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String loginGet(Model model) {
        return "login";
    }

    @RequestMapping(value = "/sign_vk_user", method = RequestMethod.GET)
    public String vkGet(Model model) {
        serviceVk = new ServiceBuilder("6603766")
                .apiSecret("5A1wz3gFCvIKD8XUdPkC")
                //   .scope("wall,ads,notes, email , friends ")
                .callback("http://localhost:8080/callback")
                .build(VkontakteApi.instance());
        String authorizationUrl = serviceVk.getAuthorizationUrl();
        return "redirect:" + authorizationUrl;
    }

    @RequestMapping(value = "/sign_google_user", method = RequestMethod.GET)
    public String googleGet(Model model) {
        serviceG = new ServiceBuilder("355384853970-9gnrdr6dapru9la5qmfi1gsrrkvb892n.apps.googleusercontent.com")
                .apiSecret("P61kW4rfUDIAJFmgN6enOg3Y")
                .scope("https://www.googleapis.com/auth/plus.login")
                .callback("http://localhost:8080/callback_g")
                .build(GoogleApi20.instance());
        String authorizationUrl = serviceG.getAuthorizationUrl();
        return "redirect:" + authorizationUrl;
    }

    @RequestMapping(value = "/callback_g", method = RequestMethod.GET)
    public String callbackG(HttpServletRequest req, @RequestParam(value = "code") String code) {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = serviceG.getAccessToken(code);


        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/plus/v1/people/me");
        request.addParameter("access_token", accessToken.getAccessToken());
        serviceG.signRequest(accessToken, request);
        try {
            Response response = serviceG.execute(request);

            String body = response.getBody();
            JSONObject obj = new JSONObject(body);


            String name = obj.getString("displayName");

            System.out.println(name);
            String name_Role = "ROLE_USER";
            Collection<Role> roles = new ArrayList<>();
            Role role = roleService.getByName(name_Role);
            roles.add(role);

            User user = new User(name);
            userDetailsService.authenticationByToken(user, req, roles);


        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(HttpServletRequest req, @RequestParam(value = "code") String code) {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = serviceVk.getAccessToken(code);
            //  System.out.println(accessToken.getAccessToken());

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        OAuthRequest request = new OAuthRequest(
                Verb.GET,
                "https://api.vk.com/method/users.get?v=5.78");
        request.addParameter("access_token", accessToken.getAccessToken());
        serviceVk.signRequest(accessToken, request);

        try {

            Response response = serviceVk.execute(request);
            String body = response.getBody();

            JSONObject obj = new JSONObject(body);
            JSONArray arr = obj.getJSONArray("response");
            String value = arr.get(0).toString();
            JSONObject obj_value = new JSONObject(value);
            String name = obj_value.getString("first_name");
            String last = obj_value.getString("last_name");

            System.out.println(name + " " + last);

            String name_Role = "ROLE_USER";
            Collection<Role> roles = new ArrayList<>();
            Role role = roleService.getByName(name_Role);
            roles.add(role);
            User user = new User(name + " " + last);
            userDetailsService.authenticationByToken(user, req, roles);

        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        return "redirect:/user";
    }

}
