package com.threedr3am.exp.dubbo.protocol.dubbo;

import com.threedr3am.exp.dubbo.protocol.Protocol;
import com.threedr3am.exp.dubbo.serialization.Serialization;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import org.apache.dubbo.common.io.Bytes;

/**
 * @author threedr3am
 */
public class DubboProtocol implements Protocol {

  public byte[] makeData(byte[] bytes, Serialization serialization) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    // header.
    byte[] header = new byte[16];
    // set magic number.
    Bytes.short2bytes((short) 0xdabb, header);
    // set request and serialization flag.
    header[2] = (byte) ((byte) 0x80 | 0x20 | serialization.getType());

    // set request id.
    Bytes.long2bytes(new Random().nextInt(100000000), header, 4);

    Bytes.int2bytes(bytes.length, header, 12);
    try {
      byteArrayOutputStream.write(header);
      byteArrayOutputStream.write(bytes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return byteArrayOutputStream.toByteArray();
  }

}
