package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static final String UNIC_BINDING_NAME_CAT = "Cat";
    public static final String UNIC_BINDING_NAME_DOG = "Dog";
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    // Pretend we're starting an RMI server as the SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            Remote stubCat = null;
            Remote stubDog = null;
            try {
                registry = LocateRegistry.createRegistry(2099);

                final Cat catService = new Cat("Васька");
                final Dog dogService = new Dog("Кабысдох");

                stubCat = UnicastRemoteObject.exportObject(catService, 0);
                stubDog = UnicastRemoteObject.exportObject(dogService,0);

                registry.bind(UNIC_BINDING_NAME_CAT, stubCat);
                registry.bind(UNIC_BINDING_NAME_DOG, stubDog);
            } catch (RemoteException e) {
                e.printStackTrace(System.err);
            } catch (AlreadyBoundException e) {
                e.printStackTrace(System.err);
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        // Starting an RMI server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        // Start the client
        CLIENT_THREAD.start();
    }
}