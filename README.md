[![Build Status](https://travis-ci.org/dpreussler/jodamoblog.svg?branch=master)](https://travis-ci.org/dpreussler/jodamoblog)

jodamoblog
==========
Just another logging framework


multiple combinable logging classes.
Combine local logging with rolling file logging with Crashlytcs with NewRelic...



Gradle
======

```groovy
compile 'de.jodamob.android:jodamoblog:1.2.4'
 
```


Usage
======

LogCatLogger : writes to android.util.log

FileLogger : writes rotating log files with java.util.logging (get content written via FileLogger.getLogFileContent() if needed)

SilentLogger: does not log (implements same interface as above, can be used to disable logging)

AsyncLogger : moves all logging into background thread (might be useful in combination with FileLogger)

UncaughtExceptionLogger: registers as exception handler in system and reports those exceptions to logfile

CrashlyticsLogger: sends exceptions that where logged to crashlytics as caught crash)

NewRelicLogger: sends exceptions that where logged to NewRelic as Network errors (there is no better way for now in API)


Log : has the same "interface" as android.util.Log so that only the package has to be changed with one additional initializer method:
Log.initLogger() excepts any of the loggers above


example:

`Log.initLogger(new LogCatLogger())` writes to console (default)

`Log.initLogger(new FileLogger(context, new LogCatLogger()))` writes to files + console

`Log.initLogger(new UncaughtExceptionLogger(new FileLogger(context)))` writes logs and uncaught crashes to file

`Log.initLogger(new AsyncLogger(new FileLogger(new CrashlyticsLogger(new LogCatLogger()))));` writes async to file and crashlytics and logcat

`Log.initLogger(new SilentLogger())` disables logging

Or simply create the UberLog:

```java
Log.initLogger(
            new NewRelicLogger(
                    new CrashlyticsLogger(
                            new LogCatLogger(
                                    new UncaughtExceptionLogger(
                                            new AsyncLogger(
                                                    new FileLogger(context)))))));
```


Small helpers
====================================

check out helper classes

```java
StreamUtils.copy(inputstream, outputstream)

Closeables.closeQuietly(stream1, stream2,...)
```

Licensed under MIT license
(c) 2014 Danny Preussler