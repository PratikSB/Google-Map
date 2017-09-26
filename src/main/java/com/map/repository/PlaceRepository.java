package com.map.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.map.entity.Place;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> , Serializable{

}
