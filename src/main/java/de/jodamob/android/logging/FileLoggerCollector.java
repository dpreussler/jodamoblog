package de.jodamob.android.logging;

import android.content.Context;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import de.jodamob.android.utils.Closeables;
import de.jodamob.android.utils.StreamUtils;

class FileLoggerCollector {
    
    private static final String ACCUMMULATED_LOG = "logscollected.txt";
    private final Context context;

    FileLoggerCollector(Context context) {
        this.context = context;
    }
    
    final File getAsSingleLogfile() {
        return getAsSingleLogfile(getLogDir());
    }

    final File[] getFiles(File dir) {
        File[] files = dir.listFiles(new LogFileFilter());
        Arrays.sort(files, new FileComparator());
        return files;
    }

    private final File getAsSingleLogfile(File logDir) {
        File newFile = new File(logDir, ACCUMMULATED_LOG);
        getAsSingleLogfile(logDir, newFile);
        return newFile;
    }

    private final void getAsSingleLogfile(File logDir, File newFile) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(newFile);
            writeFilesToStream(logDir, out);
        } catch (IOException e) {
            e.printStackTrace(); 
        } finally {
            Closeables.closeQuietly(out);
        }
    }

    private void writeFilesToStream(File dir, OutputStream out) throws IOException {
        writeFileToStream(getFiles(dir), out);
    }

    private void writeFileToStream(File[] files, OutputStream out) throws IOException {
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.length() == 0) {
                    continue;
                }
                copyInto(out, file);
            }
        }
    }

    private File getLogDir() {
        return new File(context.getCacheDir(), FileLogger.FOLDER_NAME);
    }

    private void copyInto(OutputStream out, File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        try {
            StreamUtils.copy(in, out);
        } finally {
            Closeables.closeQuietly(in);
        }
    }

    private static final class LogFileFilter implements FileFilter {
        @Override
        public boolean accept(File path) {
            if (path.isDirectory()) {
                return false;
            }
            String name = path.getName();
            return name.startsWith(FileLogger.FILE_PATTERN) && name.endsWith(FileLogger.FILE_EXTENSION);
        }
    }

    private static class FileComparator implements java.util.Comparator<File> {
        @Override
        public int compare(File file1, File file2) {
            long result = file1.lastModified() - file2.lastModified();
            return result < 0 ? -1 : result > 0 ? 1 : 0;
        }
    }
}
