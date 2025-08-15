package com.pahanaedu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelpTopicTest {

    @Test
    public void testConstructorAndGetters() {
        HelpTopic topic = new HelpTopic(
                "System Usage Guidelines",
                "This section explains how to use the billing system correctly."
        );

        assertEquals("System Usage Guidelines", topic.getTitle());
        assertEquals("This section explains how to use the billing system correctly.", topic.getDescription());
    }

    @Test
    public void testEmptyStrings() {
        HelpTopic topic = new HelpTopic("", "");

        assertEquals("", topic.getTitle());
        assertEquals("", topic.getDescription());
    }

    @Test
    public void testNullValues() {
        HelpTopic topic = new HelpTopic(null, null);

        assertNull(topic.getTitle());
        assertNull(topic.getDescription());
    }
}
