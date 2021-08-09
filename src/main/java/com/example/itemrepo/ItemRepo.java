package com.example.itemrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.Items;

@Repository
public interface ItemRepo extends JpaRepository<Items, Long>{

}
