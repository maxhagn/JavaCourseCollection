package dslab.nameserver;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.util.Config;

public class Nameserver implements INameserver, INameserverRemote {

    private Config config;
    private Registry registry;
    private final InputStream in;
    private final PrintStream out;
    private final Shell shell;
    private final String componentId;
    private final String nameserverIdentifier;
    private ConcurrentHashMap<String, INameserverRemote> registeredNameservers;
    private ConcurrentHashMap<String, String> registeredMailboxServers;

    /**
     * Creates a new server instance.
     *
     * @param componentId the id of the component that corresponds to the Config resource
     * @param config the component config
     * @param in the input stream to read console input from
     * @param out the output stream to write console output to
     */
    public Nameserver(String componentId, Config config, InputStream in, PrintStream out) {

        this.config = config;
        this.in = in;
        this.out = out;
        shell = new Shell(in, out);
        shell.register(this);
        shell.setPrompt(componentId + ">");
        this.componentId = componentId;
        this.registeredNameservers = new ConcurrentHashMap<String, INameserverRemote>();
        this.registeredMailboxServers = new ConcurrentHashMap<String, String>();

        if ( componentId.equals("ns-root") ) { this.nameserverIdentifier = config.getString("root_id");  }
        else { this.nameserverIdentifier = config.getString("domain"); }

    }

    /**
     * Initiates a new server instance.
     * @return void
     */
    @Override
    public void run() {

        if ( nameserverIdentifier.equals("root-nameserver")) {
            try {
                registry = LocateRegistry.createRegistry(config.getInt("registry.port"));
                INameserverRemote remote = (INameserverRemote) UnicastRemoteObject.exportObject(this, 0);
                registry.bind(config.getString("root_id"), remote);
            } catch (RemoteException e) {
                throw new RuntimeException("Error while starting server.", e);
            } catch (AlreadyBoundException e) {
                throw new RuntimeException("Error while binding remote object to registry.", e);
            }
        }

        else {
            try {
                registry = LocateRegistry.getRegistry(
                        config.getString("registry.host"),
                        config.getInt("registry.port")
                );
                INameserverRemote current = (INameserverRemote) UnicastRemoteObject.exportObject(this, 0);
                INameserverRemote root = (INameserverRemote) registry.lookup(config.getString("root_id"));
                root.registerNameserver(config.getString("domain"), current);

            } catch (RemoteException e) {
                throw new RuntimeException("Error while starting server.", e);
            } catch (NotBoundException e) {
                throw new RuntimeException("Error while lookup", e);
            } catch (InvalidDomainException e) {
                throw new RuntimeException("Error invalid domain", e);
            } catch (AlreadyRegisteredException e) {
                throw new RuntimeException("Error nameserver already registered", e);
            } catch (ZoneNotRegisteredException e) {
                throw new RuntimeException("Zone is missing", e);
            }
        }

        System.out.println("'" + nameserverIdentifier + "' nameserver started.");
        shell.run();
    }

    /**
     * Commandline command "nameservers". Prints all known nameservers.
     * @return void
     */
    @Command
    @Override
    public void nameservers() {
        ArrayList<String> keyList = new ArrayList<String>(registeredNameservers.keySet());
        keyList.sort(String::compareToIgnoreCase);
        int i = 1;
        for (String key : keyList) {
            this.out.format("%d. %s\r\n", i++, key);
        }
    }

    /**
     * Commandline command "addresses". Prints all known addresses.
     * @return void
     */
    @Command
    @Override
    public void addresses() {
        ArrayList<String> keyList = new ArrayList<String>(registeredMailboxServers.keySet());
        keyList.sort(String::compareToIgnoreCase);
        int i = 1;
        for (String key : keyList) {
            this.out.format("%d. %s %s\r\n", i++, key, registeredMailboxServers.get(key));
        }
    }

    /**
    * Commandline command "shutdown". Shutdown a single nameserver instance.
    * @return void
    */
    @Command
    @Override
    public void shutdown() {
        System.out.println("'" + nameserverIdentifier + "' Shutting server down.");
        try {
            UnicastRemoteObject.unexportObject(this, true);
            if ( nameserverIdentifier.equals("root-nameserver") ) {
                UnicastRemoteObject.unexportObject(registry, true);
            } else {
                registry.unbind(config.getString("root_id"));
            }

            System.out.println("'" + nameserverIdentifier + "' Server shut down.");
        } catch (RemoteException | NotBoundException e) {
            System.out.println("'" + nameserverIdentifier + "' Error while shutting down server: " + e.getMessage());
        }

        throw new StopShellException();
    }

    /**
     * Registers new nameservers recursively.
     *
     * @param domain the domain of the new nameserver
     * @param nameserver the remote object of the new nameserver
     * @return void
     */
    @Override
    public void registerNameserver(String domain, INameserverRemote nameserver) throws RemoteException, AlreadyRegisteredException, InvalidDomainException, ZoneNotRegisteredException {
        if ( domain.contains(".") ) {
            String lastDomain = domain.substring(domain.lastIndexOf('.') + 1).trim();
            if ( registeredNameservers.containsKey(lastDomain) ) {
                String newRequestDomain = domain.substring(0, domain.lastIndexOf('.')).trim();
                System.out.println("'" + nameserverIdentifier + "': Forwarding nameserver register request registerNameserver(" + newRequestDomain + ") to '" + lastDomain + "'.");
                registeredNameservers.get(lastDomain).registerNameserver(
                        newRequestDomain,
                        nameserver
                );
            } else {
                throw new ZoneNotRegisteredException("Zone " + lastDomain + " not registered.");
            }
        } else {
            if ( !registeredNameservers.containsKey(domain) ) {
                System.out.println("'" + nameserverIdentifier + "': Registering nameserver for zone '" + domain + "'.");
                registeredNameservers.put(domain, nameserver);
            } else {
                throw new AlreadyRegisteredException("The Domain " + domain + " is already registered.");
            }
        }
    }

    /**
     * Registers new mailbox server recursively.
     *
     * @param domain the domain of the new mailbox server
     * @param address the address of the mailbox server
     * @return void
     */
    @Override
    public void registerMailboxServer(String domain, String address) throws RemoteException, AlreadyRegisteredException, InvalidDomainException, ZoneNotRegisteredException {
        if ( domain.contains(".") ) {
            String lastDomain = domain.substring(domain.lastIndexOf('.') + 1).trim();
            if ( registeredNameservers.containsKey(lastDomain) ) {
                String newRequestDomain = domain.substring(0, domain.lastIndexOf('.')).trim();
                System.out.println("'" + nameserverIdentifier + "': Forwarding mailbox register request registerMailboxServer(" + newRequestDomain + ") to '" + lastDomain + "'.");
                registeredNameservers.get(lastDomain).registerMailboxServer(
                        newRequestDomain,
                        address
                );
            } else {
                throw new ZoneNotRegisteredException("Zone " + lastDomain + " not registered.");
            }
        } else {
            if ( !registeredMailboxServers.containsKey(domain) ) {
                System.out.println("'" + nameserverIdentifier + "': Registering mailbox server for zone '" + domain + "'.");
                registeredMailboxServers.put(domain, address);
            } else {
                throw new AlreadyRegisteredException("The Domain " + domain + " is already registered.");
            }
        }
    }

    /**
     * Returns a nameserver remote object.
     *
     * @param zone the zone of the requested nameserver.
     * @return INameserverRemote the requested server.
     */
    @Override
    public INameserverRemote getNameserver(String zone) throws RemoteException {
        return registeredNameservers.get(zone);
    }

    /**
     * Returns the address of a mailbox server.
     *
     * @param username the domain of the mailbox server.
     * @return String the requested mailbox server address.
     */
    @Override
    public String lookup(String username) throws RemoteException {
        System.out.println("'" + nameserverIdentifier + "': Nameserver for " + username + " requested by transfer server.");
        return registeredMailboxServers.get(username);
    }

    public static void main(String[] args) throws Exception {
        INameserver component = ComponentFactory.createNameserver(args[0], System.in, System.out);
        component.run();
    }
}
