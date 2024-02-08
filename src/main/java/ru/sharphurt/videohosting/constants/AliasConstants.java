package ru.sharphurt.videohosting.constants;

public class AliasConstants {

    public static final String LOG_REQUEST_RECEIVED = "Request received: %s | ";
    public static final String LOG_UPLOAD_VIDEO_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("video uploading") + "Filename: %s";
    public static final String LOG_RESIZE_VIDEO_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("video resizing") + "Id: %s";
    public static final String LOG_GET_INFORMATION_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("get info") + "Id: %s";
    public static final String LOG_DELETE_VIDEO_REQUEST_RECEIVED = LOG_REQUEST_RECEIVED.formatted("delete video") + "Id: %s";

    public static final String LOG_REQUEST_PROCESSED = "Request processed: %s | ";
    public static final String LOG_UPLOAD_VIDEO_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("video uploading") + "Id: %s";
    public static final String LOG_RESIZE_VIDEO_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("video resizing") + "Id: %s";
    public static final String LOG_GET_INFORMATION_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("get info") + "Id: %s";
    public static final String LOG_DELETE_VIDEO_REQUEST_PROCESSED = LOG_REQUEST_PROCESSED.formatted("delete video") + "Id: %s";

    public static final String LOG_VIDEO_RESIZE_FINISH_JOB = LOG_REQUEST_RECEIVED.formatted("video resizing") + "ffmpeg job finished. Id: %s";
    public static final String LOG_VIDEO_RESIZE_START_JOB = LOG_REQUEST_RECEIVED.formatted("video resizing") + "ffmpeg job started. Id: %s";
    public static final String LOG_INFORMATION_SAVED = LOG_REQUEST_RECEIVED.formatted("saved to DB") + "Object: %s";
    public static final String LOG_FILE_SAVING = LOG_REQUEST_RECEIVED.formatted("start saving file on disk") + "Path: %s";
    public static final String LOG_FILE_SAVED = LOG_REQUEST_RECEIVED.formatted("file saved on disk") + "UUID: %s";
    public static final String LOG_INFORMATION_GOT = LOG_REQUEST_RECEIVED.formatted("got from DB") + "Object: %s";
    public static final String LOG_INFORMATION_DELETED = LOG_REQUEST_RECEIVED.formatted("deleted from DB") + "Object: %s";
    public static final String LOG_FILE_DELETE = LOG_REQUEST_RECEIVED.formatted("start deleting file from disk") + "Path: %s";
    public static final String LOG_FILE_DELETED = LOG_REQUEST_RECEIVED.formatted("file deleted from disk") + "UUID: %s";

    public static final String EXCEPTION_MESSAGE_BASE = "Exception during request execution: %s | ";
    public static final String EXCEPTION_CORRUPTED_FILE = EXCEPTION_MESSAGE_BASE + "File { %s } corrupted or empty";
    public static final String EXCEPTION_UNACCEPTABLE_TYPE = EXCEPTION_MESSAGE_BASE + "File { %s } has unaccaptable extension";
    public static final String EXCEPTION_FILE_NOT_SAVED = EXCEPTION_MESSAGE_BASE + "File { %s } was not saved to disk";
    public static final String EXCEPTION_FILE_NOT_FOUND = EXCEPTION_MESSAGE_BASE + "File { %s } was not found on disk";
    public static final String EXCEPTION_FILE_NOT_DELETED = EXCEPTION_MESSAGE_BASE + "File { %s } was not deleted on disk";
    public static final String EXCEPTION_VIDEO_NOT_FOUND = EXCEPTION_MESSAGE_BASE + "Video { %s } not found";
    public static final String EXCEPTION_VIDEO_PROCESSING = EXCEPTION_MESSAGE_BASE + "Video { %s } has not been processed";

}
