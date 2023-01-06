package com.tripmate.common.properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletContextListener extends HttpServlet implements javax.servlet.ServletContextListener {
    private static final long serialVersionUID = 5130624434088735043L;
    private Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

    private void initialize(String propertyFile) {
        new PropertiesManager().loadProperties(propertyFile);
        if (logger.isInfoEnabled()) {
            logger.info("Properties Loaded..\n");
        }
    }

    private void destroyListener() {
        PropertiesManager.releaseProperties();
        if (logger.isInfoEnabled()) {
            logger.info("Properties Deleted..");
        }
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String propertyFile = context.getInitParameter("PropertiesFileName");

        initialize(context.getRealPath(propertyFile));
    }

    @Override
    public void init() throws ServletException {
        String propertyFile = getInitParameter("PropertiesFileName");
        ServletContext context = getServletContext();

        initialize(context.getRealPath(propertyFile));
    }

    public void contextDestroyed(ServletContextEvent sce) {
        destroyListener();
    }

    @Override
    public void destroy() {
        destroyListener();
    }

}