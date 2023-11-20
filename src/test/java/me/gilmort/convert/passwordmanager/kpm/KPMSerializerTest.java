package me.gilmort.convert.passwordmanager.kpm;

import me.gilmort.convert.passwordmanager.kpm.model.KasperskyPasswordManager;
import me.gilmort.convert.passwordmanager.kpm.model.Websites;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class KPMSerializerTest {

    @Test
    void serialize_ValidInput_ShouldDeserializeCorrectly() throws IOException {
        URL resourceURL = this.getClass().getClassLoader().getResource("export_sample.txt");
        InputStream inputStream = resourceURL.openStream();

        KasperskyPasswordManager kpm = KPMSerializer.deserialize(inputStream);

        // Assuming you have appropriate equals methods in your classes for comparison
        // Modify the assertions based on your actual implementation
        assertEquals(2, kpm.websites().size());
        assertEquals(2, kpm.apps().size());
        assertEquals(2, kpm.notes().size());
    }

    @Test
    void serialize_InvalidInput_ShouldThrowIOException() {
        // Assuming an invalid input stream
        InputStream inputStream = new ByteArrayInputStream("Invalid Input".getBytes(StandardCharsets.UTF_8));

        // Ensure that an IOException is thrown when an invalid input is provided
        assertThrows(IOException.class, () -> KPMSerializer.deserialize(inputStream));
    }

    @Test
    void fromStringParameters_InvalidParams_ShouldThrowException() {
        // Assuming an invalid number of parameters for the constructor
        String[] invalidParams = {"param1", "param2"};

        // Ensure that an exception is thrown when an invalid number of parameters is provided
        assertThrows(Exception.class, () -> KPMSerializer.fromStringParameters(Websites.class, invalidParams));
    }
}