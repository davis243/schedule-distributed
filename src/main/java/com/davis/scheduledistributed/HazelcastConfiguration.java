package com.davis.scheduledistributed;

import java.io.FileNotFoundException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;

@Configuration
public class HazelcastConfiguration {

    private static final int RECEICED_MESSAGES_TRACK_TTL_SECS = 60 * 60 * 30;
    public static final String MESSAGE_MAP_NAME = "allmessages";
    @Bean
    public Config config() throws FileNotFoundException {
    	
    	 Config cfg = new Config();                  
         NetworkConfig network = cfg.getNetworkConfig();
         cfg.setGroupConfig(new GroupConfig("dev","dev-pass"));
         
         JoinConfig join = network.getJoin();
         cfg.getManagementCenterConfig().setEnabled(false);
         network.setPort(7701);
         network.setPortCount(100);
         network.setPortAutoIncrement(true);
         join.getTcpIpConfig().setEnabled(false);
         join.getAwsConfig().setEnabled(false);
         join.getMulticastConfig().setEnabled(true);
         join.getMulticastConfig().setMulticastTimeToLive(32);
         join.getMulticastConfig().setMulticastTimeoutSeconds(2);
         join.getMulticastConfig().setMulticastGroup("224.2.2.3");
         join.getMulticastConfig().setMulticastPort(54321);
    	
         
         
        return cfg.addMapConfig(
                new MapConfig()
                        .setName(MESSAGE_MAP_NAME)
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(RECEICED_MESSAGES_TRACK_TTL_SECS));

    }

}
