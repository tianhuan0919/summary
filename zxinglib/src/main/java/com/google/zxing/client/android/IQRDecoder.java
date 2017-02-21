package com.google.zxing.client.android;

/**
 * Created by daiepngfei on 9/11/15
 */
public interface IQRDecoder {
    void decode(byte[] data, int width, int height);
}
