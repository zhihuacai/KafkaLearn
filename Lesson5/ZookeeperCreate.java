package com.kafka.learning.Lesson5;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;


/**
 * Created by hadoop on 8/5/18.
 */
public class ZookeeperCreate {

    public static void main(String[] args) throws IOException,KeeperException,InterruptedException
    {
        final ZooKeeper zk = new ZooKeeper("spark1234:12181", 6000,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("State: " + watchedEvent.getState());
                    }
                });
        zk.create("/chroot",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Stat stat = zk.exists("/chroot",new Watcher()
        {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath() + " | " + watchedEvent.getType().name());
                try{
                    zk.exists("/chroot",this);
                }
                catch (KeeperException|InterruptedException e)
                {
                    System.out.println( "Exception: " + e.getMessage().toString()
                            + " Trace: " + e.getStackTrace());
                }
            }
        }
        );

        Thread.sleep(600000);
        zk.close();

    }

}
