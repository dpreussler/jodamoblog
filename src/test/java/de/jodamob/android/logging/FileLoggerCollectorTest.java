package de.jodamob.android.logging;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class FileLoggerCollectorTest {

    FileLoggerCollector tested = new FileLoggerCollector(mock(Context.class));

    @Test
    public void test() {
        File dir = mock(File.class);
        File file1 = mock(File.class);
        when(file1.lastModified()).thenReturn(100L);
        File file2 = mock(File.class);
        when(file2.lastModified()).thenReturn(200L);
        File file3 = mock(File.class);
        when(file3.lastModified()).thenReturn(300L);

        when(dir.listFiles(any(FileFilter.class))).thenReturn(new File[] { file2, file3, file1});
        File[] files = tested.getFiles(dir);
        assertEquals(files[0], file1);
        assertEquals(files[1], file2);
        assertEquals(files[2], file3);
    }
}