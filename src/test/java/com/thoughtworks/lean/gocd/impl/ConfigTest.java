package com.thoughtworks.lean.gocd.impl;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;

/**
 * Created by yongliuli on 8/13/16.
 */
public class ConfigTest {
    @Test
    public void testConfig() throws IOException {
        ///go/admin/config_xml
        final String cruiseXml = Resources.toString(this.getClass().getResource("/cruise-config.xml"), UTF_8);

    }
}
