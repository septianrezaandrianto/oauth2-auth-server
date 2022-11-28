package com.rnd.oauth2authserver.repository;

import com.rnd.oauth2authserver.entity.ClientCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientCredentialRepository extends JpaRepository<ClientCredential,String> {

    @Query(value="SELECT * FROM client_credential WHERE client_id = :clientId", nativeQuery = true)
    ClientCredential getClientCredentialById(@Param("clientId") String clientId);
}
