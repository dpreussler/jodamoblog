package de.jodamob.android.logging;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RedirectedLoggingTest {

    Logging logging = mock(Logging.class);
    RedirectedLogging tested = new RedirectedLogging(logging);

    @Test
    public void should_handle_null() {
        tested.i("", null);
        verify(logging).i("", "");
    }
}