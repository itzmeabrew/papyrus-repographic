package com.prx.repographics.Repository;

import com.prx.repographics.Model.Repographics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepographicsRepo extends JpaRepository<Repographics,Integer>
{
}
