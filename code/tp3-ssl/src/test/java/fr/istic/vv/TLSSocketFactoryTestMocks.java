package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        SSLSocket sslSocket = Mockito.mock(SSLSocket.class);

        TLSSocketFactory f = new TLSSocketFactory();

        Mockito.when(sslSocket.getSupportedProtocols()).thenReturn(null);
        Mockito.when(sslSocket.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(sslSocket);
        Mockito.doThrow(new AssertionError()).when(sslSocket).setEnabledProtocols(Mockito.any(String[].class));
    }

    @Test
    public void typical()  {
        SSLSocket sslSocket = Mockito.mock(SSLSocket.class);

        TLSSocketFactory f = new TLSSocketFactory();

        Mockito.when(sslSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        Mockito.when(sslSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        f.prepareSocket(sslSocket);
        Mockito.verify(sslSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});

    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}