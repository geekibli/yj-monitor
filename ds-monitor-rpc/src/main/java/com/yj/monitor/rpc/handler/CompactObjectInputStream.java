package com.yj.monitor.rpc.handler;

import io.netty.handler.codec.serialization.ClassResolver;

import java.io.*;

/**
 * @Author gaolei
 * @Date 2022/1/27 下午5:32
 * @Version 1.0
 */
public class CompactObjectInputStream extends ObjectInputStream {
    private final ClassResolver classResolver;

    CompactObjectInputStream(InputStream in, ClassResolver classResolver) throws IOException {
        super(in);
        this.classResolver = classResolver;
    }

    @Override
    protected void readStreamHeader() throws IOException {
        int version = this.readByte() & 255;
        if (version != 5) {
            throw new StreamCorruptedException("Unsupported version: " + version);
        }
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        int type = this.read();
        if (type < 0) {
            throw new EOFException();
        } else {
            switch(type) {
                case 0:
                    return super.readClassDescriptor();
                case 1:
                    String className = this.readUTF();
                    Class<?> clazz = this.classResolver.resolve(className);
                    return ObjectStreamClass.lookupAny(clazz);
                default:
                    throw new StreamCorruptedException("Unexpected class descriptor type: " + type);
            }
        }
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        Class clazz;
        try {
            clazz = this.classResolver.resolve(desc.getName());
        } catch (ClassNotFoundException var4) {
            clazz = super.resolveClass(desc);
        }

        return clazz;
    }
}
