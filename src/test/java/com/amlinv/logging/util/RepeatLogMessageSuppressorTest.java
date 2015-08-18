package com.amlinv.logging.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.slf4j.Logger;

import static org.junit.Assert.*;

/**
 * Created by art on 8/18/15.
 */
public class RepeatLogMessageSuppressorTest {

    private RepeatLogMessageSuppressor suppressor;

    private Logger mockLogger;

    @Before
    public void setupTest() throws Exception {
        this.suppressor = new RepeatLogMessageSuppressor();

        this.mockLogger = Mockito.mock(Logger.class);
    }

    @Test
    public void testGetSetMinDelay() throws Exception {
        assertEquals(RepeatLogMessageSuppressor.DEFAULT_MIN_DELAY, this.suppressor.getMinDelay());

        this.suppressor.setMinDelay(1313);

        assertEquals(1313, this.suppressor.getMinDelay());
    }

    @Test
    public void testDebug() throws Exception {
        this.suppressor.debug(this.mockLogger, "x-message-x", "x-arg1-x");

        Mockito.verify(this.mockLogger).debug("x-message-x", this.makeArgs("x-arg1-x"));
    }

    @Test
    public void testDebugSuppression() throws Exception {
        this.suppressor.setMinDelay(10);

        this.suppressor.debug(this.mockLogger, "x-message1-x", "x-arg1-x");
        this.suppressor.debug(this.mockLogger, "x-message2-x", "x-arg2-x");
        Thread.sleep(11);
        this.suppressor.debug(this.mockLogger, "x-message3-x", "x-arg3-x");

        InOrder ordered = Mockito.inOrder(this.mockLogger);

        // Verify 2 was suppressed while 1 and 3 were not.
        ordered.verify(this.mockLogger).debug("x-message1-x", this.makeArgs("x-arg1-x"));
        ordered.verify(this.mockLogger).debug("x-message3-x", this.makeArgs("x-arg3-x"));
    }

    @Test
    public void testInfo() throws Exception {
        this.suppressor.info(this.mockLogger, "x-message-x", "x-arg1-x");

        Mockito.verify(this.mockLogger).info("x-message-x", this.makeArgs("x-arg1-x"));
    }

    @Test
    public void testInfoSuppression() throws Exception {
        this.suppressor.setMinDelay(10);

        this.suppressor.info(this.mockLogger, "x-message1-x", "x-arg1-x");
        this.suppressor.info(this.mockLogger, "x-message2-x", "x-arg2-x");
        Thread.sleep(11);
        this.suppressor.info(this.mockLogger, "x-message3-x", "x-arg3-x");

        InOrder ordered = Mockito.inOrder(this.mockLogger);

        // Verify 2 was suppressed while 1 and 3 were not.
        ordered.verify(this.mockLogger).info("x-message1-x", this.makeArgs("x-arg1-x"));
        ordered.verify(this.mockLogger).info("x-message3-x", this.makeArgs("x-arg3-x"));
    }

    @Test
    public void testWarn() throws Exception {
        this.suppressor.warn(this.mockLogger, "x-message-x", "x-arg1-x");

        Mockito.verify(this.mockLogger).warn("x-message-x", this.makeArgs("x-arg1-x"));
    }

    @Test
    public void testWarnSuppression() throws Exception {
        this.suppressor.setMinDelay(10);

        this.suppressor.warn(this.mockLogger, "x-message1-x", "x-arg1-x");
        this.suppressor.warn(this.mockLogger, "x-message2-x", "x-arg2-x");
        Thread.sleep(11);
        this.suppressor.warn(this.mockLogger, "x-message3-x", "x-arg3-x");

        InOrder ordered = Mockito.inOrder(this.mockLogger);

        // Verify 2 was suppressed while 1 and 3 were not.
        ordered.verify(this.mockLogger).warn("x-message1-x", this.makeArgs("x-arg1-x"));
        ordered.verify(this.mockLogger).warn("x-message3-x", this.makeArgs("x-arg3-x"));
    }

    @Test
    public void testError() throws Exception {
        this.suppressor.error(this.mockLogger, "x-message-x", "x-arg1-x");

        Mockito.verify(this.mockLogger).error("x-message-x", this.makeArgs("x-arg1-x"));
    }

    @Test
    public void testErrorSuppression() throws Exception {
        this.suppressor.setMinDelay(10);

        this.suppressor.error(this.mockLogger, "x-message1-x", "x-arg1-x");
        this.suppressor.error(this.mockLogger, "x-message2-x", "x-arg2-x");
        Thread.sleep(11);
        this.suppressor.error(this.mockLogger, "x-message3-x", "x-arg3-x");

        InOrder ordered = Mockito.inOrder(this.mockLogger);

        // Verify 2 was suppressed while 1 and 3 were not.
        ordered.verify(this.mockLogger).error("x-message1-x", this.makeArgs("x-arg1-x"));
        ordered.verify(this.mockLogger).error("x-message3-x", this.makeArgs("x-arg3-x"));
    }

    protected Object[] makeArgs(Object... args) {
        return args;
    }
}