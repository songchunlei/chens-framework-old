package com.chens.admin.util;


import com.chens.admin.remote.ISysLogClient;
import com.chens.admin.entity.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 保存系统日志工具
 * 参考自 https://gitee.com/geek_qi/ace-security
 */
public class DBLog extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(DBLog.class);

    private static DBLog dblog = null;
    private static BlockingQueue<SysLog> logInfoQueue = new LinkedBlockingQueue<SysLog>(1024);
    private ISysLogClient sysLogClient;

    public ISysLogClient getSysLogClient() {
        return sysLogClient;
    }

    public DBLog setSysLogClient(ISysLogClient sysLogClient) {
        if(this.sysLogClient==null) {
            this.sysLogClient = sysLogClient;
        }
        return this;
    }



    public static synchronized DBLog getInstance() {
        if (dblog == null) {
            dblog = new DBLog();
        }
        return dblog;
    }

    private DBLog() {
        super("CLogOracleWriterThread");
    }

    public void offerQueue(SysLog sysLog) {
        try {
            logInfoQueue.offer(sysLog);
        } catch (Exception e) {
            logger.error("日志写入失败", e);
        }
    }

    @Override
    public void run() {
        // 缓冲队列
        List<SysLog> bufferedLogList = new ArrayList<SysLog>();
        while (true) {
            try {
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    // 写入日志
                    for(SysLog log:bufferedLogList){
                        sysLogClient.create(log);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception eee) {
                }
            } finally {
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}