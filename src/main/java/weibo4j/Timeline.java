package weibo4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import weibo4j.model.*;
import weibo4j.util.WeiboConfig;

import java.util.List;

public class Timeline extends Weibo {
    private static final long serialVersionUID = -2211659387124870836L;

    public Timeline(String accessToken) {
        super(accessToken);
    }

    public Timeline() {
    }

    /*----------------------------读取接口----------------------------------------*/

    /**
     * 返回最新的公共微博
     *
     * @return list of statuses of the Public Timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
     * </a>
     * @since JDK 1.5
     */
    public StatusWrapper getPublicTimeline() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/public_timeline.json"));
    }

    /**
     * 返回最新的公共微博
     *
     * @param count   单页返回的记录条数，默认为20。
     * @param baseApp 是否仅获取当前应用发布的信息。0为所有，1为仅本应用。
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
     * </a>
     * @since JDK 1.5
     */
    public StatusWrapper getPublicTimeline(int count, int baseApp) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/public_timeline.json", new HttpParameter[]{
                        new HttpParameter("count", count),
                        new HttpParameter("base_app", baseApp)}));

    }

    /**
     * 获取当前登录用户及其所关注用户的最新20条微博消息。
     * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
     * This method calls
     * http://api.t.sina.com.cn/statuses/friends_timeline.format
     *
     * @return list of the Friends Timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
     * statuses/friends_timeline </a>
     * @since JDK 1.5
     */
    public StatusWrapper getFriendsTimeline() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json"));

    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博消息。<br/>
     * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
     *
     * @param paging  相关分页参数
     * @param feature 过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @return list of the Friends Timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
     * statuses/friends_timeline </a>
     * @since JDK 1.5
     */
    public StatusWrapper getFriendsTimeline(Integer baseAPP, Integer feature,
                                            Paging paging) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json",
                new HttpParameter[]{
                        new HttpParameter("base_app", baseAPP.toString()),
                        new HttpParameter("feature", feature.toString())},
                paging));
    }

    /**
     * 获取当前登录用户及其所关注用户的最新20条微博消息。
     * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
     * This method calls
     * http://api.t.sina.com.cn/statuses/friends_timeline.format
     *
     * @return list of the Friends Timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline/ids">
     * statuses/friends_timeline/ids </a>
     * @since JDK 1.5
     */
    public JSONObject getFriendsTimelineIds() throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json").asJSONObject();

    }

    public JSONObject getFriendsTimelineIds(Integer baseAPP, Integer feature,
                                            Paging paging) throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json",
                new HttpParameter[]{
                        new HttpParameter("base_app", baseAPP.toString()),
                        new HttpParameter("feature", feature.toString())},
                paging).asJSONObject();
    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博消息。<br/>
     * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
     *
     * @return list of status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a href="http://open.weibo.com/wiki/2/statuses/home_timeline">
     * statuses/home_timeline </a>
     * @since JDK 1.5
     */
    public StatusWrapper getHomeTimeline() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/home_timeline.json")
        );

    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博消息。<br/>
     * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
     *
     * @param paging  相关分页参数
     * @param feature 过滤类型ID ，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @return list of the Friends Timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a href="http://open.weibo.com/wiki/2/Statuses/home_timeline">
     * statuses/home_timeline </a>
     * @since JDK 1.5
     */
    public StatusWrapper getHomeTimeline(Integer baseAPP, Integer feature,
                                         Paging paging) throws WeiboException {
        return Status
                .constructWrapperStatus(client.get(
                        WeiboConfig.getValue("baseURL") + "statuses/home_timeline.json",
                        new HttpParameter[]{
                                new HttpParameter("base_app", baseAPP.toString()),
                                new HttpParameter("feature", feature.toString())},
                        paging));
    }

    /**
     * 获取某个用户最新发表的微博列表
     *
     * @return list of the user_timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
     * @since JDK 1.5
     */
    public StatusWrapper getUserTimeline() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/user_timeline.json"));
    }

    public StatusWrapper getUserTimelineByUid(String uid) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/user_timeline.json", new HttpParameter[]{
                new HttpParameter("uid", uid)
        }));
    }

    public StatusWrapper getUserTimelineByName(String screen_name) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/user_timeline.json", new HttpParameter[]{
                new HttpParameter("screen_name", screen_name)
        }));
    }

    /**
     * 获取某个用户最新发表的微博列表
     *
     * @param uid     需要查询的用户ID。
     * @param page    返回结果的页码，默认为1。
     * @param baseApp 是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
     * @param feature 过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @return list of the user_timeline
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
     * @since JDK 1.5
     */
    public StatusWrapper getUserTimelineByUid(String uid, Paging page,
                                              Integer baseApp, Integer feature) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/user_timeline.json",
                new HttpParameter[]{
                        new HttpParameter("uid", uid),
                        new HttpParameter("base_app", baseApp.toString()),
                        new HttpParameter("feature", feature.toString())},
                page));
    }

    public StatusWrapper getUserTimelineByName(String screen_name, Paging page, Integer base_app, Integer feature) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/user_timeline.json",
                new HttpParameter[]{
                        new HttpParameter("screen_name", screen_name),
                        new HttpParameter("base_app", base_app.toString()),
                        new HttpParameter("feature", feature.toString())},
                page));
    }

    /**
     * 获取某个用户最新发表的微博列表ID
     *
     * @return user_timeline IDS
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
     * @since JDK 1.5
     */
    public JSONObject getUserTimelineIdsByUid(String uid) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "statuses/user_timeline/ids.json", new HttpParameter[]{
                new HttpParameter("uid", uid)
        }).asJSONObject();
    }

    public JSONObject getUserTimelineIdsByName(String screen_name) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "statuses/user_timeline/ids.json", new HttpParameter[]{
                new HttpParameter("screen_name", screen_name)
        }).asJSONObject();
    }

    /**
     * 获取指定微博的转发微博列表
     *
     * @param id 需要查询的微博ID
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
     * @since JDK 1.5
     */
    public StatusWrapper getRepostTimeline(String id) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL")
                        + "statuses/repost_timeline.json",
                new HttpParameter[]{new HttpParameter("id", id)}));
    }

    /**
     * 获取指定微博的转发微博列表
     *
     * @param id   需要查询的微博ID
     * @param page 返回结果的页码，默认为1
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
     * @since JDK 1.5
     */
    public StatusWrapper getRepostTimeline(String id, Paging page)
            throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL")
                        + "statuses/repost_timeline.json",
                new HttpParameter[]{new HttpParameter("id", id)}, page));
    }

    /**
     * 获取指定微博的转发微博列表
     *
     * @param id 需要查询的微博ID
     * @return ids
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost_timeline/ids">statuses/repost_timeline/ids</a>
     * @since JDK 1.5
     */
    public JSONObject getForwardTimelineIds(String id) throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "statuses/repost_timeline/ids.json",
                new HttpParameter[]{new HttpParameter("id", id)}).asJSONObject();
    }

    /**
     * 获取当前用户最新转发的微博列表
     *
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
     * @since JDK 1.5
     */
    public StatusWrapper getForwardByMe() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/repost_by_me.json"));
    }

    /**
     * 获取当前用户最新转发的微博列表
     *
     * @param page 返回结果的页码，默认为1
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
     * @since JDK 1.5
     */
    public StatusWrapper getForwardByMe(Paging page) throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/repost_by_me.json", null, page));
    }

    /**
     * 获取最新的提到登录用户的微博列表，即@我的微博
     *
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
     * @since JDK 1.5
     */
    public StatusWrapper getMentions() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig
                .getValue("baseURL") + "statuses/mentions.json"));
    }

    /**
     * 获取最新的提到登录用户的微博列表，即@我的微博
     *
     * @param page             返回结果的页码，默认为1。
     * @param filter_by_author 作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
     * @param filter_by_source 来源筛选类型，0：全部、1：来自微博、2：来自微群，默认为0。
     * @param filter_by_type   原创筛选类型，0：全部微博、1：原创的微博，默认为0。
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
     * @since JDK 1.5
     */
    public StatusWrapper getMentions(Paging page, Integer filter_by_author,
                                     Integer filter_by_source, Integer filter_by_type)
            throws WeiboException {
        return Status.constructWrapperStatus(client.get(
                WeiboConfig.getValue("baseURL") + "statuses/mentions.json",
                new HttpParameter[]{
                        new HttpParameter("filter_by_author", filter_by_author.toString()),
                        new HttpParameter("filter_by_source", filter_by_source.toString()),
                        new HttpParameter("filter_by_type", filter_by_type.toString())}, page));
    }

    /**
     * 获取最新的提到登录用户的微博ID列表，即@我的微博
     *
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/mentions/ids">statuses/mentions/ids</a>
     * @since JDK 1.5
     */
    public JSONObject getMentionsIds() throws WeiboException {
        return client.get(WeiboConfig
                .getValue("baseURL") + "statuses/mentions/ids.json").asJSONObject();
    }

    public JSONObject getMentionsIds(Paging page, Integer filter_by_author,
                                     Integer filter_by_source, Integer filter_by_type)
            throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "statuses/mentions/ids.json",
                new HttpParameter[]{
                        new HttpParameter("filter_by_author", filter_by_author.toString()),
                        new HttpParameter("filter_by_source", filter_by_source.toString()),
                        new HttpParameter("filter_by_type", filter_by_type.toString())}, page).asJSONObject();
    }

    /**
     * 获取双向关注用户的最新微博
     *
     * @return list of Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/bilateral_timeline">statuses/bilateral_timeline</a>
     * @since JDK 1.5
     */
    public StatusWrapper getBilateralTimeline() throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getValue("baseURL") + "statuses/bilateral_timeline.json"));
    }

    public StatusWrapper getBilateralTimeline(Integer base_app, Integer feature) throws WeiboException {
        return Status.constructWrapperStatus(client.get(WeiboConfig.getValue("baseURL") + "statuses/bilateral_timeline.json",
                new HttpParameter[]{
                        new HttpParameter("base_app", base_app),
                        new HttpParameter("feature", feature)
                }));
    }

    /**
     * 根据微博ID获取单条微博内容
     *
     * @param id 需要获取的微博ID。
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.1
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/show">statuses/show</a>
     * @since JDK 1.5
     */
    public Status showStatus(String id) throws WeiboException {
        return new Status(client.get(WeiboConfig.getValue("baseURL")
                        + "statuses/show.json",
                new HttpParameter[]{new HttpParameter("id", id)}));
    }

    /**
     * 通过微博ID获取其MID
     *
     * @param id   需要查询的微博ID，批量模式下，用半角逗号分隔，最多不超过20个。
     * @param type 获取类型，1：微博、2：评论、3：私信，默认为1。
     * @return Status's mid
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
     * @since JDK 1.5
     */
    public JSONObject QueryMid(Integer type, String id) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
                new HttpParameter[]{new HttpParameter("id", id),
                        new HttpParameter("type", type.toString())}).asJSONObject();
    }

    /**
     * 通过微博ID获取其MID
     *
     * @param id       需要查询的微博ID，批量模式下，用半角逗号分隔，最多不超过20个。
     * @param type     获取类型，1：微博、2：评论、3：私信，默认为1。
     * @param is_batch 是否使用批量模式，0：否、1：是，默认为0。
     * @return Status's mid
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
     * @since JDK 1.5
     */
    public JSONObject QueryMid(Integer type, String id, int is_batch) throws WeiboException {
        return client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
                new HttpParameter[]{new HttpParameter("id", id),
                        new HttpParameter("type", type.toString()),
                        new HttpParameter("is_batch", is_batch)}).asJSONObject();
    }

    /**
     * 通过微博MID获取其ID
     *
     * @param mid  true string 需要查询的微博MID，批量模式下，用半角逗号分隔，最多不超过20个
     * @param type 获取类型，1：微博、2：评论、3：私信，默认为1。
     * @return Status's id
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
     * @since JDK 1.5
     */
    public JSONObject QueryId(String mid, Integer type, int isBase62) throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
                new HttpParameter[]{new HttpParameter("mid", mid),
                        new HttpParameter("type", type.toString()),
                        new HttpParameter("isBase62", isBase62)}).asJSONObject();
    }

    /**
     * 通过微博MID获取其ID
     *
     * @param mid      true string 需要查询的微博MID，批量模式下，用半角逗号分隔，最多不超过20个
     * @param type     获取类型，1：微博、2：评论、3：私信，默认为1。
     * @param isBatch  是否使用批量模式，0：否、1：是，默认为0。
     * @param isBase62 MID是否是base62编码，0：否、1：是，默认为0。
     * @return Status's id
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
     * @since JDK 1.5
     */
    public JSONObject QueryId(String mid, Integer type, Integer isBatch, Integer isBase62) throws WeiboException {
        return client.get(
                WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
                new HttpParameter[]{new HttpParameter("mid", mid),
                        new HttpParameter("type", type.toString()),
                        new HttpParameter("is_batch", isBatch.toString()),
                        new HttpParameter("isBase62", isBase62.toString())}).asJSONObject();
    }


    /**
     * 按天返回热门微博转发榜的微博列表
     *
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
     * @since JDK 1.5
     */
    public JSONArray getForwardDaily() throws WeiboException {
        return client.get(WeiboConfig
                .getValue("baseURL") + "statuses/hot/repost_daily.json").asJSONArray();
    }

    /**
     * 按周返回热门微博转发榜的微博列表
     *
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
     * @since JDK 1.5
     */
    public JSONArray getForwardWeekly() throws WeiboException {
        return client.get(WeiboConfig
                .getValue("baseURL") + "statuses/hot/repost_weekly.json").asJSONArray();
    }

    /**
     * 按天返回热门微博评论榜的微博列表
     *
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
     * @since JDK 1.5
     */
    public JSONArray getCommentsDaily() throws WeiboException {
        return client.get(WeiboConfig
                .getValue("baseURL") + "statuses/hot/comments_daily.json").asJSONArray();
    }

    /**
     * 按周返回热门微博评论榜的微博列表
     *
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
     * @since JDK 1.5
     */
    public JSONArray getCommentsWeekly() throws WeiboException {
        return client.get(WeiboConfig
                .getValue("baseURL") + "statuses/hot/comments_weekly.json").asJSONArray();
    }

    /**
     * 转发一条新微博
     *
     * @param id 要转发的微博ID
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
     * @since JDK 1.5
     */
    public Status forward(String id) throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL")
                        + "statuses/repost.json",
                new HttpParameter[]{new HttpParameter("id", id)}));
    }

    /**
     * 转发一条微博
     *
     * @param id        要转发的微博ID
     * @param status    添加的转发文本，必须做URLencode，内容不超过140个汉字，不填则默认为“转发微博”
     * @param isComment 是否在转发的同时发表评论，0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
     * @since JDK 1.5
     */
    public Status forward(String id, String status, Integer isComment)
            throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL") + "statuses/repost.json", new HttpParameter[]{
                new HttpParameter("id", id),
                new HttpParameter("status", status),
                new HttpParameter("is_comment", isComment.toString())}));
    }

    /**
     * 根据微博ID删除指定微博
     *
     * @param id 需要删除的微博ID
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/destroy">statuses/destroy</a>
     * @since JDK 1.5
     */
    public Status destroy(String id) throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL")
                        + "statuses/destroy.json",
                new HttpParameter[]{new HttpParameter("id", id)}));
    }

    /**
     * 发布一条新微博
     *
     * @param status 要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
     * @since JDK 1.5
     */
    public Status updateStatus(String status) throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL")
                        + "statuses/update.json",
                new HttpParameter[]{new HttpParameter("status", status)}));
    }

    public List<Count> getStatusCount(String statusIds) throws WeiboException {
        return Count.constructCounts(client.get(WeiboConfig.getValue("baseURL") + "statuses/count.json",
                new HttpParameter[]{new HttpParameter("ids", statusIds)}));
    }

    /**
     * 发布一条新微博
     *
     * @param status      要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
     * @param lat         纬度，有效范围：-90.0到+90.0，+表示北纬，默认为0.0。
     * @param longs       经度，有效范围：-180.0到+180.0，+表示东经，默认为0.0。
     * @param annotations 元数据，主要是为了方便第三方应用记录一些适合于自己使用的信息，每条微博可以包含一个或者多个元数据，
     *                    必须以json字串的形式提交，字串长度不超过512个字符，具体内容可以自定
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
     * @since JDK 1.5
     */
    public Status updateStatus(String status, Float lat, Float longs,
                               String annotations) throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL")
                + "statuses/update.json", new HttpParameter[]{
                new HttpParameter("status", status),
                new HttpParameter("lat", lat.toString()),
                new HttpParameter("long", longs.toString()),
                new HttpParameter("annotations", annotations)}));
    }

    /**
     * 上传图片并发布一条新微博
     *
     * @param status 要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
     * @param item   要上传的图片，仅支持JPEG、GIF、PNG格式，图片大小小于5M。
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
     * @since JDK 1.5
     */
    public Status uploadStatus(String status, ImageItem item)
            throws WeiboException {
        return new Status(client.multiPartURL(
                WeiboConfig.getValue("baseURL") + "statuses/upload.json",
                new HttpParameter[]{new HttpParameter("status", status)},
                item));
    }

    /**
     * 上传图片并发布一条新微博
     *
     * @param status 要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
     * @param item   要上传的图片，仅支持JPEG、GIF、PNG格式，图片大小小于5M。
     * @param lat    纬度，有效范围：-90.0到+90.0，+表示北纬，默认为0.0。
     * @param longs  经度，有效范围：-180.0到+180.0，+表示东经，默认为0.0。
     * @return Status
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a
     * href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
     * @since JDK 1.5
     */
    public Status uploadStatus(String status, ImageItem item, Float lat,
                               Float longs) throws WeiboException {
        return new Status(client.multiPartURL(
                WeiboConfig.getValue("baseURL") + "statuses/upload.json",
                new HttpParameter[]{new HttpParameter("status", status),
                        new HttpParameter("lat", lat.toString()),
                        new HttpParameter("long", longs.toString())}, item));
    }

    /**
     * 获取微博官方表情的详细信息
     *
     * @return Emotion
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
     * @since JDK 1.5
     */
    public List<Emotion> getEmotions() throws WeiboException {
        return Emotion.constructEmotions(client.get(WeiboConfig
                .getValue("baseURL") + "emotions.json"));
    }

    /**
     * 第三方分享一条链接到微博
     *
     * @param status 用户分享到微博的文本内容，必须做URLencode ，内容不超过140个汉字
     * @param rip    用户分享的ip地址，格式为46.152.12.45
     */
    public Status share(String status, String rip)
            throws WeiboException {
        return new Status(client.post(WeiboConfig.getValue("baseURL")
                +  "statuses/share.json", new HttpParameter[]{
                new HttpParameter("status", status),
                new HttpParameter("rip", rip)}));
    }


    /**
     * 获取微博官方表情的详细信息
     *
     * @param type     表情类别，face：普通表情、ani：魔法表情、cartoon：动漫表情，默认为face
     * @param language 语言类别，cnname：简体、twname：繁体，默认为cnname
     * @return Emotion
     * @throws WeiboException when Weibo service or network is unavailable
     * @version weibo4j-V2 1.0.0
     * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
     * @since JDK 1.5
     */
    public List<Emotion> getEmotions(String type, String language)
            throws WeiboException {
        return Emotion.constructEmotions(client.get(
                WeiboConfig.getValue("baseURL") + "emotions.json",
                new HttpParameter[]{
                        new HttpParameter("type", type),
                        new HttpParameter("language", language)}));
    }

}
