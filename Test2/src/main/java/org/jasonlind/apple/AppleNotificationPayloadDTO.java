/*
 * Copyright 2018 FSB Technology (UK) Ltd ("FSB")
 *
 * The contents of this file are the property of FSB. You may not use the
 * contents of this file without the express permission of FSB.
 */
package org.jasonlind.apple;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by michele on 08/03/16.
 */
public class AppleNotificationPayloadDTO implements Serializable
{
    private ApplePayloadDTO aps;
    private Map<String, String> fsb;

    public AppleNotificationPayloadDTO()
    {

    }

    public AppleNotificationPayloadDTO(ApplePayloadDTO aps, Map<String, String> fsb)
    {
        this.aps = aps;
        this.fsb = fsb;
    }

    public static AppleNotificationPayloadDTO buildSimpleNotificationPayload(String alert,
                                                                             Integer badge,
                                                                             String sound,
                                                                             Integer contentAvailable,
                                                                             String category,
                                                                             Map<String, String> fsb
            ,Map<String, String> metadataGroups)
    {
        final ApplePayloadDTO aps = new ApplePayloadSimpleDTO(alert, badge, sound, contentAvailable, category, metadataGroups);
        return new AppleNotificationPayloadDTO(aps, fsb);
    }

    public static AppleNotificationPayloadDTO buildComplexNotificationPayload(String title,
                                                                              String body,
                                                                              String titleLockKey,
                                                                              List<String> titleLocArgs,
                                                                              String actionLocKey,
                                                                              String locKey,
                                                                              List<String> locArgs,
                                                                              String launchImage,
                                                                              Integer badge,
                                                                              String sound,
                                                                              Integer contentAvailable,
                                                                              String category,
                                                                              Map<String, String> fsb,
                                                                              Map<String, String> metadataGroups)
    {
        ApplePayloadComplexDTO.AlertProperty alertProperty =
                new ApplePayloadComplexDTO.AlertProperty(title, body, titleLockKey, titleLocArgs, actionLocKey, locKey, locArgs, launchImage);

        final ApplePayloadDTO aps = new ApplePayloadComplexDTO(alertProperty, badge, sound, contentAvailable, category, metadataGroups);
        return new AppleNotificationPayloadDTO(aps, fsb);
    }

    public ApplePayloadDTO getAps()
    {
        return aps;
    }

    public void setAps(ApplePayloadDTO aps)
    {
        this.aps = aps;
    }

    public Map<String, String> getFsb()
    {
        return fsb;
    }

    public void setFsb(Map<String, String> fsb)
    {
        this.fsb = fsb;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this.getClass())
                .add("aps", aps)
                .add("fsb", fsb)
                .toString();
    }
}

