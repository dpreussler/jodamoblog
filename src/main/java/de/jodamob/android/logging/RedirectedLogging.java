package de.jodamob.android.logging;

class RedirectedLogging implements Logging {

    private final Logging redirectLog;

    public RedirectedLogging(Logging redirectLog) {
        this.redirectLog = redirectLog;
    }

    @Override
    public  int v(String tag, String message) {
        return redirectLog.v(tag, asNonNull(message));
    }

    @Override
    public  int v(String tag, String message, Throwable tr) {
        return redirectLog.v(tag, asNonNull(message), tr);
    }

    @Override
    public  int d(String tag, String message) {
        return redirectLog.d(tag, asNonNull(message));
    }

    @Override
    public  int d(String tag, String message, Throwable tr) {
        return redirectLog.d(tag, asNonNull(message), tr);
    }

    @Override
    public  int i(String tag, String message) {
        return redirectLog.i(tag, asNonNull(message));
    }

    @Override
    public  int i(String tag, String message, Throwable tr) {
        return redirectLog.i(tag, asNonNull(message), tr);
    }

    @Override
    public  int w(String tag, String message) {
        return redirectLog.w(tag, asNonNull(message));
    }

    @Override
    public  int w(String tag, String message, Throwable tr) {
        return redirectLog.w(tag, asNonNull(message), tr);
    }

    @Override
    public  int w(String tag, Throwable tr) {
        return redirectLog.w(tag, tr);
    }

    @Override
    public  int e(String tag, String message) {
        return redirectLog.e(tag, asNonNull(message));
    }

    @Override
    public  int e(String tag, String message, Throwable tr) {
        return redirectLog.e(tag, asNonNull(message), tr);
    }

    @Override
    public  int e(String message) {
        return redirectLog.e(asNonNull(message));
    }

    @Override
    public  int e(String message, Throwable tr) {
        return redirectLog.e(asNonNull(message), tr);
    }

    @Override
    public  int wtf(String tag, String message, Throwable tr) {
        return redirectLog.wtf(tag, asNonNull(message), tr);
    }

    @Override
    public  int wtf(String tag, Throwable tr) {
        return redirectLog.wtf(tag, tr);
    }

    @Override
    public  int wtf(String tag, String message) {
        return redirectLog.wtf(tag, asNonNull(message));
    }

    @Override
    public  int wtf(Throwable tr) {
        return redirectLog.wtf(tr);
    }

    @Override
    public int logStackTrace(String message) {
        return redirectLog.logStackTrace(asNonNull(message));
    }

    @Override
    public  String getStackTraceString(Throwable tr) {
        return redirectLog.getStackTraceString(tr);
    }

    @Override
    public int e(Throwable tr) {
        return redirectLog.e(tr);
    }

    private String asNonNull(String message) {
        return message == null ? "" : message;
    }
}
