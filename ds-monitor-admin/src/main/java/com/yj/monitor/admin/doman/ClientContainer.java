package com.yj.monitor.admin.doman;

import com.yj.monitor.api.domain.Client;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author gaolei
 * @Date 2022/1/20 下午12:23
 * @Version 1.0
 */
public class ClientContainer {

    private static final ConcurrentHashMap<String, Client> CLIENT_CONTAINER = new ConcurrentHashMap<>();

    private static final HashSet<String> MARKED_CLIENT = new HashSet<>();

    public static void online(String key, Client client) {
        CLIENT_CONTAINER.put(key, client);
    }

    public static void offline(String key) {
        CLIENT_CONTAINER.remove(key);
    }

    public static ConcurrentHashMap<String, Client> onlineClientMap() {
        return CLIENT_CONTAINER;
    }

    public static List<Client> onlineClient() {
        return new ArrayList<>(CLIENT_CONTAINER.values());
    }

    public static long onlineCount() {
        return onlineClientMap().size();
    }

    public static void clear() {
        CLIENT_CONTAINER.clear();
    }

    public static boolean existed(String key) {
        return CLIENT_CONTAINER.get(key) != null;
    }


    public static void markAddress(String address, String clientId) {

        if (MARKED_CLIENT.contains(clientId)) {
            return;
        }

        Client client = CLIENT_CONTAINER.get(clientId);
        if (client == null) {
            throw new RuntimeException("Not found client for " + clientId);
        }
        client.setAddress(address);
        MARKED_CLIENT.add(clientId);
    }

    public static void removeByAddress(String address) {
        String clientId = findClientId(address);
        if (StringUtils.isBlank(clientId)) {
            return;
        }
        CLIENT_CONTAINER.remove(clientId);
    }

    public static void removeByClient(String clientId) {
        CLIENT_CONTAINER.remove(clientId);
    }

    public static String findClientId(String address) {
        for (Map.Entry<String, Client> entry : CLIENT_CONTAINER.entrySet()) {
            Client client = entry.getValue();
            if (client.getAddress().equals(address)) {
                return entry.getKey();
            }
        }
        return null;
    }



}
