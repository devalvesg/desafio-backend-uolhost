package com.devalvesg.desafiouolhost.Repository;

import com.devalvesg.desafiouolhost.Entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    @Query(nativeQuery = true, value = """
		SELECT codinome FROM jogador;
			""")
    List<String> findAllCodinames();

}
