package com.rbkmoney.dominant.cache;

import com.rbkmoney.damsel.domain_config.Head;
import com.rbkmoney.damsel.domain_config.Reference;
import com.rbkmoney.damsel.domain_config.RepositorySrv;
import com.rbkmoney.damsel.domain_config.Snapshot;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = DominantCacheApplication.class)
public class DominantCacheApplicationTest {

    @Value("${local.server.port}")
    protected int port;

    @Test
    public void contextLoads() throws URISyntaxException, TException {
        RepositorySrv.Iface build = new THSpawnClientBuilder()
                .withNetworkTimeout(10000)
                .withAddress(new URI("http://localhost:" + port + "/v1/dominant/cache"))
                .build(RepositorySrv.Iface.class);

        Head value = new Head();
        Reference reference = new Reference();
        reference.setHead(value);
        Snapshot checkout = build.checkout(reference);
    }
}
