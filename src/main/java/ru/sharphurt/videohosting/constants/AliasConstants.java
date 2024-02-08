package ru.sharphurt.videohosting.constants;

public class AliasConstants {

    public static final String LOG_REQUEST_RECEIVED = "Request received: %s | ";
    public static final String LOG_VIDEO_UPLOAD_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("video uploading") + "Filename: %s";
    public static final String LOG_VIDEO_RESIZE_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("video resizing") + "Id: %s";

    public static final String LOG_REQUEST_PROCESSED = "Request processed: %s";
    public static final String LOG_VIDEO_UPLOAD_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("video uploading");
    public static final String LOG_VIDEO_RESIZE_REQUEST_PROCESSED = LOG_REQUEST_RECEIVED.formatted("video resizing") + "Id: %s";
    public static final String LOG_VIDEO_RESIZE_FINISH_JOB = LOG_REQUEST_RECEIVED.formatted("video resizing") + "ffmpeg job finished. Id: %s";
    public static final String LOG_VIDEO_RESIZE_START_JOB = LOG_REQUEST_RECEIVED.formatted("video resizing") + "ffmpeg job started. Id: %s";
    public static final String LOG_INFORMATION_SAVED = LOG_REQUEST_RECEIVED.formatted("saving to DB") + "object: %s, value: %s";

    public static final String EXCEPTION_MESSAGE_BASE = "Exception during request execution: %s | ";
    public static final String EXCEPTION_CORRUPTED_FILE = EXCEPTION_MESSAGE_BASE + "File { %s } corrupted or empty";
    public static final String EXCEPTION_UNACCEPTABLE_TYPE = EXCEPTION_MESSAGE_BASE + "File { %s } has unaccaptable extension";
    public static final String EXCEPTION_FILE_NOT_SAVED = EXCEPTION_MESSAGE_BASE + "File { %s } was not saved to disk";
    public static final String EXCEPTION_VIDEO_NOT_FOUND = EXCEPTION_MESSAGE_BASE + "Video { %s } not found";
    public static final String EXCEPTION_VIDEO_PROCESSING = EXCEPTION_MESSAGE_BASE + "Video { %s } has not been processed";

}
