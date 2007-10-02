package com.mycompany.proto.console.hello.client.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * <p>Facade for logging into commons logging framework. Includes convienience methods 
 * for the various log levels.</p>
 * <p>Logging Levels:
 * <ul>
 * <li>Trace: messages used to simply show the execution call flow of a particular thread.  Note: log4j
 * treats Trace events as Debug events.</li>
 * <li>Debug: messages that are mainly relevent in a debugging environment and generally considered too
 * verbose for production.</li>
 * <li>Info: messages that denote important events that are considered normal.</li>
 * <li>Warning: messages that describe events that may cause an error, or an event that caused by the
 * application being used in an unrecommended fashion.</li>
 * <li>Error: messages that describe errors caused by potential bugs in the application, or by the
 * application being used incorrectly.  Errors can also caused by sources other than the application
 * (e.g. the database, third-party applications, the operating system, other applications).</li>
 * <li>Fatal: An error that represents an unrecoverable condition (e.g. an invalid configuration, 
 * another application or service that the application must leverage in order to function being 
 * unavailable).</li>
 * </ul>
 * </p>
 * @author jeremyp
 */
public final class Log {
    
    private static final Level TRACE = Level.ALL;
    private static final Level DEBUG = Level.FINEST;
    private static final Level INFO = Level.INFO;
    private static final Level WARN = Level.WARNING;
    private static final Level ERROR = Level.SEVERE;
    private static final Level FATAL = Level.SEVERE;
    
    public static void setLevelToTrace(String category) { setLevel(category, TRACE); }
    public static void setLevelToDebug(String category) { setLevel(category, DEBUG); }
    public static void setLevelToInfo(String category) { setLevel(category, INFO); }
    public static void setLevelToWarn(String category) { setLevel(category, WARN); }
    public static void setLevelToError(String category) { setLevel(category, ERROR); }
    public static void setLevelToFatal(String category) { setLevel(category, FATAL); }

    /**
     * Private constructor to ensure static usage.
     */
    private Log() { }
    
    /**
     * Logs a message at trace level.
     *
     * @param callingObjectRef the originating object
     * @param message the log message
     */
    public static void trace(Object callingObjectRef, String message) {
        trace(GWT.getTypeName(callingObjectRef), message, (Throwable) null);
    }

    /**
     * Logs a message at trace level triggered by catching a throwable.
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void trace(Object callingObjectRef, String message, Throwable throwable) {
        trace(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at trace level
//     *
//     * @param clazz the category of the log message
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void trace(Class clazz, String message, Throwable throwable) {
//        trace(clazz.getName(), message, throwable);
//    }
//
//    /**
//     * Logs a message at trace level
//     *
//     * @param clazz the category of the log message
//     * @param message the message to log
//     */
//    public static void trace(Class clazz, String message) {
//        trace(clazz.getName(), message);
//    }

    /**
     * Logs a message at trace level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void trace(String category, String message) {
        trace(category, message, (Throwable) null);
    }

    /**
     * Logs a message at trace level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void trace(String category, String message, Throwable throwable) {
        getLogger(category).log(TRACE,createMessage(message, throwable), throwable);
    }

    /**
     * Logs a message at debug level.
     *
     * @param callingObjectRef the originating object
     * @param message the log message
     */
    public static void debug(Object callingObjectRef, String message) {
        debug(GWT.getTypeName(callingObjectRef), message, (Throwable) null);
    }

    /**
     * Logs a message at debug level triggered by catching a throwable.
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void debug(Object callingObjectRef, String message, Throwable throwable) {
        debug(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at debug level.
//     *
//     * @param clazz the originating class
//     * @param message the log message
//     */
//    public static void debug(Class clazz, String message) {
//        debug(clazz.getName(), message, (Throwable) null);
//    }
//
//    /**
//     * Logs a message at debug level triggered by catching a throwable.
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void debug(Class clazz, String message, Throwable throwable) {
//        debug(clazz.getName(), message, throwable);
//    }

    /**
     * Logs a message at debug level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void debug(String category, String message) {
        debug(category, message, (Throwable) null);
    }

    /**
     * Logs a message at debug level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void debug(String category, String message, Throwable throwable) {
        getLogger(category).log(DEBUG,createMessage(message, throwable), throwable);
    }
    
    // The INFO level sigs

    /**
     * Logs a message at info level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     */
    public static void info(Object callingObjectRef, String message) {
        info(GWT.getTypeName(callingObjectRef), message, (Throwable) null);
    }

    /**
     * Logs a message at info level triggered by catching a throwable.
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void info(Object callingObjectRef, String message, Throwable throwable) {
        info(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at info level
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     */
//    public static void info(Class clazz, String message) {
//        info(clazz.getName(), message, (Throwable) null);
//    }
//
//    /**
//     * Logs a message at info level triggered by catching a throwable.
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void info(Class clazz, String message, Throwable throwable) {
//        info(clazz.getName(), message, throwable);
//    }

    /**
     * Logs a message at info level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void info(String category, String message) {
        info(category, message, (Throwable) null);
    }

    /**
     * Logs a message at info level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void info(String category, String message, Throwable throwable) {
        getLogger(category).log(INFO,createMessage(message, throwable), throwable);
    }

    // The WARN level sigs   

    /**
     * Logs a message at warn level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     */
    public static void warn(Object callingObjectRef, String message) {
        warn(GWT.getTypeName(callingObjectRef), message, (Throwable) null);
    }

    /**
     * Logs a message at warn level
     *
     * @param callingObjectRef the calling object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void warn(Object callingObjectRef, String message, Throwable throwable) {
        warn(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at warn level
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     */
//    public static void warn(Class clazz, String message) {
//        warn(clazz.getName(), message, (Throwable) null);
//    }
//
//    /**
//     * Logs a message at warn level
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void warn(Class clazz, String message, Throwable throwable) {
//        warn(clazz.getName(), message, throwable);
//    }

    /**
     * Logs a message at warn level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void warn(String category, String message) {
        warn(category, message, (Throwable) null);
    }

    /**
     * Logs a message at warn level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void warn(String category, String message, Throwable throwable) {
        getLogger(category).log(WARN,createMessage(message, throwable), throwable);
    }

    // The ERROR level sigs  

    /**
     * Logs a message at error level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     */
    public static void error(Object callingObjectRef, String message) {
        error(GWT.getTypeName(callingObjectRef), message, (Throwable) null);
    }

    /**
     * Logs a message at error level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void error(Object callingObjectRef, String message, Throwable throwable) {
        error(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at error level
//     *
//     * @param clazz the originating clazz
//     * @param message the message to log
//     */
//    public static void error(Class clazz, String message) {
//        error(clazz.getName(), message, (Throwable) null);
//    }
//
//    /**
//     * Logs a message at error level
//     *
//     * @param clazz the originating clazz
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void error(Class clazz, String message, Throwable throwable) {
//        error(clazz.getName(), message, throwable);
//    }

    /**
     * Logs a message at error level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void error(String category, String message) {
        error(category, message, (Throwable) null);
    }

    /**
     * Logs a message at error level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void error(String category, String message, Throwable throwable) {
        getLogger(category).log(ERROR,createMessage(message, throwable), throwable);
    }

    // The FATAL level sigs  

    /**
     * Logs a message at fatal level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     */
    public static void fatal(Object callingObjectRef, String message) {
        fatal(GWT.getTypeName(callingObjectRef), message,  (Throwable) null);
    }

    /**
     * Logs a message at fatal level
     *
     * @param callingObjectRef the originating object
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void fatal(Object callingObjectRef, String message, Throwable throwable) {
        fatal(GWT.getTypeName(callingObjectRef), message, throwable);
    }

//    /**
//     * Logs a message at fatal level
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     */
//    public static void fatal(Class clazz, String message) {
//        fatal(clazz.getName(), message,  (Throwable) null);
//    }
//
//    /**
//     * Logs a message at fatal level
//     *
//     * @param clazz the originating class
//     * @param message the message to log
//     * @param throwable the throwable triggering this log event
//     */
//    public static void fatal(Class clazz, String message, Throwable throwable) {
//        fatal(clazz.getName(), message, throwable);
//    }

    /**
     * Logs a message at fatal level
     *
     * @param category the category of the log message
     * @param message the message to log
     */
    public static void fatal(String category, String message) {
        fatal(category, message, (Throwable) null);
    }

    /**
     * Logs a message at fatal level
     *
     * @param category the category of the log message
     * @param message the message to log
     * @param throwable the throwable triggering this log event
     */
    public static void fatal(String category, String message, Throwable throwable) {
        getLogger(category).log(FATAL,createMessage(message, throwable), throwable);
    }

    /**
     * Check if debug level is enabled
     *
     * @param category the category of the log message
     * @return true if debug is enabled for the specified category
     */
    public static boolean isDebugEnabled(String category) {
        return getLogger(category).isLoggable(DEBUG);
    }

//    /**
//     * Check if debug level is enabled
//     *
//     * @param clazz the originating class
//     * @return true if debug is enabled for the category of the specified object
//     */
//    public static boolean isDebugEnabled(Class clazz) {
//        return isDebugEnabled(clazz.getName());
//    }

    /**
     * Check if debug level is enabled
     *
     * @param callingObjectRef the originating object
     * @return true if debug is enabled for the category of the specified object
     */
    public static boolean isDebugEnabled(Object callingObjectRef) {
        return isDebugEnabled(GWT.getTypeName(callingObjectRef));
    }
    
    /**
     * Check if Error level is enabled
     *
     * @param category the category of the log message
     * @return true if Error is enabled for the specified category
     */
    public static boolean isErrorEnabled(String category) {
        return getLogger(category).isLoggable(ERROR);
    }

//    /**
//     * Check if Error level is enabled
//     *
//     * @param clazz the originating class
//     * @return true if Error is enabled for the category of the specified object
//     */
//    public static boolean isErrorEnabled(Class clazz) {
//        return isErrorEnabled(clazz.getName());
//    }

    /**
     * Check if Error level is enabled
     *
     * @param callingObjectRef the originating object
     * @return true if Error is enabled for the category of the specified object
     */
    public static boolean isErrorEnabled(Object callingObjectRef) {
        return isErrorEnabled(GWT.getTypeName(callingObjectRef));
    }
    
    /**
     * Check if Fatal level is enabled
     *
     * @param category the category of the log message
     * @return true if Fatal is enabled for the specified category
     */
    public static boolean isFatalEnabled(String category) {
        return getLogger(category).isLoggable(FATAL);
    }

//    /**
//     * Check if Fatal level is enabled
//     *
//     * @param clazz the originating class
//     * @return true if Fatal is enabled for the category of the specified object
//     */
//    public static boolean isFatalEnabled(Class clazz) {
//        return isFatalEnabled(clazz.getName());
//    }

    /**
     * Check if Fatal level is enabled
     *
     * @param callingObjectRef the originating object
     * @return true if Fatal is enabled for the category of the specified object
     */
    public static boolean isFatalEnabled(Object callingObjectRef) {
        return isFatalEnabled(GWT.getTypeName(callingObjectRef));
    }

    /**
     * Check if info level is enabled
     *
     * @param category the category of the log message
     * @return true if info is enabled for the specified category
     */
    public static boolean isInfoEnabled(String category) {
        return getLogger(category).isLoggable(INFO);
    }

    /**
     * Check if info level is enabled
     *
     * @param callingObjectRef the calling object
     * @return true if info is enabled for the category of the object reference
     */
    public static boolean isInfoEnabled(Object callingObjectRef) {
        return isInfoEnabled(GWT.getTypeName(callingObjectRef));
    }

//    /**
//     * Check if info level is enabled
//     *
//     * @param clazz the calling class
//     * @return true if info is enabled for the category of the object reference
//     */
//    public static boolean isInfoEnabled(Class clazz) {
//        return isInfoEnabled(clazz.getName());
//    }
    
    /**
     * Check if Trace level is enabled
     *
     * @param category the category of the log message
     * @return true if Trace is enabled for the specified category
     */
    public static boolean isTraceEnabled(String category) {
        return getLogger(category).isLoggable(TRACE);
    }

//    /**
//     * Check if Trace level is enabled
//     *
//     * @param clazz the originating class
//     * @return true if Trace is enabled for the category of the specified object
//     */
//    public static boolean isTraceEnabled(Class clazz) {
//        return isTraceEnabled(clazz.getName());
//    }

    /**
     * Check if Trace level is enabled
     *
     * @param callingObjectRef the originating object
     * @return true if Trace is enabled for the category of the specified object
     */
    public static boolean isTraceEnabled(Object callingObjectRef) {
        return isTraceEnabled(GWT.getTypeName(callingObjectRef));
    }
    
    /**
     * Check if warn level is enabled
     *
     * @param category the category of the log message
     * @return true if warn is enabled for the specified category
     */
    public static boolean isWarnEnabled(String category) {
        return getLogger(category).isLoggable(WARN);
    }

//    /**
//     * Check if Warn level is enabled
//     *
//     * @param clazz the calling class
//     * @return true if Warn is enabled for the category of the object reference
//     */
//    public static boolean isWarnEnabled(Class clazz) {
//        return isWarnEnabled(clazz.getName());
//    }

    /**
     * Check if Warn level is enabled
     *
     * @param callingObjectRef the calling object
     * @return true if Warn is enabled for the category of the object reference
     */
    public static boolean isWarnEnabled(Object callingObjectRef) {
        return isWarnEnabled(GWT.getTypeName(callingObjectRef));
    }
    
    private static Logger getLogger(String category) {
    	return Logger.getLogger(category);
    }
    
    private static String createMessage(String message, Throwable throwable) {
    	if (throwable != null) {
    		HTML html = new HTML("<pre>" + printStackTrace(throwable.getStackTrace()) + "</pre>");
    		html.setWidth("400px");
    		PopupPanel popup = new PopupPanel(true);
    		popup.setVisible(true);
    		popup.setWidget(html);
    		popup.setStyleName("ks-popups-Popup");
    		popup.setPopupPosition(200, 200);
    		popup.show();
    		return message + " - " + throwable.getMessage();
    	} else {
    		return message;
    	}
    }
    
    private static String printStackTrace(Object[] stackTrace) {
    	if (stackTrace == null || stackTrace.length == 0) {
    		return "(Empty Stack Trace)";
    	} else {
	    	StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < stackTrace.length; i++) {
				buffer.append(stackTrace[i] + "<br/>");
			}
			return buffer.toString();
    	}
	}

	public static void setLevel(String category, Level level) {
		Logger.getLogger(category).setLevel(level);
	}
}
