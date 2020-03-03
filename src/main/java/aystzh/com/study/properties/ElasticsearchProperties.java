package aystzh.com.study.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Created by zhanghuan on 2019/9/4.
 */
@Configuration
public class ElasticsearchProperties {

    @Value("${elasticsearch.cluster.nodes}")
    private String clusterNodes;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.client.transport.sniff}")
    private Boolean clientTransportSniff;

    @Value("${elasticsearch.client.ignore.cluster.name}")
    private Boolean clientIgnoreClusterName;

    @Value("${elasticsearch.client.ping.timeout}")
    private String clientPingTimeout;

    @Value("${elasticsearch.client.nodes.sampler.interval}")
    private String clientNodesSamplerInterval;


    public String getClusterNodes() {
        return clusterNodes;
    }


    public String getClusterName() {
        return clusterName;
    }


    public Boolean getClientTransportSniff() {
        return clientTransportSniff;
    }


    public Boolean getClientIgnoreClusterName() {
        return clientIgnoreClusterName;
    }


    public String getClientPingTimeout() {
        return clientPingTimeout;
    }


    public String getClientNodesSamplerInterval() {
        return clientNodesSamplerInterval;
    }


}
