package com.fadili.learn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fadili.learn.models.Address;
import com.fadili.learn.models.UserApp;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findAllByUserApp(UserApp userApp);
	Address findByUserAppAndAddressId(UserApp userApp, String addressID);
	
}
