package com.threedr3am.exp.dubbo.payload;

/**
 * @author threedr3am
 */
public interface Payload {

  Object getPayload(String[] args) throws Exception;
}
