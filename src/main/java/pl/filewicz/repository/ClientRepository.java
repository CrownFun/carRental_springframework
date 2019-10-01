package pl.filewicz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.filewicz.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
   List<Client> findBylastName(String lastName);

   Optional<Client> findAllByPhoneNumber(long phone);

   List<Client> findAllByAddresContaining(String addres);

}
