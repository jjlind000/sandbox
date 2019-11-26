/*
 * Copyright 2018 FSB Technology (UK) Ltd ("FSB")
 *
 * The contents of this file are the property of FSB. You may not use the
 * contents of this file without the express permission of FSB.
 */
package org.jasonlind.apple;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Please refer to:
 * https://developer.apple.com/library/mac/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/Chapters/TheNotificationPayload.html#//apple_ref/doc/uid/TP40008194-CH107-SW1
 *
 * Created by michele on 02/03/16.
 */
public abstract class ApplePayloadDTO implements Serializable
{
    protected Integer badge;
    protected String sound;
    @SerializedName("content-available")
    protected Integer contentAvailable;
    protected String category;
    @SerializedName("thread-id")
    protected String threadId;
    protected Map<String,String> metadataGroups;

    public ApplePayloadDTO()
    {

    }

    public ApplePayloadDTO(Integer badge, String sound, Integer contentAvailable, String category, Map<String,String> metadataGroups)
    {
        this.badge = badge;
        this.sound = sound;
        this.contentAvailable = contentAvailable;
        this.category = category;
        this.metadataGroups = metadataGroups;
    }

    public Integer getBadge()
    {
        return badge;
    }

    public String getSound()
    {
        return sound;
    }

    public Integer getContentAvailable()
    {
        return contentAvailable;
    }

    public String getCategory()
    {
        return category;
    }

    public void setBadge(Integer badge)
    {
        this.badge = badge;
    }

    public void setSound(String sound)
    {
        this.sound = sound;
    }

    public void setContentAvailable(Integer contentAvailable)
    {
        this.contentAvailable = contentAvailable;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getThreadId()
    {
        return threadId;
    }

    public void setThreadId(String threadId)
    {
        this.threadId = threadId;
    }

    public Map<String, String> getMetadataGroups()
    {
        return metadataGroups;
    }

    public void setMetadataGroups(Map<String, String> metadataGroups)
    {
        this.metadataGroups = metadataGroups;
    }

    @Override
    public String toString()
    {

        return MoreObjects.toStringHelper(this.getClass())
                .add("badge", badge)
                .add("sound", sound)
                .add("contentAvailable", contentAvailable)
                .add("category", category)
                .add("threadId", threadId)
                .add("metadataGroups", metadataGroups.entrySet().stream()
                                                           .map(entry->String.format("[%s:%s]",entry.getValue(),entry.getValue()))
                                                           .collect(Collectors.joining(",")))
                .toString();
    }
}
