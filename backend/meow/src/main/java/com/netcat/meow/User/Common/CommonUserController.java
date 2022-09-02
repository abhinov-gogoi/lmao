package com.netcat.meow.User.Common;

import com.netcat.meow.Service.TokenService;
import com.netcat.meow.User.Auth.UserService;
import com.netcat.meow.Utility.Literal;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CommonUserController {

    Logger logger = LoggerFactory.getLogger(CommonUserController.class);

    private CommonUserService commonUserService;
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    public CommonUserController() {
        commonUserService = CommonUserService.getInstance();
        userService = UserService.getInstance();
    }

    @RequestMapping(value = "api/v1/common/user/test", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
    public String commonUserControllerTest() {
        logger.info("commonUserControllerTest api hit");
        return "SUCCESS";
    }

    @RequestMapping(value = "api/v1/common/user", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> getUserData() {
        Map<String, Object> ret_map = new HashMap<>();

        logger.info("getUserData api hit");
        try {
            String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
            String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
            logger.info(accessToken);
            logger.info(login_id);
            if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
                logger.info("token validation failed" + request.getRequestURI());
                return ret_map;
            }

            Document userDetails = userService.getUserDetails(login_id);

            ret_map.put(Literal.id, userDetails.get(Literal._id).toString());
            ret_map.put(Literal.name, userDetails.get(Literal.name));
            ret_map.put(Literal.email, userDetails.get(Literal.login_id));
            ret_map.put(Literal.avatar, userDetails.get(Literal.avatar));
            ret_map.put(Literal.status, userDetails.get(Literal.active_status));
            return ret_map;
        } catch (Exception e) {
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            ret_map.put(Literal.EXCEPTION_MESAGE, e.getMessage());
            return ret_map;
        }


    }

    @RequestMapping(value = "api/v1/common/user/profilePic", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> uploadProfilePic(@RequestBody Map<String, Object> req_map) {
        logger.info("uploadProfilePic api hit");
        Map<String, Object> ret_map = new HashMap<>();

        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            ret_map.put(Literal.STATUS, Literal.ERROR);
            ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
            return ret_map;
        }

        if(userService.uploadProfilePic(req_map.get(Literal.image).toString(), login_id)) {
            ret_map.put(Literal.STATUS, Literal.SUCCESS);
            ret_map.put(Literal.MESSAGE, Literal.DATA_UPDATED);
            return ret_map;
        }
        ret_map.put(Literal.STATUS, Literal.ERROR);
        ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
        return ret_map;
    }


}