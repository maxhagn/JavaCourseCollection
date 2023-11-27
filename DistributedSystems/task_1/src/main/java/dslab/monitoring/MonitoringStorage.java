package dslab.monitoring;

import java.util.concurrent.ConcurrentHashMap;

public class MonitoringStorage {
    public ConcurrentHashMap<String, Long> serverCounts = new ConcurrentHashMap<String, Long>();
    public ConcurrentHashMap<String, Long> addressCounts = new ConcurrentHashMap<String, Long>();

    public synchronized void countServer(String server) {
        if (!serverCounts.containsKey(server)) {
            serverCounts.put(server, 1L);
        } else {
            serverCounts.replace(server, serverCounts.get(server) + 1);
        }
    }

    public synchronized void countAddress(String address) {
        if (!addressCounts.containsKey(address)) {
            addressCounts.put(address, 1L);
        } else {
            addressCounts.replace(address, addressCounts.get(address) + 1);
        }
    }
}
