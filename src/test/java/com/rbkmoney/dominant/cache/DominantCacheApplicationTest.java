package com.rbkmoney.dominant.cache;

import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DominantCacheApplication.class)
public class DominantCacheApplicationTest {

    @Test
    public void contextLoads() throws URISyntaxException, TException {
    }
}
