package weibo4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo4j.Timeline;
import weibo4j.context.LoginContextHolder;
import weibo4j.http.Result;
import weibo4j.model.StatusWrapper;
import weibo4j.model.WeiboException;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping(value = "/statuses")
public class StatusesController {

    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     */
    @GetMapping("home_timeline")
    public Result homeTimeline() throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getHomeTimeline());
    }

    /**
     * 获取授权用户发布的微博
     */
    @GetMapping("user_timeline")
    public Result userTimeline() throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getUserTimeline());
    }

    /**
     * 获取指定微博的转发微博列表
     */
    @GetMapping("repost_timeline")
    public Result repostTimeline(String id) throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getRepostTimeline(id));
    }
    /**
     * 获取最新的提到登录用户的微博列表，即@我的微博
     */
    @GetMapping("mentions")
    public Result mentions() throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getMentions());
    }

    /**
     * 根据微博ID获取单条微博内容
     */
    @GetMapping("show")
    public Result show(String id) throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.showStatus(id));
    }

    /**
     * 批量获取指定微博的转发数评论数
     */
    @GetMapping("count")
    public Result count(String statusIds) throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getStatusCount(statusIds));
    }

    /**
     * 获取微博官方表情的详细信息
     */
    @GetMapping("emotions")
    public Result emotions() throws WeiboException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());
        return Result.ok(timeline.getEmotions());
    }

    /**
     * 第三方分享链接到微博
     */
    @PostMapping("share")
    public Result share(String status) throws WeiboException, UnknownHostException {
        Timeline timeline = new Timeline(LoginContextHolder.getLoginId());

        //获取ip地址
        String hostAddress = "183.156.91.245";
        return Result.ok(timeline.share(status, hostAddress));
    }



}
