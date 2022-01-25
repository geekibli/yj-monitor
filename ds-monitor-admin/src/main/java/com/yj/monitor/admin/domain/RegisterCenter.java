package com.yj.monitor.admin.domain;

import com.yj.monitor.api.domain.Node;
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
public class RegisterCenter {

    private static final ConcurrentHashMap<String, Node> CLIENT_CONTAINER = new ConcurrentHashMap<>();

    private static final HashSet<String> MARKED_CLIENT = new HashSet<>();

    public static void online(String key, Node node) {
        CLIENT_CONTAINER.put(key, node);
    }

    public static void offline(String key) {
        CLIENT_CONTAINER.remove(key);
    }

    public static ConcurrentHashMap<String, Node> onlineClientMap() {
        return CLIENT_CONTAINER;
    }

    public static List<Node> onlineClient() {
        return new ArrayList<>(CLIENT_CONTAINER.values());
    }

    public static long onlineCount() {
        return onlineClientMap().size();
    }

    public static void clear() {
        CLIENT_CONTAINER.clear();
    }

    public static boolean isOnline(String key) {
        return CLIENT_CONTAINER.get(key) != null;
    }


    public static void markAddress(String address, String clientId) {
        if (MARKED_CLIENT.contains(clientId)) {
            return;
        }

        Node node = CLIENT_CONTAINER.get(clientId);
        if (node == null) {
            throw new RuntimeException("Not found client for " + clientId);
        }
        node.setAddress(address);
        MARKED_CLIENT.add(clientId);
    }

    public static void removeByAddress(String address) {
        String clientId = findClientId(address);
        if (StringUtils.isBlank(clientId)) {
            return;
        }
        removeByClient(clientId);
    }

    public static void removeByClient(String clientId) {
        offline(clientId);
    }

    public static String findClientId(String address) {
        for (Map.Entry<String, Node> entry : CLIENT_CONTAINER.entrySet()) {
            Node node = entry.getValue();
            if (node.getAddress().equals(address)) {
                return entry.getKey();
            }
        }
        return null;
    }


}
