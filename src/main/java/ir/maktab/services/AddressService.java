package ir.maktab.services;

import ir.maktab.entities.Address;
import ir.maktab.repository.Impl.AddressRepositoryImpl;

public class AddressService {
    private static Address address = new Address();
    private static AddressRepositoryImpl repository = new AddressRepositoryImpl();

    public static void insert(Address address) {
        repository.insert(address);
    }
}
