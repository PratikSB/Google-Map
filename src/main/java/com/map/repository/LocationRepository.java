package com.map.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.map.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>,Serializable{

}
