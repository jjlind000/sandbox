/*
 * Copyright 2018 FSB Technology (UK) Ltd ("FSB")
 *
 * The contents of this file are the property of FSB. You may not use the
 * contents of this file without the express permission of FSB.
 */
package org.jasonlind.apple;

import java.io.Serializable;
import java.util.Map;

/**
 * Please refer to:
 * https://developer.apple.com/library/mac/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/Chapters/TheNotificationPayload.html#//apple_ref/doc/uid/TP40008194-CH107-SW1
 *
 * Created by michele on 02/03/16.
 */
public class ApplePayloadSimpleDTO extends ApplePayloadDTO implements Serializable
{
    private String alert;

    public ApplePayloadSimpleDTO()
    {

    }

    public ApplePayloadSimpleDTO(String alert, Integer badge, String sound, Integer contentAvailable, String category, Map<String, String> metadataGroups)
    {
        super(badge, sound, contentAvailable, category, metadataGroups);
        this.alert = alert;
    }

    public String getAlert()
    {
        return alert;
    }

    public void setAlert(String alert)
    {
        this.alert = alert;
    }

    @Override
    public String toString()
    {
        return String.format("AppleNotificationPayloadSimpleDTO{alert=%s, " +
                                "badge=%s, sound=%s, contentAvailable=%s, category=%s, metadataGroups=%s}",
                alert, badge, sound, contentAvailable, category, metadataGroups);
    }
}
