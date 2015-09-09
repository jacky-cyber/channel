package com.zjht.channel.server;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: ServerLauncher
 * @Description: 系统启动入口
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月26日
 */
public class ServerLauncher {
    private final static Logger logger = LoggerFactory
            .getLogger(ServerLauncher.class);

    private SpringManager springMgr;

    public static void main(String[] args) {
        long start = 0L;
        long end = 0L;
        try {
            logger.info("开始启动channel-server系统...");
            start = System.currentTimeMillis();
            new ServerLauncher().init();
            end = System.currentTimeMillis();
            logger.info("成功启动channel-server系统，耗时:[{}]ms", (end - start));
        } catch (Throwable e) {
            logger.error("启动channel-server系统失败,系统退出!", e);
            System.exit(-1);
        }
    }

    /**
     * start:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @since JDK 1.8
     */
    private ServerLauncher start() {
        logger.info("开始启动系统");
        // Runner.runExample(Server.class);
        VertxOptions options = new VertxOptions().setClustered(false);
//        DeploymentOptions deploymentOptions = null;
        String verticleID = Server.class.getName();
        /*
         * System.setProperty("vertx.cwd", exampleDir); Consumer<Vertx> runner =
         * vertx -> { try { if (deploymentOptions != null) {
         * vertx.deployVerticle(verticleID, deploymentOptions); } else {
         * vertx.deployVerticle(verticleID); } } catch (Throwable t) {
         * t.printStackTrace(); } }; if (options.isClustered()) {
         * Vertx.clusteredVertx(options, res -> { if (res.succeeded()) { Vertx
         * vertx = res.result(); runner.accept(vertx); } else {
         * res.cause().printStackTrace(); } }); } else { Vertx vertx =
         * Vertx.vertx(options); runner.accept(vertx); }
         */

        Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(verticleID);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        runner.accept(Vertx.vertx(options));
        logger.info("成功启动系统");
        return this;
    }

    /**
     * 初始化系统配置. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private ServerLauncher configure() {
        logger.info("开始初始化系统配置");
        logger.info("成功初始化系统配置");
        return this;
    }

    /**
     * init:初始化spring容器. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    private ServerLauncher init() {
        // String filename = "spring.xml";
        logger.info("开始初始化spring容器");
        springMgr = SpringManager.newInstance();
        /*
         * logger.debug("通过配置文件[{}]初始化spring容器",filename);
         * SpringManager.newInstance() .initApplicationContext(filename)
         * .getAppContext() .registerShutdownHook();
         */
        logger.debug("通过注解类[{}]初始化spring容器",SpringConfiguration.class.getName());
        springMgr.initApplicationContext(SpringConfiguration.class)
                .getAppContext().registerShutdownHook();
        logger.info("成功初始化spring容器");
        return this;
    }
}
