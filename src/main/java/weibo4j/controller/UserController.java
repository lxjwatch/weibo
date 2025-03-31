package weibo4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weibo4j.Timeline;
import weibo4j.context.LoginContextHolder;
import weibo4j.http.Result;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/showUser")
    public Result getUserShow(@RequestParam("uid") String uuid) throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        User user = timeline.getUserService().showUserById(uuid);
        if (user != null) {
            return Result.ok(user);
        }
        return Result.fail();
    }
}
