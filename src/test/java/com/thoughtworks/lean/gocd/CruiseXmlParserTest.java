package com.thoughtworks.lean.gocd;

import com.google.common.io.Resources;
import com.thoughtworks.lean.gocd.dto.pipeline.PipelineConfig;
import org.junit.Test;

import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CruiseXmlParserTest {

    public static final String CRUISE_CONFIG_XML = "/cruise-config.xml";
    private CruiseXmlParser parser;

    @Test
    public void should_get_pipeline_groups() throws Exception {

        // given
        final String cruiseXml = Resources.toString(this.getClass().getResource(CRUISE_CONFIG_XML), UTF_8);
        parser = new CruiseXmlParser(cruiseXml);

        // when
        List<String> pipelineGroups = parser.getPipelineGroups();

        System.out.println(pipelineGroups.size());
        // then
        assertThat(pipelineGroups.size(), is(12));
    }

    @Test
    public void should_get_pipelines_by_group_name() throws Exception {

        // given
        final String cruiseXml = Resources.toString(this.getClass().getResource(CRUISE_CONFIG_XML), UTF_8);
        parser = new CruiseXmlParser(cruiseXml);

        // when
        List<PipelineConfig> pipelines = parser.getPipelines("test");

        // then
        assertThat(pipelines.size(), is(13));
    }


}
