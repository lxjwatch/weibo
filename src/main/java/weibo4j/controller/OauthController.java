package weibo4j.controller;

import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo4j.WeiboUserInfo;
import weibo4j.model.WeiboException;
import weibo4j.util.RedisUtil;
import weibo4j.vo.UserVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private RedisUtil redisUtil;


    private final AuthRequestFactory factory;

    public OauthController(AuthRequestFactory factory) {
        this.factory = factory;
    }

    @GetMapping
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors
                .toMap(oauth -> oauth.toLowerCase() + "登录",
                        oauth -> "http://127.0.0.1:8077/oauth/login/" + oauth.toLowerCase()));
    }

    /**
     * 登录 返回给前端回调地址
     *
     * @param type
     * @param response
     * @throws IOException
     */
    @GetMapping("/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        String authorize = authRequest.authorize(AuthStateUtils.createState());
        response.sendRedirect(authorize);
    }

    @RequestMapping("/{type}/callback")
    public void login(@PathVariable String type, AuthCallback callback, HttpServletResponse response2) throws WeiboException, IOException {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        // 获取用户信息
        if (response.ok()) {
            // 将data转为WeiboUserInfo
            WeiboUserInfo userInfo = JSON.parseObject(JSON.toJSONString(response.getData()), WeiboUserInfo.class);
            // 存储用户信息到 Redis
            String userId = userInfo.getUuid(); // 假设用户信息中有 uid 字段
            redisUtil.setNx(userId, userInfo.getToken().getAccessToken(), (long) (3600 * 3), TimeUnit.SECONDS);
            UserVO userVO = new UserVO();
            userVO.setUuid(userId);
            userVO.setAccessToken(userInfo.getToken().getAccessToken());
            //uservo转为json
            String json1 = JSON.toJSONString(userVO);
            // 将用户数据转为JSON字符串并编码
            String json = URLEncoder.encode(
                    json1,
                    "UTF-8"
            );
            response2.sendRedirect("http://127.0.0.1:8077/home.html?data=" + json);
        }
    }

}
