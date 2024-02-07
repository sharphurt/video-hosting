package ru.sharphurt.videohosting.constants;

public class AliasConstants {

    public static final String LOG_REQUEST_RECEIVED = "Request received: %s | ";
    public static final String LOG_VIDEO_UPLOAD_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("video uploading") + "Filename: %s";

    public static final String LOG_REQUEST_PROCESSED = "Request processed: %s";
    public static final String LOG_VIDEO_UPLOAD_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("video uploading");

}
