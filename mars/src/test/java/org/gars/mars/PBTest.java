package org.gars.mars;

import org.gars.mars.proto.ToPb;
import org.junit.Test;

/**
 * Created on 2018/12/6.
 *
 * @author grayCat
 * @since 1.0
 */
public class PBTest {

    @Test
    public void writePb() {
        try {
            String[] message = new String[5];
            message[0] = "org.gars.mars;";
            message[1] = "J:\\SVN\\youxi\\pb\\hahaha.proto";
            message[2] = "org.gars.mars.proto.ProtobufMessage";
            message[3] = null;
            ToPb.main(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
