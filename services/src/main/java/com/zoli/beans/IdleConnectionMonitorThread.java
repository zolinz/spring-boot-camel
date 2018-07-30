package com.zoli.beans;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class IdleConnectionMonitorThread implements Runnable {
        private HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;


        public void setHttpClientConnectionManager(HttpClientConnectionManager connMgr){
            System.out.println("Monitor setting conn manager");
            this.connMgr = connMgr;
        }

   /* public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }*/

        @Override
        public void run() {

            System.out.println("Monitor thread called");
            try {
                while (!shutdown) {
                    synchronized (this) {
                        System.out.println("Hello from Monitor thread");
                        wait(1000 * 60 );
                        System.out.println("Monitor thread closing expired connections");
                        // Close expired connections
                        connMgr.closeExpiredConnections();

                        System.out.println("Monitor thread closing idle connections");
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }


    }
