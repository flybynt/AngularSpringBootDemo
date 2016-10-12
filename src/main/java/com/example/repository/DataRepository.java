package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.repository.entity.DataEntity;
 
// SPRING REST repository example
//
// SpringBoot REST verb to repository operation mappings with meta-data
//Examples:
// http://localhost:8080/repodemo
// http://localhost:8080/repodemo/search
// http://localhost:8080/repodemo/search/findByFirstName?fn=Mark
// http://localhost:8080/repodemo/search/findByLastName?ln=Coyote
// http://localhost:8080/repodemo/search/findByEmail?em=wileycoyote@acme.com
//
@RepositoryRestResource(path="repodemo")
public interface DataRepository extends JpaRepository<DataEntity, Long> {
	
	// These are explicit ways to search for a specific DataEntity with parameters
	Collection<DataEntity> findByFirstName (@Param("fn") String fn);
	Collection<DataEntity> findByLastName (@Param("ln") String ln);
	Collection<DataEntity> findByEmail (@Param("em") String em);
}
