package com.donesvad.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class TestContext {

  private final Map<StorageKeys, Object> keyStorage = new ConcurrentHashMap<>();

  public void addToStorage(StorageKeys key, Object value) {
    keyStorage.put(key, value);
  }

  public Object getFromStorage(StorageKeys key) {
    return keyStorage.get(key);
  }

  public boolean contains(StorageKeys key) {
    return keyStorage.containsKey(key);
  }
}
