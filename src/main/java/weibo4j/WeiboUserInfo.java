package weibo4j;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class WeiboUserInfo {
    private String uuid;
    private String username;
    private String nickname;
    private String avatar;
    private String blog;
    private String location;
    private String remark;
    private String gender;
    private String source;
    private Token token;
    @JsonProperty("rawUserInfo")
    private RawUserInfo rawUserInfo;

    @Data
    public static class Token {
        @JsonProperty("accessToken")
        private String accessToken;
        @JsonProperty("expireIn")
        private long expireIn;
        @JsonProperty("refreshTokenExpireIn")
        private int refreshTokenExpireIn;
        private String uid;
        private String openId;
    }

    @Data
    public static class RawUserInfo {
        @JsonProperty("is_teenager")
        private int isTeenager;
        @JsonProperty("favourites_count")
        private int favouritesCount;
        @JsonProperty("vplus_ability")
        private int vplusAbility;
        private String province;
        @JsonProperty("screen_name")
        private String screenName;
        @JsonProperty("interaction_user")
        private int interactionUser;
        private int block;
        private long id;
        @JsonProperty("geo_enabled")
        private boolean geoEnabled;
        @JsonProperty("like_me")
        private boolean likeMe;
        @JsonProperty("light_ring")
        private boolean lightRing;
        @JsonProperty("wenda_ability")
        private int wendaAbility;
        @JsonProperty("video_total_counter")
        private Map<String, Integer> videoTotalCounter;
        @JsonProperty("nft_ability")
        private int nftAbility;
        @JsonProperty("show_auth")
        private int showAuth;
        @JsonProperty("ecommerce_ability")
        private int ecommerceAbility;
        private String domain;
        private boolean following;
        @JsonProperty("audio_ability")
        private int audioAbility;
        @JsonProperty("follow_me")
        private boolean followMe;
        @JsonProperty("friends_count")
        private int friendsCount;
        @JsonProperty("credit_score")
        private int creditScore;
        @JsonProperty("user_ability_extend")
        private int userAbilityExtend;
        private String gender;
        private String city;
        @JsonProperty("profile_url")
        private String profileUrl;
        @JsonProperty("brand_ability")
        private int brandAbility;
        @JsonProperty("super_topic_not_syn_count")
        private int superTopicNotSynCount;
        @JsonProperty("unfollowing_recom_switch")
        private int unfollowingRecomSwitch;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("wbcolumn_ability")
        private int wbcolumnAbility;
        @JsonProperty("live_ability")
        private int liveAbility;
        @JsonProperty("bi_followers_count")
        private int biFollowersCount;
        @JsonProperty("is_teenager_list")
        private int isTeenagerList;
        @JsonProperty("verified_reason")
        private String verifiedReason;
        @JsonProperty("video_status_count")
        private int videoStatusCount;
        @JsonProperty("newbrand_ability")
        private int newbrandAbility;
        private int urisk;
        private int star;
        @JsonProperty("status_total_counter")
        private Map<String, Integer> statusTotalCounter;
        @JsonProperty("online_status")
        private int onlineStatus;
        @JsonProperty("block_app")
        private int blockApp;
        private String url;
        @JsonProperty("avatar_large")
        private String avatarLarge;
        @JsonProperty("planet_video")
        private int planetVideo;
        @JsonProperty("gongyi_ability")
        private int gongyiAbility;
        @JsonProperty("hardfan_ability")
        private int hardfanAbility;
        @JsonProperty("statuses_count")
        private int statusesCount;
        private Insecurity insecurity;
        @JsonProperty("verified_source")
        private String verifiedSource;
        @JsonProperty("allow_all_act_msg")
        private boolean allowAllActMsg;
        private int urank;
        @JsonProperty("verified_trade")
        private String verifiedTrade;
        @JsonProperty("is_auth")
        private int isAuth;
        private String weihao;
        @JsonProperty("green_mode")
        private int greenMode;
        @JsonProperty("mb_expire_time")
        private int mbExpireTime;
        @JsonProperty("verified_source_url")
        private String verifiedSourceUrl;
        @JsonProperty("comment_display")
        private int commentDisplay;
        @JsonProperty("auth_status")
        private int authStatus;
        @JsonProperty("video_mark")
        private int videoMark;
        @JsonProperty("live_status")
        private int liveStatus;
        @JsonProperty("special_follow")
        private boolean specialFollow;
        @JsonProperty("followers_count_str")
        private String followersCountStr;
        @JsonProperty("chaohua_ability")
        private int chaohuaAbility;
        private boolean like;
        @JsonProperty("place_ability")
        private int placeAbility;
        @JsonProperty("verified_type")
        private int verifiedType;
        @JsonProperty("pagefriends_count")
        private int pagefriendsCount;
        private String name;
        @JsonProperty("cover_image_phone")
        private String coverImagePhone;
        @JsonProperty("like_display")
        private int likeDisplay;
        private String idstr;
        @JsonProperty("is_big")
        private int isBig;
        private String description;
        private String remark;
        private int ptype;
        @JsonProperty("verified_reason_url")
        private String verifiedReasonUrl;
        @JsonProperty("block_word")
        private int blockWord;
        @JsonProperty("avatar_type")
        private int avatarType;
        private int vvip;
        @JsonProperty("avatar_hd")
        private String avatarHd;
        private int hongbaofei;
        @JsonProperty("video_play_count")
        private int videoPlayCount;
        private int mbtype;
        @JsonProperty("user_ability")
        private int userAbility;
        @JsonProperty("story_read_state")
        private int storyReadState;
        private int mbrank;
        private String lang;
        private int clazz;
        @JsonProperty("is_punish")
        private int isPunish;
        @JsonProperty("allow_all_comment")
        private boolean allowAllComment;
        private boolean verified;
        @JsonProperty("block_me")
        private int blockMe;
        @JsonProperty("profile_image_url")
        private String profileImageUrl;
        @JsonProperty("pc_new")
        private int pcNew;
        @JsonProperty("paycolumn_ability")
        private int paycolumnAbility;
        @JsonProperty("brand_account")
        private int brandAccount;
        @JsonProperty("vclub_member")
        private int vclubMember;
        @JsonProperty("followers_count")
        private int followersCount;
        @JsonProperty("is_guardian")
        private int isGuardian;
        private String location;
        private int svip;

        @Data
        public static class Insecurity {
            @JsonProperty("sexual_content")
            private boolean sexualContent;
        }
    }
}