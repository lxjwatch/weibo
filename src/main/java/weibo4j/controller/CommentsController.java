package weibo4j.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.context.LoginContextHolder;
import weibo4j.http.Result;
import weibo4j.model.WeiboException;

@RestController
@Slf4j
@RequestMapping("/comments")
public class CommentsController {


    /**
     * 根据微博ID返回某条微博的评论列表
     */
    @GetMapping("/show")
    public Result show(String id) throws WeiboException {
        Comments comments = new Comments(LoginContextHolder.getLoginId());
        return Result.ok(comments.getCommentById(id));
    }
    /**
     * 获取最新的提到当前登录用户的评论，即@我的评论
     */
    @GetMapping("/mentions")
    public Result mentions() throws WeiboException {
        Comments comments = new Comments(LoginContextHolder.getLoginId());
        return Result.ok(comments.getCommentMentions());
    }
    /**
     * 评论一条微博
     */
    @GetMapping("/create")
    public Result create(String comment, String id) throws WeiboException {
        Comments comments = new Comments(LoginContextHolder.getLoginId());
        return Result.ok(comments.createComment(comment, id,"183.156.91.245"));
    }
    /**
     * 回复一条评论
     */
    @GetMapping("/reply")
    public Result reply(String comment, String id, String cid) throws WeiboException {
        Comments comments = new Comments(LoginContextHolder.getLoginId());
        return Result.ok(comments.replyComment(cid, id, comment,"183.156.91.245"));
    }
}
