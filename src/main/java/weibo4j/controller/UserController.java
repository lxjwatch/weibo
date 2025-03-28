package weibo4j.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo4j.OAuth;
import weibo4j.WeiboUserInfo;
import weibo4j.http.AccessToken;
import weibo4j.http.Result;
import weibo4j.model.WeiboException;
import weibo4j.util.RedisUtil;
import weibo4j.util.WeiboConfig;
import weibo4j.vo.UserVO;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/oauth")
public class  UserController {

    @Resource
    private RedisUtil redisUtil;


    private final AuthRequestFactory factory;

    public UserController(AuthRequestFactory factory) {
        this.factory = factory;
    }

    @GetMapping
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors
                .toMap(oauth -> oauth.toLowerCase() + "登录",
                        oauth -> "http://127.0.0.1:8077/oauth/login/" + oauth.toLowerCase()));
    }

    @GetMapping("/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(type + "::" + AuthStateUtils.createState()));
    }

    @RequestMapping("/{type}/callback")
    public Result login(@PathVariable String type, AuthCallback callback) throws WeiboException {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        // 获取用户信息
        if (response.ok()) {
            // 将data转为WeiboUserInfo
            WeiboUserInfo userInfo = JSON.parseObject(JSON.toJSONString(response.getData()), WeiboUserInfo.class);
            // 存储用户信息到 Redis
            String userId = userInfo.getUuid(); // 假设用户信息中有 uid 字段
            redisUtil.set(userId, userInfo.getToken().getAccessToken());
            UserVO userVO = new UserVO();
            userVO.setUuid(userId);
            userVO.setAccessToken(userInfo.getToken().getAccessToken());
            return Result.ok(userVO);
        }
        return Result.fail(response.getMsg());
    }

}
