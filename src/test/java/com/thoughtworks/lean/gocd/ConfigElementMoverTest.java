package com.thoughtworks.lean.gocd;

import com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;
import static org.junit.Assert.assertEquals;

/**
 * Created by yongliuli on 8/13/16.
 */
public class ConfigElementMoverTest {


    public static final String CRUISE_CONFIG_XML = "/cruise-config.xml";
    ConfigElementMover configElementMover;

    @Before
    public void setup() throws IOException {
        configElementMover = new ConfigElementMover(Resources.toString(this.getClass().getResource(CRUISE_CONFIG_XML), UTF_8));
    }

    @Test
    public void should_get_correct_group_order() {
        assertEquals(0, configElementMover.getGroupIndex("test"));
        assertEquals(1, configElementMover.getGroupIndex("Plugins"));
        assertEquals(2, configElementMover.getGroupIndex("Leansw-Services-Test"));
        assertEquals(3, configElementMover.getGroupIndex("Misc"));
        assertEquals(4, configElementMover.getGroupIndex("Deliflow-WebApp"));
        assertEquals(5, configElementMover.getGroupIndex("Deliflow-Identity"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception() {
        configElementMover.getGroupIndex("Badlfjakfjalkjk-Identity");
    }


    @Test
    public void should_move_group_to_before() {
        configElementMover.moveGroupToBefore("Plugins", "test");
        assertEquals(configElementMover.getGroupIndex("test") - 1, configElementMover.getGroupIndex("Plugins"));
    }

    @Test
    public void should_move_group__to_after() {
        configElementMover.moveGroupToAfter("test", "Plugins");
        assertEquals(configElementMover.getGroupIndex("test") - 1, configElementMover.getGroupIndex("Plugins"));
    }

    @Test
    public void should_move_group__to_top() {
        configElementMover.moveGroupToTop("Plugins");
        assertEquals(0, configElementMover.getGroupIndex("Plugins"));
        assertEquals(1, configElementMover.getGroupIndex("test"));
    }

    @Test
    public void should_move_group__to_bottom() {
        configElementMover.moveGroupToBottom("Plugins");
        assertEquals(configElementMover.getGroupSize() - 1, configElementMover.getGroupIndex("Plugins"));
    }

    @Test
    public void should_move_group_to_index() {
        configElementMover.moveGroupToIndex("Plugins", 8);
        assertEquals(8, configElementMover.getGroupIndex("Plugins"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_in_pipeline_group() {
        configElementMover.getPipelineCoordinate("test", "bababkajbka");
    }

    @Test
    public void should_get_pipeline_coordinate_2() {
        PipelineCoordinate pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-3");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 0);

        pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-2");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 2);

    }

    @Test
    public void should_get_pipeline_coordinate() {
        PipelineCoordinate pipelineCoordinate = configElementMover.getPipelineCoordinate("test", "test-pipeline-3");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 0);

        pipelineCoordinate = configElementMover.getPipelineCoordinate("test", "test-pipeline-2");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 2);


        pipelineCoordinate = configElementMover.getPipelineCoordinate("Plugins", "soanr-lean-test-pyramid-plugin");
        assertEquals(pipelineCoordinate.getGroupIndex(), 1);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 0);

    }

    @Test
    public void should_get_document() throws IOException {
        final String cruiseXml = Resources.toString(this.getClass().getResource(CRUISE_CONFIG_XML), UTF_8);
        Document document = Jsoup.parse(cruiseXml, UTF_8.displayName());
        Elements elements = document.select("cruise>pipelines");
        elements.forEach(element -> {
            System.out.println(element.attr("group"));
            element.select(">pipeline").forEach(element1 -> {
                System.out.println("--->" + element1.attr("name"));
            });
        });
        System.out.println();
    }


    @Test
    public void should_move_pipeline_to_group_top(){
        configElementMover.movePipelineToGroupTop("test-pipeline-2","test");
        PipelineCoordinate pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-2");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 0);

        pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-3");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 1);
    }

    @Test
    public void should_move_pipeline_to_group_and_index(){
        configElementMover.movePipelineToGroupAndIndex("test-pipeline-2","test" , 3);
        PipelineCoordinate pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-2");
        assertEquals(pipelineCoordinate.getGroupIndex(), 0);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 3);

    }


    @Test
    public void should_move_pipeline_to_group_bottom(){
        configElementMover.movePipelineToGroupBottom("test-pipeline-2","Plugins");
        PipelineCoordinate pipelineCoordinate = configElementMover.getPipelineCoordinate("test-pipeline-2");
        assertEquals(pipelineCoordinate.getGroupIndex(), 1);
        assertEquals(pipelineCoordinate.getPipelineIndexInGroup(), 2);


    }



}
