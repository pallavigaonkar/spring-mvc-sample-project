package com.util;

import org.slf4j.Logger;

public final class MessageLogger {

	private MessageLogger() {
	}

	public static void error(final Logger logger, final String message) {
		logger.error(message);
	}

	public static void error(final Logger logger, final String message, final Exception ex) {
		logger.error(message, ex);
	}

	public static void debug(final Logger logger, final String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public static void debug(final Logger logger, final String message, final Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug(message, ex);
		}

	}

	public static void info(final Logger logger, final String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	public static void info(final Logger logger, final String message, final Exception ex) {
		if (logger.isInfoEnabled()) {
			logger.info(message, ex);
		}
	}

	public static void trace(final Logger logger, final String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	public static void trace(final Logger logger, final String message, final Exception ex) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, ex);
		}
	}

	public static void error(final org.apache.log4j.Logger logger, final String message) {
		logger.error(message);
	}

	public static void error(final org.apache.log4j.Logger logger, final String message, final Exception ex) {
		logger.error(message, ex);
	}

	public static void debug(final org.apache.log4j.Logger logger, final String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public static void debug(final org.apache.log4j.Logger logger, final String message, final Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug(message, ex);
		}

	}

	public static void info(final org.apache.log4j.Logger logger, final String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	public static void info(final org.apache.log4j.Logger logger, final String message, final Exception ex) {
		if (logger.isInfoEnabled()) {
			logger.info(message, ex);
		}
	}

	public static void trace(final org.apache.log4j.Logger logger, final String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	public static void trace(final org.apache.log4j.Logger logger, final String message, final Exception ex) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, ex);
		}
	}

	public static void info(final Logger logger, final Object arg) {
		if (logger.isInfoEnabled() && arg != null) {
			logger.info(arg.toString());
		}
	}

	public static void debug(final Logger logger, final Object arg) {
		if (logger.isDebugEnabled() && arg != null) {
			logger.debug(arg.toString());
		}
	}

	public static void error(final Logger logger, final Object arg) {
		if (logger.isErrorEnabled() && arg != null) {
			logger.error(arg.toString());
		}
	}

	public static void info(final org.apache.log4j.Logger logger, final Object arg) {
		if (logger.isInfoEnabled() && arg != null) {
			logger.info(arg.toString());
		}
	}

	public static void debug(final org.apache.log4j.Logger logger, final Object arg) {
		if (logger.isDebugEnabled() && arg != null) {
			logger.debug(arg.toString());
		}
	}

	public static void error(final org.apache.log4j.Logger logger, final Object arg) {
		if (arg != null) {
			logger.error(arg.toString());
		}
	}

	public static void warn(final Logger logger, final String message) {
		logger.warn(message);
	}

	public static void warn(final Logger logger, final String message, final Exception ex) {
		logger.warn(message, ex);
	}

	public static void warn(final org.apache.log4j.Logger logger, final Object arg) {
		if (arg != null) {
			logger.warn(arg.toString());
		}
	}

}
