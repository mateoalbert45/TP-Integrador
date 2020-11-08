package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Hotel;
import demo.model.Vuelo;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
