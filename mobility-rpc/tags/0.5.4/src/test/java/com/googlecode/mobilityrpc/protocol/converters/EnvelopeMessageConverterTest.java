/**
 * Copyright 2011 Niall Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.mobilityrpc.protocol.converters;

import com.googlecode.mobilityrpc.protocol.converters.messages.EnvelopeMessageConverter;
import com.googlecode.mobilityrpc.protocol.pojo.Envelope;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Niall Gallagher
 */
public class EnvelopeMessageConverterTest {

    @Test
    public void testEnvelopeMessageConverter() {
        EnvelopeMessageConverter converter = new EnvelopeMessageConverter();
        Envelope input = new Envelope(Envelope.MessageType.EXECUTION_REQUEST, new byte[] {1,2,3,4,5});
        byte[] serialized = converter.convertToProtobuf(input);
        System.out.println("Serialized to: " + serialized.length + " bytes");
        Envelope output = converter.convertFromProtobuf(serialized);
        System.out.println("Output: " + output);

        assertEquals(Envelope.MessageType.EXECUTION_REQUEST, output.getMessageType());
        assertArrayEquals(new byte[] {1,2,3,4,5}, output.getMessage());
    }
}
