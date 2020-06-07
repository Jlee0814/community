package com.community.controller;

import com.community.dto.AccessTokenDTO;
import com.community.dto.GithubUser;
import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String uri;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String Authorize(@RequestParam(name="code") String code, @RequestParam(name="state") String state,
                            HttpServletResponse response){

        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        //System.out.println(user.getLogin());
        if(githubUser!=null){
            //success
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setGmtCreate(System.currentTimeMillis());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
           return "redirect:/";
        }else
            return "redirect:/";
        //return "index";
    }
}
