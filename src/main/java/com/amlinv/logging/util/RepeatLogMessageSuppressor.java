/*
 *   Copyright 2015 AML Innovation & Consulting LLC
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package com.amlinv.logging.util;

import org.slf4j.Logger;

import java.util.Map;

/**
 * Created by art on 4/5/15.
 */
public class RepeatLogMessageSuppressor {
    public static final long DEFAULT_MIN_DELAY = 300000L;

    private long lastLoggedTime = 0;
    private long minDelay = DEFAULT_MIN_DELAY;

    public long getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(long minDelay) {
        this.minDelay = minDelay;
    }

    public void debug (Logger destLogger, String message, Object... args) {
        long nowMs = System.nanoTime() / 1000000L;
        long elapsed = nowMs - lastLoggedTime;

        if ( elapsed > minDelay ) {
            destLogger.debug(message, args);
            lastLoggedTime = nowMs;
        }
    }

    public void info (Logger destLogger, String message, Object... args) {
        long nowMs = System.nanoTime() / 1000000L;
        long elapsed = nowMs - lastLoggedTime;

        if ( elapsed > minDelay ) {
            destLogger.info(message, args);
            lastLoggedTime = nowMs;
        }
    }

    public void warn (Logger destLogger, String message, Object... args) {
        long nowMs = System.nanoTime() / 1000000L;
        long elapsed = nowMs - lastLoggedTime;

        if ( elapsed > minDelay ) {
            destLogger.warn(message, args);
            lastLoggedTime = nowMs;
        }
    }

    public void error (Logger destLogger, String message, Object... args) {
        long nowMs = System.nanoTime() / 1000000L;
        long elapsed = nowMs - lastLoggedTime;

        if ( elapsed > minDelay ) {
            destLogger.error(message, args);
            lastLoggedTime = nowMs;
        }
    }
}
