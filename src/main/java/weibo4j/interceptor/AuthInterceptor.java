package weibo4j.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import weibo4j.context.LoginContextHolder;
import weibo4j.util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 1. 获取loginId
        String loginId = request.getHeader("loginId");

        // 2. 校验必填参数
        if (StringUtils.isEmpty(loginId)) {
            sendError(response, 401, "Missing loginId in header");
            return false;
        }

        // 3. 查询Redis获取Token
        String token = redisUtil.get(loginId);
        if (StringUtils.isEmpty(token)) {
            sendError(response, 401, "Invalid loginId");
            return false;
        }

        // 4. 存入ThreadLocal
        LoginContextHolder.set("loginId", token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) {
        // 清除ThreadLocal数据防止内存泄漏
        LoginContextHolder.remove();
    }

    private void sendError(HttpServletResponse response, int code, String msg) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"code\":" + code + ",\"message\":\"" + msg + "\"}"
        );
    }
}