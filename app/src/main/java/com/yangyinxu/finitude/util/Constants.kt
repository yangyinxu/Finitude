package com.yangyinxu.finitude.util

object Constants {

    // domain
    const val BASE_ARCHTREE_URL = "http://archtree-env.eba-cqf4qjeg.us-east-1.elasticbeanstalk.com"

    // routes
    const val ROUTE_HOME = "home"
    const val ROUTE_CHAT = "chat"
    const val ROUTE_PLAYER = "player"
    const val ROUTE_SETTINGS = "settings"
    const val ROUTE_LOGIN = "login"
    const val ROUTE_SIGN_UP = "sign_up"

    const val ROUTE_POST_DETAILS = "postDetails/{postId}"

    // nav arguments
    const val POST_DETAILS_ARGUMENT_KEY = "postId"

    // authentication
    const val MIN_PASSWORD_LENGTH = 8;

    // player
    const val PLAYER_CONTENT_URI_SCHEME = "content"
    const val PLAYER_VIDEO_URIS_KEY = "videoUris"
    const val PLAYER_SUBSCRIPTION_TIMEOUT = 5000L
    const val PLAYER_INPUT_STRING = "audio/*"
}