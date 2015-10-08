package de.jodamob.android.logging;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileLoggerPreparation {
    
    private final Context context;

    FileLoggerPreparation(Context context) {
        this.context = context;
    }
    
    final void prepare(Logger logger) {
        prepare(logger, getLogDir(), FileLogger.FILE_PATTERN, FileLogger.FILE_EXTENSION);
    }

    private File getLogDir() {
        return new File(context.getCacheDir(), FileLogger.FOLDER_NAME);
    }

    private void prepare(Logger logger, File logFolder, String logpattern, String logExtention) {
        removeExistingHandlers(logger);
        enableFileLogger(logger, logFolder, logpattern, logExtention);
    }

    private void enableFileLogger(Logger logger, File logFolder, String logpattern, String logExtention) {
        try {
            setLoggerToFile(logger, logFolder, logpattern, logExtention);    
        } catch(IOException e) {
            System.err.println(e.toString());
        }
    }

    private void removeExistingHandlers(Logger logger) {
        for (final Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
    }

    private void setLoggerToFile(Logger logger, File logFolder, String logpattern, String logExtention) throws IOException {
        createLogFolder(logFolder);
        createLogHandler(logger, logFolder, logpattern, logExtention);    
    }

    private void createLogHandler(Logger logger, File logFolder, String logpattern, String logExtention) throws IOException {
        logger.addHandler(new SimpleFileHandler(logFolder, logpattern, logExtention));
        logger.setLevel(Level.INFO);
    }

    private void createLogFolder(File logFolder) {
        logFolder.mkdirs();
    }
}
