/*
 * Copyright 2018 FSB Technology (UK) Ltd ("FSB")
 *
 * The contents of this file are the property of FSB. You may not use the
 * contents of this file without the express permission of FSB.
 */
package org.jasonlind.apple;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Please refer to:
 * https://developer.apple.com/library/mac/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/Chapters/TheNotificationPayload.html#//apple_ref/doc/uid/TP40008194-CH107-SW1
 *
 * Created by michele on 02/03/16.
 */
public class ApplePayloadComplexDTO extends ApplePayloadDTO implements Serializable
{
    private AlertProperty alert;

    public ApplePayloadComplexDTO()
    {

    }
    
    public ApplePayloadComplexDTO(AlertProperty alert, Integer badge, String sound, Integer contentAvailable, String category, Map<String, String> metadataGroups)
    {
        super(badge, sound, contentAvailable, category, metadataGroups);
        this.alert = alert;
    }

    public AlertProperty getAlert()
    {
        return alert;
    }

    public void setAlert(AlertProperty alert)
    {
        this.alert = alert;
    }

    @Override
    public String toString()
    {
        return String.format("AppleNotificationPayloadSimpleDTO{alert=%s, " +
                        "badge=%s, sound=%s, contentAvailable=%s, category=%s}",
                alert, badge, sound, contentAvailable, category);
    }

    public static class AlertProperty implements Serializable
    {
        private String title;
        private String body;
        private String titleLockKey;
        private List<String> titleLocArgs;
        private String actionLocKey;
        private String locKey;
        private List<String> locArgs;
        private String launchImage;

        public AlertProperty()
        {

        }

        public AlertProperty(String title, String body, String locKey, List<String> locArgs, String launchImage)
        {
            this.title = title;
            this.body = body;
            this.locKey = locKey;
            this.locArgs = locArgs;
            this.launchImage = launchImage;
            this.titleLockKey = null;
            this.titleLocArgs = null;
            this.actionLocKey = null;
        }

        public AlertProperty(String title,
                             String body,
                             String titleLockKey,
                             List<String> titleLocArgs,
                             String actionLocKey,
                             String locKey,
                             List<String> locArgs,
                             String launchImage)
        {
            this.title = title;
            this.body = body;
            this.titleLockKey = titleLockKey;
            this.titleLocArgs = titleLocArgs;
            this.actionLocKey = actionLocKey;
            this.locKey = locKey;
            this.locArgs = locArgs;
            this.launchImage = launchImage;
        }

        public String getTitle()
        {
            return title;
        }

        public String getBody()
        {
            return body;
        }

        public String getTitleLockKey()
        {
            return titleLockKey;
        }

        public boolean isTitleLockKeyNull()
        {
            return titleLockKey == null;
        }

        public List<String> getTitleLocArgs()
        {
            return titleLocArgs;
        }

        public boolean isTitleLocArgsNull()
        {
            return titleLocArgs == null;
        }

        public String getActionLocKey()
        {
            return actionLocKey;
        }

        public boolean isActionLocKeyNull()
        {
            return actionLocKey == null;
        }

        public String getLocKey()
        {
            return locKey;
        }

        public List<String> getLocArgs()
        {
            return locArgs;
        }

        public String getLaunchImage()
        {
            return launchImage;
        }

        @Override
        public String toString()
        {
            return String.format("AlertProperty{title=%s, body=%s, titleLockKey=%s, titleLocArgs=%s, " +
                                    "actionLocKey=%s, locKey=%s, locArgs=%s, launchImage=%s}",
                    title, body, titleLockKey, titleLocArgs, actionLocKey, locKey, locArgs, launchImage);
        }
    }

}
