package com.mh.forum.mailNotifyer;

public class MessageFactory {

    public static String welcomeMessage() {
        return "Thank you for subscribing, were glad to have you in ou community";
    }

    public static String passwordChangeMessage() {
        return "Your password have just been successfuly updated";
    }

    public static String accountUpdatedMessage() {
        return "Your personnal informations has been updated successfuly";
    }

    public static String ideaCreatedMessage() {
        return "Your idea has been successfuly published";
    }
}
