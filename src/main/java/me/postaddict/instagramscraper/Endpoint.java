package me.postaddict.instagramscraper;

public class Endpoint {
    public static final String BASE_URL = "http://localhost:8010";
    public static final String ACCOUNT_PAGE = "http://localhost:8010/{{username}}";
    public static final String MEDIA_LINK = "http://localhost:8010/p/{{code}}";
    public static final String ACCOUNT_MEDIAS = "http://localhost:8010/{{username}}/media?max_id={{maxId}}";
    public static final String ACCOUNT_JSON_INFO = "http://localhost:8010/{{username}}/?__a=1";
    public static final String MEDIA_JSON_INFO = "http://localhost:8010/p/{{code}}/?__a=1";
    public static final String MEDIA_JSON_BY_LOCATION_ID = "http://localhost:8010/explore/locations/{{facebookLocationId}}/?__a=1&max_id={{maxId}}";
    public static final String MEDIA_JSON_BY_TAG = "http://localhost:8010/explore/tags/{{tag}}/?__a=1&max_id={{maxId}}";
    public static final String GENERAL_SEARCH = "http://localhost:8010/web/search/topsearch/?query={{query}}";
    public static final String ACCOUNT_JSON_INFO_BY_ID = "ig_user({{userId}}){id,username,external_url,full_name,profile_pic_url,biography,followed_by{count},follows{count},media{count},is_private,is_verified}";
    public static final String LAST_COMMENTS_BY_CODE = "ig_shortcode({{code}}){comments.last({{count}}){count,nodes{id,created_at,text,user{id,profile_pic_url,username,follows{count},followed_by{count},biography,full_name,media{count},is_private,external_url,is_verified}},page_info}}";
    public static final String COMMENTS_BEFORE_COMMENT_ID_BY_CODE = "ig_shortcode({{code}}){comments.before({{commentId}},{{count}}){count,nodes{id,created_at,text,user{id,profile_pic_url,username,follows{count},followed_by{count},biography,full_name,media{count},is_private,external_url,is_verified}},page_info}}";

    public static final String INSTAGRAM_QUERY_URL = "http://localhost:8010/query/";

    public static String getAccountPageLink(String username) {
        return ACCOUNT_PAGE.replace("{{username}}", username);
    }

    public static String getAccountJsonInfoLinkByUsername(String username) {
        return ACCOUNT_JSON_INFO.replace("{{username}}", username);
    }

    public static String getAccountJsonInfoLinkByAccountId(long userId) {
        return ACCOUNT_JSON_INFO_BY_ID.replace("{{userId}}", "" + userId);
    }

    public static String getAccountMediasJsonLink(String username, String maxId) {
        if (maxId == null) {
            maxId = "";
        }
        return ACCOUNT_MEDIAS.replace("{{username}}", username).replace("{{maxId}}", maxId);
    }

    public static String getMediaPageLinkByCode(String code) {
        return MEDIA_LINK.replace("{{code}}", code);
    }

    public static String getMediaJsonLinkByShortcode(String shortcode) {
        return MEDIA_JSON_INFO.replace("{{code}}", shortcode);
    }

    public static String getMediasJsonByLocationIdLink(String facebookLocationId, String maxId) {
        if (maxId == null) {
            maxId = "";
        }
        return MEDIA_JSON_BY_LOCATION_ID.replace("{{facebookLocationId}}", facebookLocationId).replace("{{maxId}}", maxId);
    }

    public static String getMediasJsonByTagLink(String tag, String maxId) {
        if (maxId == null) {
            maxId = "";
        }
        return MEDIA_JSON_BY_TAG.replace("{{tag}}", tag).replace("{{maxId}}", maxId);
    }

    public static String getGeneralSearchJsonLink(String query) {
        return GENERAL_SEARCH.replace("{{query}}", query);
    }

    public static String getLastCommentsByCodeLink(String code, int count) {
        return LAST_COMMENTS_BY_CODE
                .replace("{{code}}", code)
                .replace("{{count}}", "" + count);
    }

    public static String getCommentsBeforeCommentIdByCode(String code, int count, String commentId) {
        return COMMENTS_BEFORE_COMMENT_ID_BY_CODE
                .replace("{{code}}", code)
                .replace("{{count}}", "" + count)
                .replace("{{commentId}}", commentId);
    }

}
