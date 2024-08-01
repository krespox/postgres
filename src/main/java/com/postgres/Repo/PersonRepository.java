package com.postgres.Repo;

import com.postgres.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {


    // by lastname
    @Query(value = "SELECT p FROM Person p WHERE p.lastName = :lastName")
    List<Person> findPersonsByLastName(@Param("lastName") String lastName);
}
//personvalidator sprawdzanie poprawnych danych +++
// first name last name nie moze byc takie samo +++
// nie moze bys spacji w first i lst name +++
// oba zaczynaja sie z wielkiej litery +++
// port zmienic na inny z 8080 +++
//serializacja i deserializacja json +++
// testy do kazdej klasy +++
// do repo customowe query"select" +++
//oblsuga plikow++

// customowe zeby skasowac po imieniu (natywne query)
// feinklient implementacja komunikacji z aplka na node-red[nowe endpoint i po wysłaniu wysyla wszystkie do node-red zeby wszystko wyslał na koncu] ""do kazdej
// wartosci dodac ciag znakow i bedzie on konfigurowany w yaml""

// rups-teoria i podstawy ""set kolekcje mapy listy" cashowanie cashowanie samodzielne i na zawolanie

//<groupId>org.assertj</groupId>
//       <artifactId>assertj-core</artifactId>
//liquid base


// sam sql poczytac na chat"podstawy" potem sql postgres
//tranzakcyjnosc (odziellne dla azdy danych i do stringa)
//sql postgres{spring data,

// dodac cos od siebie








//generacja tokena wkleic do gita itd










// hibernate
// helm - z kubernetesem powiazany
// teraform zwiazane z cloudem
// kolekcje i dodac do kodu listy mapy sety
// bardzo wazne jak wyglada obsuga wyjatkow, czym sa, jakie sa podstawowe itd
// azure devops (pipeline i bildpipeline1)
// azure [pczytac jak gita obslugiwac co tam robic itd
