package weibo4j.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import weibo4j.http.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment extends WeiboResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1272011191310628589L;
    private Date createdAt;                    //评论时间
    private long id;                           //评论id
    private String mid;                           //评论id
    private String idstr;                       //评论id
    private String text;                       //评论内容
    private String source;                     //内容来源
    private Comment replyComment = null;       //回复的评论内容
    private User user = null;                  //User对象
    private Status status = null;              //Status对象

    /*package*/
    public Comment(Response res) throws WeiboException {
        super(res);
        JSONObject json = res.asJSONObject();
        try {
            id = json.getLong("id");
            mid = json.getString("mid");
            idstr = json.getString("idstr");
            text = json.getString("text");
            if (json.has("source") && !StringUtils.isEmpty(json.getString("source"))) {
                source = json.getString("source");
            }
            createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            if (!json.isNull("user"))
                user = new User(json.getJSONObject("user"));
            if (!json.isNull("status"))
                status = new Status(json.getJSONObject("status"));
            if (!json.isNull("reply_comment"))
                replyComment = (new Comment(json.getJSONObject("reply_comment")));
        } catch (JSONException je) {
            throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
        }
    }

    public Comment(JSONObject json) throws WeiboException, JSONException {
        id = json.getLong("id");
        mid = json.getString("mid");
        idstr = json.getString("idstr");
        text = json.getString("text");
        if (json.has("source") && !StringUtils.isEmpty(json.getString("source"))) {
            source = json.getString("source");
        }
        createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        if (!json.isNull("user"))
            user = new User(json.getJSONObject("user"));
        if (!json.isNull("status"))
            status = new Status(json.getJSONObject("status"));
        if (!json.isNull("reply_comment"))
            replyComment = (new Comment(json.getJSONObject("reply_comment")));
    }

    public Comment(String str) throws WeiboException, JSONException {
        // StatusStream uses this constructor
        super();
        JSONObject json = new JSONObject(str);
        id = json.getLong("id");
        mid = json.getString("mid");
        idstr = json.getString("idstr");
        text = json.getString("text");
        source = json.getString("source");
        createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        if (!json.isNull("user"))
            user = new User(json.getJSONObject("user"));
        if (!json.isNull("status"))
            status = new Status(json.getJSONObject("status"));
        if (!json.isNull("reply_comment"))
            replyComment = (new Comment(json.getJSONObject("reply_comment")));
    }

    public static CommentWrapper constructWapperComments(Response res) throws WeiboException {
        JSONObject json = res.asJSONObject(); //asJSONArray();
        try {
            JSONArray comments = json.getJSONArray("comments");
            int size = comments.length();
            List<Comment> comment = new ArrayList<Comment>(size);
            for (int i = 0; i < size; i++) {
                comment.add(new Comment(comments.getJSONObject(i)));
            }
            long previousCursor = json.optLong("previous_cursor");
            long nextCursor = json.optLong("next_cursor");
            long totalNumber = json.optLong("total_number");
            String hasvisible = json.optString("hasvisible");
            return new CommentWrapper(comment, previousCursor, nextCursor, totalNumber, hasvisible);
        } catch (JSONException e) {
            throw new WeiboException(e);
        }
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public Comment getReplyComment() {
        return replyComment;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Comment [createdAt=" + createdAt + ", id=" + id + ", mid="
                + mid + ", idstr=" + idstr + ", text=" + text + ", source="
                + source + ", replyComment=" + replyComment + ", user=" + user
                + ", status=" + status + "]";
    }

}
