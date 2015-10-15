package com.example.android.apis.justjava;

public class HashVsListTestDB {
    public int id;

    public String messageId;

    public Integer groupId;

    public String postTimestamp;

    public int type;

    public int status;

    public String message;

    public int senderId;

    public boolean isReported;

    public boolean reportLabelDisplay;

    public String reportExpiration;

    public int readCount;


    public String contentMainInfo;

    public String contentSubInfo;

    public boolean selected = false;

    public int viewType = -1;

    public int progressBar = 0;

    public long sentTimestamp = -1;

    public MyMessageType getType() {
        return MyMessageType.toEnum(type);
    }

    public MessageStatus getStatus() {
        return MessageStatus.toEnum(status);
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.contentMainInfo = thumbnailImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.contentSubInfo = imageUrl;
    }

    public void setStampId(String stampId) {
        this.contentMainInfo = stampId;
    }

    public void setStampImageUrl(String stampImageUrl) {
        this.contentSubInfo = stampImageUrl;
    }

    public void setEstampId(String estampId) {
        this.contentMainInfo = estampId;
    }

    public void setOmikujiId(String omikujiId) {
        this.contentMainInfo = omikujiId;
    }

    public void setCommunityId(String communityId) {
        this.contentMainInfo = communityId;
    }
}
