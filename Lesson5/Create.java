package com.kafka.learning.Lesson5;

import org.apache.zookeeper.*;

import java.io.IOException;
import org.apache.zookeeper.data.Stat;
/**
 * Created by hadoop on 8/26/18.
 */
public class Create {
    public static void main(String[] args) throws IOException,KeeperException,InterruptedException
    {
        final ZooKeeper zk = new ZooKeeper("spark1234:12181",6000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("State: " + event.getState());
            }
            });


        zk.create("/chroot/test",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(10000);

        zk.create("/chroot/test",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(10000);
        zk.create("/chroot/test",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(10000);


        Stat stat = zk.exists("/chroot/test", new Watcher() {
            @Override
            public void process(WatchedEvent event)
            {
                System.out.println(event.getPath() + " | " + event.getType().name());
                try
                {
                    zk.exists("/chroot/test",this);
                } catch (KeeperException | InterruptedException e)
                {

                }
            }
        }

        );
        Thread.sleep(100000);
        zk.close();
    }



}
